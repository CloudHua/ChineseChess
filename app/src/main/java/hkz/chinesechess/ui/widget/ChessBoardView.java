package hkz.chinesechess.ui.widget;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.ViewDragHelper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.model.util.ChessUtil;
import hkz.chinesechess.ui.IChessBoardView;

/**
 * Created by wind on 2016/1/19.
 */
public class ChessBoardView extends ViewGroup implements IChessBoardView {

    private Map<IChess, View> mChesses = new HashMap<>();

    private int mWidth, mHeight;

    private final int width = 8;
    private final int height = 9;

    private final int piece;
    private final int padding;

    private ViewDragHelper mViewDragHelper;

    private Callback mCallback;

    public ChessBoardView(Context context, Callback callback) {
        super(context);
        mCallback = callback;
        mViewDragHelper = ViewDragHelper.create(this, 1f, new DragCallback());
        piece = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        padding = piece;
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        mWidth = params.width - padding * 2;
        mHeight = params.height - padding * 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = width - padding * 2;
        mHeight = height - padding * 2;
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        for (int i = 0; i <= width; i++) {
            canvas.drawLine(i * mWidth / width + padding, padding, i * mWidth / width + padding, mHeight + padding, paint);
        }
        for (int i = 0; i <= height; i++) {
            canvas.drawLine(padding, i * mHeight / height + padding, mWidth + padding, i * mHeight / height + padding, paint);
        }
        super.dispatchDraw(canvas);
    }

    @Override
    public void addChess(IChess chess) {
        View view = createChessView(chess);
        mChesses.put(chess, view);
        addView(view);
    }

    @Override
    public void removeChess(IChess chess) {
        removeView(getChessView(chess));
        mChesses.remove(chess);
    }

    @Override
    public void moveChess(IChess chess, Point from, Point to) {
        moveChess(chess, from, to, false);
    }

    private void moveChess(IChess chess, Point from, Point to, boolean callCallback) {
        final View view = getChessView(chess);
        final Point toViewPoint = toViewPoint(to);
        final Point fromViewPoint = new Point(view.getLeft(), view.getTop());
        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator<Point>() {
            Point point = new Point();

            @Override
            public Point evaluate(float fraction, Point startValue, Point endValue) {
                int x = (int) (startValue.x + (fraction * (endValue.x - startValue.x)));
                int y = (int) (startValue.y + (fraction * (endValue.y - startValue.y)));
                point.set(x, y);
                return point;
            }
        }, fromViewPoint, toViewPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LayoutParams params = (LayoutParams) view.getLayoutParams();
                params.x = ((Point) animation.getAnimatedValue()).x;
                params.y = ((Point) animation.getAnimatedValue()).y;
                view.setLayoutParams(params);
            }
        });
        if (callCallback && !from.equals(to)) {
            mCallback.onChessMoved(chess, from, to);
        }
        animator.start();
    }

    @Override
    public Point toViewPoint(Point point) {
        Point out = new Point();
        out.x = point.x * (mWidth / width);
        out.y = point.y * (mHeight / height);
        out.offset(-piece / 2 + padding, -piece / 2 + padding);
        return out;
    }

    @Override
    public Point toChessPoint(Point point) {
        point.offset(piece / 2 - padding, piece / 2 - padding);
        int x = Math.round((float) point.x / (mWidth / width));
        int y = Math.round((float) point.y / (mHeight / height));
        x = Math.min(Math.max(x, 0), width);
        y = Math.min(Math.max(y, 0), height);
        return new Point(x, y);
    }


    @Override
    public View getChessView(IChess chess) {
        return mChesses.get(chess);
    }

    private IChess getChessFromView(View view) {
        return (IChess) view.getTag();
    }

    @Override
    public View createChessView(IChess chess) {
        TextView chessView = new TextView(getContext());
        Point p = toViewPoint(chess.getPoint());
        chessView.setLayoutParams(new LayoutParams(p.x, p.y, piece, piece));
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(80);
        drawable.setSize(160, 160);
        drawable.setStroke(2, ChessUtil.getChessColor(chess));
        chessView.setBackgroundDrawable(drawable);
        chessView.setText(ChessUtil.getChessName(chess));
        chessView.setGravity(Gravity.CENTER);
        chessView.setTextColor(ChessUtil.getChessColor(chess));
        chessView.setTag(chess);
        return chessView;
    }

    private class DragCallback extends ViewDragHelper.Callback {

        public boolean tryCaptureView(View child, int pointerId) {
            return mChesses.containsValue(child) && mCallback.canDragChess(getChessFromView(child), child);
        }

        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            LayoutParams params = (LayoutParams) changedView.getLayoutParams();
            params.x = left;
            params.y = top;
            changedView.setLayoutParams(params);
        }

        public void onViewCaptured(View capturedChild, int activePointerId) {
            capturedChild.animate().scaleX(1.1f).scaleY(1.1f).start();
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            releasedChild.animate().scaleX(1).scaleY(1).start();
            IChess chess = getChessFromView(releasedChild);
            Point chessPoint = toChessPoint(new Point(releasedChild.getLeft(), releasedChild.getTop()));
            if (mCallback.canDropChess(chess, releasedChild, chessPoint)) {
                moveChess(chess, chess.getPoint(), chessPoint, true);
            } else {
                moveChess(chess, chessPoint, chess.getPoint(), false);
            }
        }

        public int clampViewPositionVertical(View child, int top, int dy) {
            final int bottomBound = getHeight() - child.getHeight();
            final int newTop = Math.min(Math.max(top, 0), bottomBound);
            return newTop;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            final int rightBound = getWidth() - child.getWidth();
            final int newLeft = Math.min(Math.max(left, 0), rightBound);
            return newLeft;
        }

        public int getViewHorizontalDragRange(View child) {
            return getWidth() - child.getWidth();
        }

        public int getViewVerticalDragRange(View child) {
            return getHeight() - child.getHeight();
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public int x;
        public int y;

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int x, int y, int width, int height) {
            super(width, height);
            this.x = x;
            this.y = y;
        }
    }
}
