package etong.instagram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2014/9/17.
 */
public class CardLayout extends RelativeLayout {

    private int left,top,right,bottom;

    private int white = 0xffffffff;

    private int strokeColor = 0xFFE4E4E4;

    public CardLayout(Context context) {
        super(context);
    }

    public CardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    public void init(){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setBounds(0, 100, getWidth(), getHeight() - 100);
        drawable.setStroke(1, strokeColor);
        drawable.setColor(white);
        drawable.setCornerRadius(2);
        setBackgroundCompat(drawable);
        drawable.setBounds(0, 100, getWidth(), getHeight() - 100);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * Set the View's background. Masks the API changes made in Jelly Bean.
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public void setBackgroundCompat(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
    }
}
