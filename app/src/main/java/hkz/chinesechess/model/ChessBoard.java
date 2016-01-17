package hkz.chinesechess.model;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.List;

import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.model.base.IChessBoard;
import hkz.chinesechess.model.base.IPlayer;

/**
 * Created by Administrator on 2016/1/15.
 */
public class ChessBoard implements IChessBoard{
    //IChess chess;
    Rect size;
    List<IChess> iChessesRed;
    List<IChess> iChessesBlack;
    List<IChess> iChesses;


    public ChessBoard(Rect size,List<IChess> iChessesRed,List<IChess> iChessesBlack){
               this.size=size;
               this.iChessesRed=iChessesRed;
               this.iChessesBlack=iChessesBlack;
        for (int i=0;i<iChessesRed.size();i++) {
            iChesses.add(i,iChessesRed.get(i));
        }
        for (int j=0;j<iChessesBlack.size();j++){
            iChesses.add(iChessesRed.size()+j,iChessesBlack.get(j));
        }
    }
    @Override
    public List<IChess> getAllChess() {

        return iChesses;
    }

    @Override
    public List<IChess> getChessByType(int type) {
        if(type==0){
        return iChessesRed;
        }else
            return iChessesBlack;
    }

    @Override
    public Rect getSize() {
        return size;
    }

    @Override
    public void addChess(IChess chess) {
            iChesses.add(chess);
    }

    @Override
    public void removeChess(IChess chess) {
        iChesses.remove(chess);
    }

    @Override
    public boolean isChessHere(Point point) {
        for(int k=0;k<iChesses.size();k++){
            if (iChesses.get(k).getPoint().equals(point)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IChess getChessOnPoint(Point point) {
        int k;
        for( k=0;k<iChesses.size();k++) {
            if (iChesses.get(k).getPoint().equals(point))
                return iChesses.get(k);
        }
        return null;
    }

    @Override
    public void listenChessBoard(ChessBoardListener listener) {

    }

    @Override
    public void unListenChessBoard(ChessBoardListener listener) {

    }
}
