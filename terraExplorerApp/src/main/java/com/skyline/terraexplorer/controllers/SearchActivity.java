package com.skyline.terraexplorer.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

import com.skyline.teapi.*;
import com.skyline.terraexplorer.R;
import com.skyline.terraexplorer.SearchSuggestionProvider;
import com.skyline.terraexplorer.TEAppException;
import com.skyline.terraexplorer.models.*;
import com.skyline.terraexplorer.models.SearchWeb.SearchResult;
import com.skyline.terraexplorer.tools.EditFavoriteTool;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.text.Html;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchActivity extends MatchParentActivity {
	
	private class TableDataSourceDelegateImpl extends TableDataSourceDelegateBase
	{
		@Override
		public void didSelectRowAtIndexPath(long packedPosition) {
			SearchActivity.this.didSelectRowAtIndexPath(packedPosition);
		}
		
		@Override
		public ContextMenuEntry[] contextMenuForPath(long packedPosition) {
			return SearchActivity.this.contexMenuForPath(packedPosition);
		}
		@Override
		public void contextMenuItemTapped(int menuId, long packedPosition) {
			SearchActivity.this.contextMenuItemTapped(menuId, packedPosition);
		}
		@Override
		public boolean accessoryButtonTappableForRowWithIndexPath(
				long packedPosition) {
			return true;
		}
		
		@Override
		public void accessoryButtonTappedForRowWithIndexPath(long packedPosition) {
			SearchActivity.this.accessoryButtonTappedForRowWithIndexPath(packedPosition);
		}
	}
	
	private class TESearchAsyncTask extends AsyncTask<String,Void, ArrayList<DisplayGroupItem>>
	{

		@Override
		protected ArrayList<DisplayGroupItem> doInBackground(String... queries) {
			final String query = queries[0];
			return UI.runOnRenderThread(new Callable<ArrayList<DisplayGroupItem>>() {
				@Override
				public ArrayList<DisplayGroupItem> call() throws Exception {
					return searchTE(query);
				}
				
			});
		}
		
		private ArrayList<DisplayGroupItem> searchTE(String query)
		{
	        DisplayGroupItem layers = new DisplayGroupItem(R.string.search_layers);
	        DisplayGroupItem places = new DisplayGroupItem(R.string.search_places);
	        ArrayList<DisplayGroupItem> allResults = new ArrayList<DisplayGroupItem>();
	        allResults.add(layers);
	        allResults.add(places);
	        // search TE
	        // walk over TE tree and find all places, locations and presentations
	        IProjectTree projectTree = ISGWorld.getInstance().getProjectTree();
	        ArrayList<String> groupsToProcess = new ArrayList<String>();
	        groupsToProcess.add(projectTree.getRootID());
	        while (groupsToProcess.size() > 0)
	        {
	            DisplayGroupItem itemGroup;
	            int tag;
	            String groupID = groupsToProcess.get(0);
	            String itemID = projectTree.GetNextItem(groupID, ItemCode.CHILD);
	            int icon;
	            while (itemID.isEmpty() == false)
	            {
                    ITerraExplorerObject object = projectTree.GetObject(itemID);
	                if(projectTree.IsGroup(itemID) && object == null)
	                {
	                	groupsToProcess.add(itemID);
	                }
	                else
	                {
	                    itemGroup = null;
	                    icon = 0;
	                    tag = 0;
	                    // if this is a place
	                    if(object.getObjectType() == ObjectTypeCode.OT_PRESENTATION)
	                    {
	                        itemGroup = places;
	                        tag = ITEM_PRESENTATION;
	                        icon = R.drawable.presentation;
	                    }
	                    if(object.getObjectType() == ObjectTypeCode.OT_LOCATION)
	                    {
	                        itemGroup = places;
	                        tag = ITEM_LOCATION;
	                        icon = R.drawable.location;
	                    }
	                    // if this is layer
	                    if(object.getObjectType() == ObjectTypeCode.OT_FEATURE_LAYER || object.getObjectType() == ObjectTypeCode.OT_3D_MESH_LAYER || object.getObjectType() == ObjectTypeCode.OT_IMAGERY_LAYER || object.getObjectType() == ObjectTypeCode.OT_ELEVATION_LAYER)
	                    {
	                        itemGroup = layers;
	                        tag = ITEM_LAYER;
	                        icon = R.drawable.layers_dark;
	                    }
	                    if(itemGroup != null)
	                    {
	                        String itemName = projectTree.GetItemName(itemID);
	                        DisplayItem displayItem = addToGroupFiltered(itemGroup, itemName, query);
	                        if(displayItem != null)
	                        {
	                            displayItem.id = object.getID();
	                            displayItem.tag = tag;
	                            displayItem.icon = icon;
	                        }
	                    }
	                }
	                itemID = projectTree.GetNextItem(itemID,ItemCode.NEXT);
	            }
	            groupsToProcess.remove(0);
	        }
	        // search for favorites
	        for(FavoriteItem fItem : FavoritesStorage.defaultStorage.getAll())
	        {
	            DisplayItem displayItem = addToGroupFiltered(places,fItem.name,query);
	            if(displayItem != null)
	            {
	                displayItem.id = fItem.id;
	                displayItem.tag = ITEM_FAVORITE;
	                displayItem.icon = R.drawable.favorit_places;
	            }
	        }
	        return allResults;
	}
	@Override
	protected void onPostExecute(ArrayList<DisplayGroupItem> allResults) {
		allResults.add(0, webSearchResults);
		dataSource.setDataItems(allResults);
	}
	
}
	
	private class WebSearchAsyncTask extends AsyncTask<String,Void, ArrayList<SearchResult>>
	{
		TEAppException error;
		String query;
		@Override
		protected ArrayList<SearchResult> doInBackground(String... queries) {
			SearchWeb searcher = new SearchWeb();
			try
			{
				query = queries[0];
				return searcher.search(query, new double[7]);
			}
			catch(TEAppException ex)
			{
				error = ex;
				return null;
			}
		}
		@Override
		protected void onPostExecute(ArrayList<SearchResult> result) {
			webSearchResults.childItems.clear();
			if(error != null)
			{
				String errorMessage = String.format(getString(R.string.search_error), error.getMessage());
				webSearchResults.childItems.add(new DisplayItem(errorMessage));
			}
			else
			{
				if(result.size() == 0)
					webSearchResults.childItems.add(new DisplayItem(R.string.search_nodata));
				for (SearchResult sr : result) {
					String name = sr.name;
					DisplayItem item = SearchActivity.this.addToGroupFiltered(webSearchResults,name, query);
					if(item == null)
					{
						item = new DisplayItem(name);
						webSearchResults.childItems.add(item);
					}
					item.id = String.format(Locale.US, "%fx%f", sr.lon, sr.lat);
					item.subTitle = sr.desc;
					item.accessoryIcon = R.drawable.favorits_checkbox;
					item.tag = ITEM_SEARCH_RESULT;
				}
			}
			SearchActivity.this.dataSource.reloadData();
		}
	}
	
	private static final int ITEM_SEARCH_RESULT = 2;
	private static final int  ITEM_LAYER = 3;
	private static final int  ITEM_PRESENTATION = 4;
	private static final int  ITEM_LOCATION = 5;
	private static final int  ITEM_FAVORITE = 6;
	private static final int ITEM_RECENT_SEARCH = 7;

	
	private SearchView searchView;
	private SearchManager searchManager;

	private DisplayGroupItem teSearchResults;
	private DisplayGroupItem webSearchResults;
	private WebSearchAsyncTask webSearchTask;
	private TESearchAsyncTask teSearchTask;
	
	private TableDataSource dataSource;
	private ExpandableListView tableView;
	private TableDataSourceDelegateImpl delegate = new TableDataSourceDelegateImpl();
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		UI.addHeader( R.string.title_activity_search, R.drawable.search,this);
		searchView = (SearchView)findViewById(R.id.search);
		searchView.setSubmitButtonEnabled(true);
		searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		
		tableView = (ExpandableListView)findViewById(android.R.id.list);
		dataSource = new TableDataSource(tableView, delegate);

		handleIntent(getIntent());

	}
	
	@Override
	protected void onNewIntent(Intent intent) {
	    setIntent(intent);
	    handleIntent(intent);
	}

	private void handleIntent(Intent intent) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
		
		if (query == null || query.trim().isEmpty()) {
			this.dataSource.setDataItems(this.recentSearchesFiltered(""));
		    searchView.requestFocus();
		}
		else
		{
				searchView.setQuery(intent.getStringExtra(SearchManager.QUERY), false);
				SearchRecentSuggestions suggestions = new SearchRecentSuggestions(SearchActivity.this,
						  SearchSuggestionProvider.AUTHORITY, SearchSuggestionProvider.MODE);
			    suggestions.saveRecentQuery(query, null);
			    doSearch(query);		      
		}
	}
	
	private ArrayList<DisplayGroupItem> recentSearchesFiltered(String filter)
	{
		DisplayGroupItem group = new DisplayGroupItem(R.string.search_recents);
		Cursor suggestions = getSuggestions(searchManager.getSearchableInfo(getComponentName()),filter, -1);
		int textColumnIndex = suggestions.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
		while(suggestions.moveToNext())
		{
			String suggestion = suggestions.getString(textColumnIndex);
			DisplayItem item = new DisplayItem(suggestion);
			item.tag = ITEM_RECENT_SEARCH;
			group.childItems.add(item);
		}
		suggestions.close();
		 ArrayList<DisplayGroupItem> result = new ArrayList<DisplayGroupItem>();
		 result.add(group);
		return result;
	}
	
	 // This is copied from Android's core/java/android/app/SearchManager.java.
    // 	TODO: Use SearchManager.getSuggestions() once it's available
	 /**
     * Gets a cursor with search suggestions.
     *
     * @param searchable Information about how to get the suggestions.
     * @param query The search text entered (so far).
     * @param limit The query limit to pass to the suggestion provider. This is advisory,
     *        the returned cursor may contain more rows. Pass {@code -1} for no limit.
     * @return a cursor with suggestions, or <code>null</null> the suggestion query failed.
     *
     * @hide because SearchableInfo is not part of the API.
     */
    private Cursor getSuggestions(SearchableInfo searchable, String query, int limit) {
        if (searchable == null) {
            return null;
        }

        String authority = searchable.getSuggestAuthority();
        if (authority == null) {
            return null;
        }

        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(authority)
                .query("")  // TODO: Remove, workaround for a bug in Uri.writeToParcel()
                .fragment("");  // TODO: Remove, workaround for a bug in Uri.writeToParcel()

        // if content path provided, insert it now
        final String contentPath = searchable.getSuggestPath();
        if (contentPath != null) {
            uriBuilder.appendEncodedPath(contentPath);
        }

        // append standard suggestion query path
        uriBuilder.appendPath(SearchManager.SUGGEST_URI_PATH_QUERY);

        // get the query selection, may be null
        String selection = searchable.getSuggestSelection();
        // inject query, either as selection args or inline
        String[] selArgs = null;
        if (selection != null) {    // use selection if provided
            selArgs = new String[] { query };
        } else {                    // no selection, use REST pattern
            uriBuilder.appendPath(query);
        }

        if (limit > 0) {
            uriBuilder.appendQueryParameter(SearchManager.SUGGEST_PARAMETER_LIMIT, String.valueOf(limit));
        }

        Uri uri = uriBuilder.build();

        // finally, make the query
        return getContentResolver().query(uri, null, selection, selArgs, null);
    }
    
    // adopted from 
    private void deleteSuggestion(String name)
    {
    	String contentUri = "content://" + SearchSuggestionProvider.AUTHORITY + "/suggestions";
    	Uri uri = Uri.parse(contentUri);
    	getContentResolver().delete(uri, "display1=?", new String[] {name});
    }
    private void doSearch(String query)
    {
    	if(query.startsWith(".."))
    	{
    		final String param = query.substring(2);
    		if(param.equals("startprof"))
    		{
    			UI.runOnRenderThreadAsync(new Runnable() {
					@Override
					public void run() 
					{
		    			Map<String, File> externalStorage = ExternalStorage.getAllStorageLocations();
		    			String root = externalStorage.get(ExternalStorage.SD_CARD).getAbsolutePath();
		    			UI.getTEView().renderer.teStartProfiling(root+"/com.skyline.terraexplorer/gmon.out");						
					}
				});

    		}
    		if(param.equals("endprof"))
    		{
	    		UI.runOnRenderThreadAsync(new Runnable() {
					@Override
					public void run() {
						UI.getTEView().renderer.teEndProfiling();						
					}
				});
    		}
    		else
    		{
	    		UI.runOnRenderThreadAsync(new Runnable() {
					@Override
					public void run() {
			    		ISGWorld.getInstance().SetParam(8345, param);
					}
				});
    		}
    		return;
    	}
    	tableView.requestFocus();
    	// hide keyboard
    	InputMethodManager inputManager = (InputMethodManager) this
                .getSystemService(Context.INPUT_METHOD_SERVICE);    
    	inputManager.hideSoftInputFromWindow(tableView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    	
    	webSearchResults = new DisplayGroupItem(R.string.search_webresults);
    	if(SearchWeb.isEnabled()) 
    	{
    		webSearchResults.childItems.add(new DisplayItem(R.string.search_searching,0,DisplayItem.ProgressIcon));
    		dataSource.setDataItems(new DisplayGroupItem[] { webSearchResults });
    		doWebSearchAsync(query);
    	}
    	
    	teSearchResults = new DisplayGroupItem("" /*R.string.search_teresults*/);
    	teSearchResults.childItems.add(new DisplayItem(R.string.search_searching,0,DisplayItem.ProgressIcon));
    	doTESearchAsync(query);
    }
	private void doTESearchAsync(String query) {
		if(teSearchTask != null)
			teSearchTask.cancel(true);
		teSearchTask = new TESearchAsyncTask();
		teSearchTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, query);
	}
	private void doWebSearchAsync(String query)
	{
		if(webSearchTask != null)
			webSearchTask.cancel(true);
		webSearchTask = new WebSearchAsyncTask();
		webSearchTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, query);
	}
	
	private DisplayItem addToGroupFiltered(DisplayGroupItem group,
			String name, String query) {
	    if(name == null)
	        return null;
	    int location = name.toLowerCase().indexOf(query.toLowerCase());
	    if(location == -1)
	    	return null;
	    
    	StringBuilder sb = new StringBuilder(name);
    	sb.insert(location + query.length(), "</b>");
    	sb.insert(location, "<b>");
    	DisplayItem item = new DisplayItem();
    	item.attributedName = Html.fromHtml(sb.toString());
    	group.childItems.add(item);
	    return item;
	}

	private void didSelectRowAtIndexPath(long packedPosition)
	{
		DisplayItem item = dataSource.getItemForPath(packedPosition);
		// perform search on term
		if(item.tag == ITEM_RECENT_SEARCH)
		{
			doSearch(item.name);
		}
		else
		{
			flyTo(item,ActionCode.AC_FLYTO);
		}
	}
	private void accessoryButtonTappedForRowWithIndexPath(long packedPosition) 
	{
		DisplayItem item = dataSource.getItemForPath(packedPosition);
		if(item.accessoryIcon == R.drawable.favorits) // remove from favorites
		{
			item.accessoryIcon = R.drawable.favorits_checkbox;
			FavoriteItem favItem = FavoritesStorage.defaultStorage.getItem(item.id);
			FavoritesStorage.defaultStorage.deleteItem(favItem.id);
			item.id = String.format(Locale.US, "%fx%f", favItem.position.getX(),favItem.position.getY());
			item.tag = ITEM_SEARCH_RESULT;
		}
		else // add to favorites
		{
			item.accessoryIcon = R.drawable.favorits;
			FavoriteItem favItem = new FavoriteItem();
			if(item.attributedName != null)
				favItem.name = item.attributedName.toString();
			else
				favItem.name = item.name;
			favItem.position = getPositionFromSearchResult(item);
			favItem.desc = item.subTitle;
			FavoritesStorage.defaultStorage.saveItem(favItem);
			item.id = favItem.id;
			item.tag = ITEM_FAVORITE;
			ToolManager.INSTANCE.openTool(EditFavoriteTool.class.getName(), favItem.id);
		}
		dataSource.reloadData();
	}
	
	private ContextMenuEntry[] contexMenuForPath(long packedPosition) {
		DisplayItem item = dataSource.getItemForPath(packedPosition);
		switch(item.tag)
		{
			case ITEM_RECENT_SEARCH:
				return new ContextMenuEntry[] { 
					new ContextMenuEntry(R.drawable.search, 4),
					new ContextMenuEntry(R.drawable.delete, 0),
					};
			case ITEM_FAVORITE:
			case ITEM_LOCATION:
			case ITEM_SEARCH_RESULT:
				return new ContextMenuEntry[] { 
					new ContextMenuEntry(R.drawable.fly_to, 1),
					new ContextMenuEntry(R.drawable.jump_to, 2),
					};
			case ITEM_PRESENTATION:
				return new ContextMenuEntry[] { 
					new ContextMenuEntry(R.drawable.play, 3),
					};
		}
		return null;
	}
	private void contextMenuItemTapped(int menuId, long packedPosition) {
		DisplayItem item = dataSource.getItemForPath(packedPosition);
		switch(menuId)
		{
			case 0: // delete recent search
			{
				deleteSuggestion(item.name);
				this.dataSource.setDataItems(this.recentSearchesFiltered(searchView.getQuery().toString()));
				break;
			}
			case 1: //fly to
			{
				flyTo(item, ActionCode.AC_FLYTO);
				break;
			}
			case 2: //jump to
			{
				flyTo(item, ActionCode.AC_JUMP);
				break;
			}
			case 3: //play
			{
				flyTo(item, ActionCode.AC_JUMP);
				break;
			}
			case 4: //do search
			{
				doSearch(item.name);
				break;
			}
		}
	}


	private void flyTo(final DisplayItem item,final int pattern)
	{
		UI.runOnRenderThreadAsync(new Runnable() {			
			@Override
			public void run() {
				switch(item.tag)
				{
					case ITEM_FAVORITE:
					{
						FavoriteItem favorite = FavoritesStorage.defaultStorage.getItem(item.id);
						ISGWorld.getInstance().getNavigate().FlyTo(favorite.position, pattern);
						break;
					}
					case ITEM_LOCATION:
					{
						ISGWorld.getInstance().getNavigate().FlyTo(item.id, pattern);
						break;
					}
					case ITEM_SEARCH_RESULT:
					{
						IPosition pos = getPositionFromSearchResult(item);
						ISGWorld.getInstance().getNavigate().FlyTo(pos, pattern);
						break;
					}
					case ITEM_LAYER:
					{
						ISGWorld.getInstance().getNavigate().FlyTo(item.id, pattern);
						break;				
					}
					case ITEM_PRESENTATION:
					{
						ITerraExplorerObject object = ISGWorld.getInstance().getCreator().GetObject(item.id);
						IPresentation presentation = object.CastTo(IPresentation.class);
						presentation.Stop();
						presentation.Play(0);
						break;
					}
				}
			}
		});
		UI.popToMainActivity();
	}
	
	private IPosition getPositionFromSearchResult(final DisplayItem item)
	{
		return UI.runOnRenderThread(new Callable<IPosition>() {

			@Override
			public IPosition call() throws Exception {
				String[] latlon = item.id.split("x");
				return ISGWorld.getInstance().getCreator().CreatePosition(Double.parseDouble(latlon[0]), Double.parseDouble(latlon[1]), 0, AltitudeTypeCode.ATC_ON_TERRAIN, 0, -75, 0, 5000);
			}
		});
	}


}
