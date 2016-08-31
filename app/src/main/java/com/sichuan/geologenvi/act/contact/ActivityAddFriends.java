package com.sichuan.geologenvi.act.contact;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.geodisaster.AreaSelectorAct;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.Contact;
import com.sichuan.geologenvi.views.ContactDialog;
import com.sichuan.geologenvi.views.ContactDialog2;
import com.sichuan.geologenvi.views.ContactDialog3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityAddFriends extends AppFrameAct implements SectionIndexer {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortGroupMemberAdapter adapter;
	private ClearEditText mClearEditText;

	private LinearLayout titleLayout;
	private TextView title;
	private TextView tvNofriends;
	private SqlHandler handler;
	/**
	 * 上次第一个可见元素，用于滚动时记录标识。
	 */
	private int lastFirstVisibleItem = -1;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private ArrayList<Contact> contacts;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;
	private String sqlStr="";
	private String otherStr="";
	private int type=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_friends);


		_setHeaderTitle("通讯录");
		type=getIntent().getIntExtra("Type", 0);
		initViews();
		if(type==2)
		_setRightHomeText("筛选", new View.OnClickListener(){

			@Override
			public void onClick(View view) {
				Intent intent2=new Intent(ActivityAddFriends.this, AreaSelectorAct.class);
				startActivityForResult(intent2, 0x11);
			}
		});
		handler=new SqlHandler(this);
		switch (type){
			case 0:
				sqlStr="SELECT PHONE as phone, NAME as name FROM SL_TXL_SHENG";
				contacts=handler.getPersonInfo(sqlStr);
				break;
			case 1:
				sqlStr="SELECT PHONE as phone, NAME as name FROM SL_TXL_SHI";
				contacts=handler.getPersonInfo(sqlStr);
				break;
			case 2:
				sqlStr="SELECT JCBA05A130 as phone, JCBA05A090 as name, JCBA05A180 otherinfo, NAME as addr " +
						"FROM SL_JCBA05A as a left join SL_TATTR_DZZH_XZQH as b on (a.JCBA05A040=b.CODE ) WHERE  a.JCBA05A040 is not null" +
						" and (JCBA05A090 is not null OR JCBA05A130 is not null)"+
						" union all "+
						" SELECT JCBA05A130 as phone, JCBA05A090 as name, JCBA05A180 otherinfo, NAME as addr " +
						"FROM SL_JCBA05A as a left join SL_TATTR_DZZH_XZQH as b on (a.JCBA05A030=b.CODE ) where a.JCBA05A040 is null "+
						" and (JCBA05A090 is not null OR JCBA05A130 is not null)";

				contacts=handler.getPersonInfo(sqlStr);
				break;

			case 3:
				sqlStr="SELECT BANGONGDIANHUA as phone, ZHIWU as name FROM SL_TXL_SHIGUOTUJU";
				contacts=handler.getPersonInfo(sqlStr);
				break;
			case 4:
				sqlStr="SELECT PHONE as phone, NAME as name FROM SL_TXL_SHIDHZ";
				contacts=handler.getPersonInfo(sqlStr);
				break;
			case 5:
				sqlStr="SELECT * FROM SL_TXL_DZZHFZPQFG";
				contacts=handler.getPersonInfo2(sqlStr);
				break;
			case 6:
				sqlStr="SELECT * FROM SL_TXL_SHILD";
				contacts=handler.getPersonInfo3(sqlStr);
				break;
			case 7:
				sqlStr="SELECT ZHAA01A735 as phone, ZHAA01A020 as name, ZHAA01A740 as otherinfo FROM SL_ZHAA01A WHERE ZHAA01A735 is not null OR ZHAA01A740 is not null";
				contacts=handler.getPersonInfo(sqlStr);
				break;
		}
		if(contacts.size()>0)
			setContacts();
	}

	private void initViews() {
		titleLayout = (LinearLayout) findViewById(R.id.title_layout);
		title = (TextView) this.findViewById(R.id.title_layout_catalog);
		tvNofriends = (TextView) this
				.findViewById(R.id.title_layout_no_friends);
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				if(type==5){
					ContactDialog2 contactDialog2 = new ContactDialog2(ActivityAddFriends.this, (Contact) adapter.getItem(position));
					contactDialog2.show();
				}else if(type==6){
					ContactDialog3 contactDialog3 = new ContactDialog3(ActivityAddFriends.this, (Contact) adapter.getItem(position));
					contactDialog3.show();
				}else {
					ContactDialog contactDialog = new ContactDialog(ActivityAddFriends.this, (Contact) adapter.getItem(position), "名称");
					contactDialog.show();
				}
			}
		});
	}

	private void setContacts(){
		filledData(contacts);

		// 根据a-z进行排序源数据
		Collections.sort(contacts, pinyinComparator);
		adapter = new SortGroupMemberAdapter(this, contacts);
		sortListView.setAdapter(adapter);
		sortListView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
								 int visibleItemCount, int totalItemCount) {
				int section = getSectionForPosition(firstVisibleItem);
				int nextSection = getSectionForPosition(firstVisibleItem + 1);
				int nextSecPosition = getPositionForSection(+nextSection);
				if (firstVisibleItem != lastFirstVisibleItem) {
					MarginLayoutParams params = (MarginLayoutParams) titleLayout
							.getLayoutParams();
					params.topMargin = 0;
					titleLayout.setLayoutParams(params);
					title.setText(contacts.get(
							getPositionForSection(section)).getSortLetters());
				}
				if (nextSecPosition == firstVisibleItem + 1) {
					View childView = view.getChildAt(0);
					if (childView != null) {
						int titleHeight = titleLayout.getHeight();
						int bottom = childView.getBottom();
						MarginLayoutParams params = (MarginLayoutParams) titleLayout
								.getLayoutParams();
						if (bottom < titleHeight) {
							float pushedDistance = bottom - titleHeight;
							params.topMargin = (int) pushedDistance;
							titleLayout.setLayoutParams(params);
						} else {
							if (params.topMargin != 0) {
								params.topMargin = 0;
								titleLayout.setLayoutParams(params);
							}
						}
					}
				}
				lastFirstVisibleItem = firstVisibleItem;
			}
		});
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				// 这个时候不需要挤压效果 就把他隐藏掉
				titleLayout.setVisibility(View.GONE);
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	/**
	 * 为ListView填充数据
	 *
	 * @param date
	 * @return
	 */
	private void filledData(ArrayList<Contact> datas) {

		for (int i = 0; i < datas.size(); i++) {
			Contact contact=datas.get(i);
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(contact.getName());
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				contact.setSortLetters(sortString.toUpperCase());
			} else {
				contact.setSortLetters("#");
			}
		}

	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 *
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<Contact> filterDateList = new ArrayList<>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = contacts;
			tvNofriends.setVisibility(View.GONE);
		} else {
			filterDateList.clear();
			for (Contact sortModel : contacts) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
		if (filterDateList.size() == 0) {
			tvNofriends.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return contacts.get(position).getSortLetters().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < contacts.size(); i++) {
			String sortStr = contacts.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==0x22){
			AreaInfo area= (AreaInfo) data.getSerializableExtra("Area");
			String areaCode=area.getCode();
			String typeStr=" and (JCBA05A090 is not null OR JCBA05A130 is not null)";
			if (areaCode.length() == 6)
				typeStr = typeStr + " and JCBA05A030 = '" + areaCode + "'";
			else if (areaCode.length() == 9)
				typeStr = typeStr + " and JCBA05A040 = '" + areaCode + "'";

			sqlStr="SELECT JCBA05A130 as phone, JCBA05A090 as name, JCBA05A180 otherinfo, NAME as addr" +
					" FROM SL_JCBA05A as a left join SL_TATTR_DZZH_XZQH as b on (a.JCBA05A040=b.CODE ) WHERE  a.JCBA05A040 is not null" + typeStr+
					" union all "+
					" SELECT JCBA05A130 as phone, JCBA05A090 as name, JCBA05A180 otherinfo, NAME as addr" +
					" FROM SL_JCBA05A as a left join SL_TATTR_DZZH_XZQH as b on (a.JCBA05A030=b.CODE ) where a.JCBA05A040 is null "
					+" and (JCBA05A090 is not null OR JCBA05A130 is not null)"+typeStr;

			ArrayList<Contact> filterDateList = handler.getPersonInfo(sqlStr);

			filledData(filterDateList);

			Collections.sort(filterDateList, pinyinComparator);
			adapter.updateListView(filterDateList);

		}
	}
}
