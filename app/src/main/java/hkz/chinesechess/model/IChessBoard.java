package hkz.chinesechess.model;

import android.graphics.Point;

import java.util.List;

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

    Point getSize();

    void addChess(IChess chess);

    void removeChess(IChess chess);

    boolean isChessHere(Point point);

    IChess getChessOnPoint(Point point);

    void listenChessBoard(ChessBoardListener listener);

    void unListenChessBoard(ChessBoardListener listener);

}
