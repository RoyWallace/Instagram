package etong.instagram;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Administrator on 14-8-11.
 */
public class CircleCanvasLayout extends RelativeLayout {

    /**
     * 画笔
     */
    private Paint paint;

    /**
     * 颜色black
     */
    public final static int black = 0x70000000;//黑色

    /**
     * 颜色white
     */
    public final static int white = 0xddffffff;//白色

    public int paintColor;

    /**
     * 颜色orange
     */
    public int orange;

    /**
     * 圆圈初始半径
     */
    private float minRadius;

    private float maxRadius;

    /**
     * 圆圈半径
     */
    private float radius;

    /**
     * 圈圈圆心x轴坐标
     */
    private float cx;

    /**
     * 圈圈圆心y轴坐标
     */
    private float cy;

    /**
     * 扩散量单位
     */
    public float increase;

    /**
     * 动画是否可以启用
     */
    private boolean animAble = false;

    /**
     * 屏幕分辨率密度比
     */
    private float density;

    /**
     * 缩小动画的时间
     */
    private long outDuration = 300;

    /**
     * 放大动画执行时间
     */
    private long inDuration = 500;

    private boolean drawAtBack = true;

    private Animator.AnimatorListener zoomInListener;

    private Animator.AnimatorListener zoomOutListener;

    public Animator.AnimatorListener getZoomInListener() {
        return zoomInListener;
    }

    public void setZoomInListener(Animator.AnimatorListener zoomInListener) {
        this.zoomInListener = zoomInListener;
    }

    public Animator.AnimatorListener getZoomOutListener() {
        return zoomOutListener;
    }

    public void setZoomOutListener(Animator.AnimatorListener zoomOutListener) {
        this.zoomOutListener = zoomOutListener;
    }

    public boolean isDrawAtBack() {
        return drawAtBack;
    }

    public void setDrawAtBack(boolean drawAtBack) {
        this.drawAtBack = drawAtBack;
    }

    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public long getOutDuration() {
        return outDuration;
    }

    public void setOutDuration(long outDuration) {
        this.outDuration = outDuration;
    }

    public long getInDuration() {
        return inDuration;
    }

    public void setInDuration(long inDuration) {
        this.inDuration = inDuration;
    }

    public float getMinRadius() {
        return minRadius;
    }

    public void setMinRadius(float minRadius) {
        this.minRadius = minRadius;
    }

    public CircleCanvasLayout(Context context) {
        super(context);
        init();
    }

    public CircleCanvasLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleCanvasLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {

        setWillNotDraw(false);

        density = getResources().getDisplayMetrics().density;

        paint = new Paint();
        paint.setColor(paintColor);
        paint.setAntiAlias(true);//抗锯齿

    }

    public void setPosition(int x, int y) {
        this.cx = x;
        this.cy = y;
    }

    /**
     * 设置画笔颜色
     *
     * @param color
     */
    public void setPaintColor(int color) {
        paint.setColor(color);
    }

    /**
     * 获取画笔颜色
     *
     * @return
     */
    public int getPaintColor() {
        return paint.getColor();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        //设置圈圈最大半径
        maxRadius = getDiagonal();
        //确保view必须数据初始化完成，然后才开始动画
        animAble = true;
    }

    @Override
    public void draw(Canvas canvas) {
        if (drawAtBack) {
            if (!isInEditMode()) {
                canvas.drawCircle(cx, cy, radius, paint);
            }
            super.draw(canvas);

        }else{
            super.draw(canvas);
            if (!isInEditMode()) {
                canvas.drawCircle(cx, cy, radius, paint);
            }
        }

    }

    /**
     * 随机产生圆点x轴坐标
     *
     * @return
     */
    private int randomCx() {
        return randomCenter(getWidth());
    }

    /**
     * 随机产生圆点x轴坐标
     *
     * @return
     */
    private int randomCy() {
        return randomCenter(getHeight());
    }

    /**
     * 随机产生圆点坐标
     *
     * @return
     */
    private int randomCenter(int scope) {
        return (int) (Math.random() * scope);
    }

    /**
     * 放大
     */
    public void ZoomIn() {
//        inDuration = 5000;
//        ValueAnimator colorAnimator = ValueAnimator.ofInt(0, 255);
//        colorAnimator.setDuration(inDuration);
//        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                Integer value = (Integer) valueAnimator.getAnimatedValue();
//                paint.setColor(Color.argb(255,255,255-value,255-value));
//                invalidate();
//            }
//        });
//
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(minRadius, maxRadius);
        valueAnimator.setDuration(inDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float value = (Float) valueAnimator.getAnimatedValue();
                radius = minRadius + value;
                invalidate();
            }
        });
        valueAnimator.start();
        if (zoomInListener != null)
            valueAnimator.addListener(zoomInListener);
    }


    /**
     * 缩小
     */
    public void ZoomOut() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(minRadius, maxRadius);
        valueAnimator.setDuration(outDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float value = (Float) valueAnimator.getAnimatedValue();
                radius = maxRadius - value;
                invalidate();
            }
        });
        valueAnimator.start();
        if (zoomOutListener != null)
            valueAnimator.addListener(zoomOutListener);
    }

    public void starFall() {

    }

    /**
     * 自定义圈圈半径
     *
     * @param radius
     */
    public void setMaxRadius(int radius) {
        this.maxRadius = radius;
    }

    /**
     * 获取圈圈最大半径
     *
     * @return
     */
    public float getMaxRadius() {
        return maxRadius;
    }

    /**
     * 计算画布对角线长度
     *
     * @return
     */
    public float getDiagonal() {
        return (float) Math.sqrt(Math.pow((double) getHeight(), 2) + Math.pow((double) getWidth(), 2));
    }

    public void reset(){
        radius = 0;
        postInvalidateDelayed(10000);
    }


}
