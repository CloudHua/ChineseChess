package hkz.chinesechess.model.chess;

import android.graphics.Point;

import hkz.chinesechess.model.base.IChessBoard;

/**
 * Created by Administrator on 2016/1/19.
 */
public class Bing extends BaseChess {
    Point InitPoint;
    public Bing(int chessType, Point chessPoint, IChessBoard chessBoard) {
        super(chessType, chessPoint, chessBoard);
        InitPoint=chessPoint;
    }
    @Override
    public boolean canReach(Point point) {
        if (!chessBoard.getSize().contains(point.x, point.y) ||point.equals(chessPoint)) {
            return false;
        }
        if(InitPoint.y==6&&point.x==InitPoint.x&&InitPoint.y>point.y&&(chessPoint.y-point.y==1)){
            if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                return true;
            }
            if (!chessBoard.isChessHere(point)) {
                return true;
            }
        }
        if((InitPoint.y==6&&(chessPoint.y-InitPoint.y)>1)&&((Math.abs(point.x - chessPoint.x) == 1) || ((chessPoint.y - point.y) == 1))) {
            if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                return true;
            }
            if (!chessBoard.isChessHere(point)) {
                return true;
            }
        }
        if(InitPoint.y==3&&point.x==InitPoint.x&&InitPoint.y<point.y&&(point.y-chessPoint.y==1)){
            if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                return true;
            }
            if (!chessBoard.isChessHere(point)) {
                return true;
            }
        }
        if((InitPoint.y==3&&Math.abs(chessPoint.y-InitPoint.y)>1)&&((Math.abs(point.x - chessPoint.x) == 1) || ((point.y - chessPoint.y) == 1))){
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
