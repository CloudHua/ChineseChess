package hkz.chinesechess.model.chess;

import android.graphics.Point;

import hkz.chinesechess.model.base.IChessBoard;

/**
 * Created by Administrator on 2016/1/20.
 */
public class Jiang extends BaseChess {
    Point InitPoint;
    public Jiang(int chessType, Point chessPoint, IChessBoard chessBoard) {
        super(chessType, chessPoint, chessBoard);
        InitPoint=chessPoint;
    }

    @Override
    public boolean canReach(Point point) {
        if (!chessBoard.getSize().contains(point.x, point.y) ||point.equals(chessPoint)) {
            return false;
        }
        if(((Math.abs(point.x-InitPoint.x)>1)||(Math.abs(point.y-InitPoint.y)>2))){
            return false;
        }
        if(point.x==chessPoint.x&&Math.abs(point.y-chessPoint.y)==1) {
            if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                return true;
            }
            if (!chessBoard.isChessHere(point)) {
                return true;
            }
        }
        if(point.y==chessPoint.y&&Math.abs(point.x-chessPoint.x)==1) {
            if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                return true;
            }
            if (!chessBoard.isChessHere(point)) {
                return true;
            }
        }
        return false;
    }
}
