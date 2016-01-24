package hkz.chinesechess.model.chess;

import android.graphics.Point;

import hkz.chinesechess.model.base.IChessBoard;

/**
 * Created by Administrator on 2016/1/23.
 */
public class Ma extends BaseChess {
    Point InitPoint;
    public Ma(int chessType, Point chessPoint, IChessBoard chessBoard) {
        super(chessType, chessPoint, chessBoard);
        InitPoint=chessPoint;
    }

    @Override
    public boolean canReach(Point point) {
        Point TempPoint=chessPoint;
        if(!chessBoard.getSize().contains(point.x, point.y) || point.equals(chessPoint)) {
            return false;
        }
        if(Math.abs(point.x-chessPoint.x)==1&&point.y-chessPoint.y==2){
            TempPoint.y=chessPoint.y+1;
            if(chessBoard.isChessHere(TempPoint)){
               return false;
            }
            else {
                if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                    return true;
                }
                if (!chessBoard.isChessHere(point)) {
                    return true;
                }
            }
        }
        if(Math.abs(point.x-chessPoint.x)==1&&chessPoint.y-point.y==2){
            TempPoint.y=chessPoint.y-1;
            if(chessBoard.isChessHere(TempPoint)){
                return false;
            }
            else {
                if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                    return true;
                }
                if (!chessBoard.isChessHere(point)) {
                    return true;
                }
            }
        }
        if(Math.abs(point.y-chessPoint.y)==1&&point.x-chessPoint.x==2){
            TempPoint.x=chessPoint.x+1;
            if(chessBoard.isChessHere(TempPoint)){
                return false;
            }
            else {
                if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                    return true;
                }
                if (!chessBoard.isChessHere(point)) {
                    return true;
                }
            }
        }
        if(Math.abs(point.y-chessPoint.y)==1&&chessPoint.x-point.x==2){
            TempPoint.x=chessPoint.x-1;
            if(chessBoard.isChessHere(TempPoint)){
                return false;
            }
            else {
                if (chessBoard.isChessHere(point) && chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()) {
                    return true;
                }
                if (!chessBoard.isChessHere(point)) {
                    return true;
                }
            }
        }
        return false;
    }
}
