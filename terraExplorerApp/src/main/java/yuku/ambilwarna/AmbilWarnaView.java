package yuku.ambilwarna;

import com.skyline.terraexplorer.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AmbilWarnaView {

	private final boolean supportsAlpha;
	final View viewHue;
	final AmbilWarnaSquare viewSatVal;
	final ImageView viewCursor;
	final ImageView viewAlphaCursor;
	final View viewOldColor;
	final View viewNewColor;
	final View viewAlphaOverlay;
	final ImageView viewTarget;
	final ImageView viewAlphaCheckered;
	final ViewGroup viewContainer;
	final float[] currentColorHsv = new float[3];
	int alpha;
	private View rootView;

	/**
	 * Create an AmbilWarnaDialog.
	 *
	 * @param context activity context
	 * @param color current color
	 * @param listener an OnAmbilWarnaListener, allowing you to get back error or OK
	 */
	public AmbilWarnaView(final Context context, int color) {
		this(context, color, false);
	}

	/**
	 * Create an AmbilWarnaDialog.
	 *
	 * @param context activity context
	 * @param color current color
	 * @param supportsAlpha whether alpha/transparency controls are enabled
	 * @param listener an OnAmbilWarnaListener, allowing you to get back error or OK
	 */
	public AmbilWarnaView(final Context context, int color, boolean supportsAlpha) {
		this.supportsAlpha = supportsAlpha;

		if (!supportsAlpha) { // remove alpha if not supported
			color = color | 0xff000000;
		}

		Color.colorToHSV(color, currentColorHsv);
		alpha = Color.alpha(color);

		rootView = LayoutInflater.from(context).inflate(R.layout.ambilwarna_dialog, null);
		viewHue = rootView.findViewById(R.id.ambilwarna_viewHue);
		viewSatVal = (AmbilWarnaSquare) rootView.findViewById(R.id.ambilwarna_viewSatBri);
		viewCursor = (ImageView) rootView.findViewById(R.id.ambilwarna_cursor);
		viewOldColor = rootView.findViewById(R.id.ambilwarna_oldColor);
		viewNewColor = rootView.findViewById(R.id.ambilwarna_newColor);
		viewTarget = (ImageView) rootView.findViewById(R.id.ambilwarna_target);
		viewContainer = (ViewGroup) rootView.findViewById(R.id.ambilwarna_viewContainer);
		viewAlphaOverlay = rootView.findViewById(R.id.ambilwarna_overlay);
		viewAlphaCursor = (ImageView) rootView.findViewById(R.id.ambilwarna_alphaCursor);
		viewAlphaCheckered = (ImageView) rootView.findViewById(R.id.ambilwarna_alphaCheckered);
		
		rootView.findViewById(R.id.ambilwarna_state).setVisibility(View.GONE);
		

		{ // hide/show alpha
			viewAlphaOverlay.setVisibility(supportsAlpha? View.VISIBLE: View.GONE);
			viewAlphaCursor.setVisibility(supportsAlpha? View.VISIBLE: View.GONE);
			viewAlphaCheckered.setVisibility(supportsAlpha? View.VISIBLE: View.GONE);
		}

		viewSatVal.setHue(getHue());
		viewOldColor.setBackgroundColor(color);
		viewNewColor.setBackgroundColor(color);

		viewHue.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE
				|| event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_UP) {

					float y = event.getY();
					if (y < 0.f) y = 0.f;
					if (y > viewHue.getMeasuredHeight()) {
						y = viewHue.getMeasuredHeight() - 0.001f; // to avoid jumping the cursor from bottom to top.
					}
					float hue = 360.f - 360.f / viewHue.getMeasuredHeight() * y;
					if (hue == 360.f) hue = 0.f;
					setHue(hue);

					// update view
					viewSatVal.setHue(getHue());
					moveCursor();
					viewNewColor.setBackgroundColor(getColor());
					updateAlphaView();
					return true;
				}
				return false;
			}
		});

		if (supportsAlpha) viewAlphaCheckered.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if ((event.getAction() == MotionEvent.ACTION_MOVE)
				|| (event.getAction() == MotionEvent.ACTION_DOWN)
				|| (event.getAction() == MotionEvent.ACTION_UP)) {

					float y = event.getY();
					if (y < 0.f) {
						y = 0.f;
					}
					if (y > viewAlphaCheckered.getMeasuredHeight()) {
						y = viewAlphaCheckered.getMeasuredHeight() - 0.001f; // to avoid jumping the cursor from bottom to top.
					}
					final int a = Math.round(255.f - ((255.f / viewAlphaCheckered.getMeasuredHeight()) * y));
					AmbilWarnaView.this.setAlpha(a);

					// update view
					moveAlphaCursor();
					int col = AmbilWarnaView.this.getColor();
					int c = a << 24 | col & 0x00ffffff;
					viewNewColor.setBackgroundColor(c);
					return true;
				}
				return false;
			}
		});
		viewSatVal.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE
				|| event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_UP) {

					float x = event.getX(); // touch event are in dp units.
					float y = event.getY();

					if (x < 0.f) x = 0.f;
					if (x > viewSatVal.getMeasuredWidth()) x = viewSatVal.getMeasuredWidth();
					if (y < 0.f) y = 0.f;
					if (y > viewSatVal.getMeasuredHeight()) y = viewSatVal.getMeasuredHeight();

					setSat(1.f / viewSatVal.getMeasuredWidth() * x);
					setVal(1.f - (1.f / viewSatVal.getMeasuredHeight() * y));

					// update view
					moveTarget();
					viewNewColor.setBackgroundColor(getColor());

					return true;
				}
				return false;
			}
		});

		// move cursor & target on first draw
		ViewTreeObserver vto = rootView.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				moveCursor();
				if (AmbilWarnaView.this.supportsAlpha) moveAlphaCursor();
				moveTarget();
				if (AmbilWarnaView.this.supportsAlpha) updateAlphaView();
				rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}

	public View getRootView()
	{
		return rootView;
	}
	
	protected void moveCursor() {
		float y = viewHue.getMeasuredHeight() - (getHue() * viewHue.getMeasuredHeight() / 360.f);
		if (y == viewHue.getMeasuredHeight()) y = 0.f;
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewCursor.getLayoutParams();
		layoutParams.leftMargin = (int) (viewHue.getLeft() - Math.floor(viewCursor.getMeasuredWidth() / 2) - viewContainer.getPaddingLeft());
		layoutParams.topMargin = (int) (viewHue.getTop() + y - Math.floor(viewCursor.getMeasuredHeight() / 2) - viewContainer.getPaddingTop());
		viewCursor.setLayoutParams(layoutParams);
	}

	protected void moveTarget() {
		float x = getSat() * viewSatVal.getMeasuredWidth();
		float y = (1.f - getVal()) * viewSatVal.getMeasuredHeight();
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewTarget.getLayoutParams();
		layoutParams.leftMargin = (int) (viewSatVal.getLeft() + x - Math.floor(viewTarget.getMeasuredWidth() / 2) - viewContainer.getPaddingLeft());
		layoutParams.topMargin = (int) (viewSatVal.getTop() + y - Math.floor(viewTarget.getMeasuredHeight() / 2) - viewContainer.getPaddingTop());
		viewTarget.setLayoutParams(layoutParams);
	}

	protected void moveAlphaCursor() {
		final int measuredHeight = this.viewAlphaCheckered.getMeasuredHeight();
		float y = measuredHeight - ((this.getAlpha() * measuredHeight) / 255.f);
		final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.viewAlphaCursor.getLayoutParams();
		layoutParams.leftMargin = (int) (this.viewAlphaCheckered.getLeft() - Math.floor(this.viewAlphaCursor.getMeasuredWidth() / 2) - this.viewContainer.getPaddingLeft());
		layoutParams.topMargin = (int) ((this.viewAlphaCheckered.getTop() + y) - Math.floor(this.viewAlphaCursor.getMeasuredHeight() / 2) - this.viewContainer.getPaddingTop());

		this.viewAlphaCursor.setLayoutParams(layoutParams);
	}

	public int getColor() {
		final int argb = Color.HSVToColor(currentColorHsv);
		return alpha << 24 | (argb & 0x00ffffff);
	}

	private float getHue() {
		return currentColorHsv[0];
	}

	private float getAlpha() {
		return this.alpha;
	}

	private float getSat() {
		return currentColorHsv[1];
	}

	private float getVal() {
		return currentColorHsv[2];
	}

	private void setHue(float hue) {
		currentColorHsv[0] = hue;
	}

	private void setSat(float sat) {
		currentColorHsv[1] = sat;
	}

	private void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	private void setVal(float val) {
		currentColorHsv[2] = val;
	}

	private void updateAlphaView() {
		final GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
		Color.HSVToColor(currentColorHsv), 0x0
		});
		viewAlphaOverlay.setBackgroundDrawable(gd);
	}
}
