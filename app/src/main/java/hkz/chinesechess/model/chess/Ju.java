package hkz.chinesechess.model.chess;

import android.graphics.Point;

import hkz.chinesechess.model.base.IChessBoard;

/**
 * Created by Administrator on 2016/1/23.
 */
public class Ju extends BaseChess {
    Point InitPoint;
    public Ju(int chessType, Point chessPoint, IChessBoard chessBoard) {
        super(chessType, chessPoint, chessBoard);
        InitPoint=chessPoint;
    }

    @Override
    public boolean canReach(Point point) {
        int count = 0, tempy = 0,tempx=0;
        Point Tempoint = chessPoint;
        if(!chessBoard.getSize().contains(point.x, point.y) || point.equals(chessPoint)) {
            return false;
        }
        if (point.x == chessPoint.x) {
            tempy = point.y - chessPoint.y;
            if (tempy > 0) {
                for (int i = 0; i <= Math.abs(point.y - chessPoint.y); i++) {
                    Tempoint.y += 1;
                    if (chessBoard.isChessHere(Tempoint)) {
                        count++;
                    }
                }
                if (count==0) {
                    return true;
                }
                if(count==1&&chessBoard.isChessHere(point)&&(chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType())){
                    return true;
                }
            }
            if (tempy < 0) {
                for (int i = 0; i < Math.abs(point.y - chessPoint.y); i++) {
                    Tempoint.y -= 1;
                    if (chessBoard.isChessHere(Tempoint)) {
                        count++;
                    }
                }
                if (count==0) {
                    return true;
                }
                if(count==1&&chessBoard.isChessHere(point)&&(chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType())){
                    return true;
                }
            }
        }
        if (point.y == chessPoint.y) {
            tempx = point.x - chessPoint.x;
            if (tempx > 0) {
                for (int i = 0; i < Math.abs(point.x - chessPoint.x); i++) {
                    Tempoint.x += 1;
                    if (chessBoard.isChessHere(Tempoint)) {
                        count++;
                    }
                }
                if (count==0) {
                    return true;
                }
                if(count==1&&chessBoard.isChessHere(point)&&(chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType())){
                    return true;
                }
            }
            if (tempx < 0) {
                for (int i = 0; i < Math.abs(point.x - chessPoint.x); i++) {
                    Tempoint.x -= 1;
                    if (chessBoard.isChessHere(Tempoint)) {
                        count++;
                    }
                }
                if (count==0) {
                    return true;
                }
                if(count==1&&chessBoard.isChessHere(point)&&(chessBoard.getChessOnPoint(point).getType() != chessBoard.getChessOnPoint(chessPoint).getType())){
                    return true;
                }

            }
        }
        return false;
    }
}
