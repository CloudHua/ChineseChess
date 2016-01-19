package hkz.chinesechess.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.ui.IChessBoardView;

/**
 * Created by wind on 2016/1/19.
 */
public class ChessBoardView extends ViewGroup implements IChessBoardView {

    private Map<IChess, View> mChesses = new HashMap<>();

    private int mWidth, mHeight;

    private ViewDragHelper mViewDragHelper;

    private ChessBoardViewCallback mCallback;

    public ChessBoardView(Context context, int width, int height, ChessBoardViewCallback callback) {
        super(context);
        mWidth = width;
        mHeight = height;
        mCallback = callback;
        init();
    }


    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1f, new DragCallback());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

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
        return super.onTouchEvent(event);
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
        View view = getChessView(chess);
        Point p = toViewPoint(to);
        view.animate().x(p.x).y(p.y).start();
    }

    @Override
    public Point toViewPoint(Point point) {
        return null;
    }

    @Override
    public Point toChessPoint(Point point) {
        return null;
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
        ImageView imageView = new ImageView(getContext());
        int chessWidth = 0;
        Point p = toViewPoint(chess.getPoint());
        imageView.setLayoutParams(new LayoutParams(p.x, p.y, chessWidth, chessWidth));
        //TODO
        imageView.setTag(chess);
        return imageView;
    }

    private class DragCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mChesses.containsValue(child) && mCallback.canDragChess(getChessFromView(child), child);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            capturedChild.animate().scaleX(1.1f).scaleY(1.1f).translationZ(3).start();
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            releasedChild.animate().scaleX(1).scaleY(1).translationZ(1).start();
            Point chessPoint = toChessPoint(new Point((int) releasedChild.getX(), (int) releasedChild.getY()));
            if (mCallback.canDropChess(getChessFromView(releasedChild), releasedChild, chessPoint)) {
                moveChess(getChessFromView(releasedChild), chessPoint, chessPoint);
            } else {
                moveChess(getChessFromView(releasedChild), chessPoint, getChessFromView(releasedChild).getPoint());
            }
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - child.getHeight();
            final int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - child.getWidth();
            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return getWidth() - child.getWidth();
        }

        @Override
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
