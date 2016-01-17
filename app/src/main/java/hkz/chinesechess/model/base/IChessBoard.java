package hkz.chinesechess.model.base;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.List;

import hkz.chinesechess.model.base.IChess;

/**
 * Created by wind on 2016/1/14.
 */
public interface IChessBoard {

    interface ChessBoardListener {

        void onChessAdded(IChess chess);

        void onChessRemoved(IChess chess);

        void onChessMoved(IChess chess, Point from, Point to);
    }

    List<IChess> getAllChess();

    List<IChess> getChessByType(int type);

    Rect getSize();

    void addChess(IChess chess);

    void removeChess(IChess chess);
    void moveChess(IChess chess, Point from, Point to);

    boolean isChessHere(Point point);

    IChess getChessOnPoint(Point point);

    void listenChessBoard(ChessBoardListener listener);

    void unListenChessBoard(ChessBoardListener listener);

}
