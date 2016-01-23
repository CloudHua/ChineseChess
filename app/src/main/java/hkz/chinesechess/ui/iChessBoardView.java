package hkz.chinesechess.ui;

import android.graphics.Point;
import android.view.View;

import hkz.chinesechess.model.base.IChess;

/**
 * Created by wind on 2016/1/19.
 */
public interface IChessBoardView {

    interface Callback {

        boolean canDragChess(IChess chess, View chessView);

        boolean canDropChess(IChess chess, View chessView, Point point);

        void onChessMoved(IChess chess, Point from, Point to);
    }

    void addChess(IChess chess);

    void removeChess(IChess chess);

    void moveChess(IChess chess, Point from, Point to);

    Point toViewPoint(Point point);

    Point toChessPoint(Point point);

    View getChessView(IChess chess);

    View createChessView(IChess chess);

}
