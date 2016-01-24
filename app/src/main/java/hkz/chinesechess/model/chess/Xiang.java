package hkz.chinesechess.model.chess;

import android.graphics.Point;

import hkz.chinesechess.model.base.IChessBoard;

/**
 * Created by Administrator on 2016/1/23.
 */
public class Xiang extends BaseChess {
    Point InitPoint;
    public Xiang(int chessType, Point chessPoint, IChessBoard chessBoard) {
        super(chessType, chessPoint, chessBoard);
        InitPoint=chessPoint;
    }

    @Override
    public boolean canReach(Point point) {
        Point TempPoint1=chessPoint,TempPoint2=chessPoint;
        if(!chessBoard.getSize().contains(point.x, point.y) || point.equals(chessPoint)) {
            return false;
        }
        if(Math.abs(point.y-InitPoint.y)>4){
            return false;
        }
        if(point.x-chessPoint.x==2&&point.y-chessPoint.y==2){
            TempPoint1.x=chessPoint.x+1;
            TempPoint1.y=chessPoint.y+1;
            TempPoint2.x=chessPoint.x+2;
            TempPoint2.y=chessPoint.y+2;
            if(chessBoard.isChessHere(TempPoint1)){
                return false;
            }else {
                if(chessBoard.isChessHere(TempPoint2)&&chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()){
                    return true;
                }
                if(!chessBoard.isChessHere(TempPoint2)){
                    return true;
                }
            }
        }
        if(point.x-chessPoint.x==2&&chessPoint.y-point.y==2){
            TempPoint1.x=chessPoint.x+1;
            TempPoint1.y=chessPoint.y-1;
            TempPoint2.x=chessPoint.x+2;
            TempPoint2.y=chessPoint.y-2;
            if(chessBoard.isChessHere(TempPoint1)){
                return false;
            }else {
                if(chessBoard.isChessHere(TempPoint2)&&chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()){
                    return true;
                }
                if(!chessBoard.isChessHere(TempPoint2)){
                    return true;
                }
            }
        }
        if(chessPoint.x-point.x==2&&point.y-chessPoint.y==2){
            TempPoint1.x=chessPoint.x-1;
            TempPoint1.y=chessPoint.y+1;
            TempPoint2.x=chessPoint.x-2;
            TempPoint2.y=chessPoint.y+2;
            if(chessBoard.isChessHere(TempPoint1)){
                return false;
            }else {
                if(chessBoard.isChessHere(TempPoint2)&&chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()){
                    return true;
                }
                if(!chessBoard.isChessHere(TempPoint2)){
                    return true;
                }
            }
        }
        if(chessPoint.x-point.x==2&&chessPoint.y-point.y==2){
            TempPoint1.x=chessPoint.x-1;
            TempPoint1.y=chessPoint.y-1;
            TempPoint2.x=chessPoint.x-2;
            TempPoint2.y=chessPoint.y-2;
            if(chessBoard.isChessHere(TempPoint1)){
                return false;
            }else {
                if(chessBoard.isChessHere(TempPoint2)&&chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType()){
                    return true;
                }
                if(!chessBoard.isChessHere(TempPoint2)){
                    return true;
                }
            }
        }
        return false;
    }
}
