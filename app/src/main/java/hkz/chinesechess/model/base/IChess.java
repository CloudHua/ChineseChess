package hkz.chinesechess.model.base;

import android.graphics.Point;

import java.util.List;

/**
 * Created by wind on 2016/1/14.
 */
public interface IChess {

    interface OnChessMoveListener {
        void onChessMove(IChess chess, Point from, Point to);
    }

    int getType();

    Point getPoint();

    boolean canReach(Point point);

    List<Point> getReachablePoint();

    boolean moveTo(Point point);

    void trackMovement(OnChessMoveListener listener);

    void stopTrack(OnChessMoveListener listener);

}


