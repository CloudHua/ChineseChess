package hkz.chinesechess.model.chess;

import android.graphics.Point;

import java.util.List;

import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.model.base.IChessBoard;
import hkz.chinesechess.model.base.IPlayer;

/**
 * Created by Administrator on 2016/1/15.
 */
public class BaseChess implements IChess{

    int chessType;
    Point chessPoint;
    IChessBoard chessBoard;

    public BaseChess(int chessType,Point chessPoint,IChessBoard chessBoard){
        this.chessType=chessType;
        this.chessPoint=new Point(chessPoint);
        this.chessBoard=chessBoard;
    }

    @Override
    public int getType() {
        return chessType;
    }

    @Override
    public Point getPoint() {
        return chessPoint;
    }

    @Override
    public boolean canReach(Point point) {
        if (chessBoard.getSize().contains(point.x, point.y) && !point.equals(chessPoint)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Point> getReachablePoint() {
        return null;
    }

    @Override
    public boolean moveTo(Point point) {
        if (canReach(point)) {
            chessPoint.x=point.x;
            chessPoint.y=point.y;
            return true;
        } else
            return false;
    }

    @Override
    public void trackMovement(OnChessMoveListener listener) {

    }

    @Override
    public void stopTrack(OnChessMoveListener listener) {

    }
}
