package hkz.chinesechess.model.chess;

import android.graphics.Point;

import hkz.chinesechess.model.base.IChessBoard;

/**
 * Created by Administrator on 2016/1/20.
 */
public class Pao extends BaseChess {
    Point InitPoint;
    public Pao(int chessType, Point chessPoint, IChessBoard chessBoard) {
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
                if (tempy > 1) {
                    for (int i = 0; i < Math.abs(point.y - chessPoint.y); i++) {
                        Tempoint.y += 1;
                        if (chessBoard.isChessHere(Tempoint)) {
                            count++;
                        }
                    }
                    if (count == 1||count==0) {
                        return true;
                    }
                }
                if (tempy < -1) {
                    for (int i = 0; i < Math.abs(point.y - chessPoint.y); i++) {
                        Tempoint.y -= 1;
                        if (chessBoard.isChessHere(Tempoint)) {
                            count++;
                        }
                    }
                    if (count == 1||count==0) {
                        return true;
                    }
                }
        }
        if (point.y == chessPoint.y) {
                tempx = point.x - chessPoint.x;
                if (tempx > 1) {
                    for (int i = 0; i < Math.abs(point.x - chessPoint.x); i++) {
                        Tempoint.x += 1;
                        if (chessBoard.isChessHere(Tempoint)) {
                            count++;
                        }
                    }
                    if (count == 1||count==0) {
                        return true;
                    }
                }
                if (tempx < -1) {
                    for (int i = 0; i < Math.abs(point.x - chessPoint.x); i++) {
                        Tempoint.x -= 1;
                        if (chessBoard.isChessHere(Tempoint)) {
                            count++;
                        }
                    }
                    if (count == 1||count==0) {
                        return true;
                    }

                }
        }

        if((Math.abs(point.x-chessPoint.x)==1||Math.abs(point.y-chessPoint.y)==1)&&!chessBoard.isChessHere(point)){
            return true;
        }

        return false;
    }

}
