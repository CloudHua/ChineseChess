package hkz.chinesechess.model;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.LinkedList;
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
    List<IChess> iChesses=new LinkedList<IChess>();
    ChessBoardListener chessBoardListener;

    public ChessBoard(Rect size,List<IChess> iChesses){
               this.size=size;
    }
    @Override
    public List<IChess> getAllChess() {
        return iChesses;
    }

    @Override
    public List<IChess> getChessByType(int type) {
        List iChessesTemp=new LinkedList();
        for(int i=0;i<iChesses.size();i++){
            if (iChesses.get(i).getType()==type){
                iChessesTemp.add(iChesses.get(i));
            }
        }
        return iChessesTemp;
    }

    @Override
    public Rect getSize() {
        return size;
    }

    @Override
    public void addChess(IChess chess) {
        iChesses.add(chess);
        if(chessBoardListener!=null){
            chessBoardListener.onChessAdded(chess);
        }
    }

    @Override
    public void removeChess(IChess chess) {
        iChesses.remove(chess);
        if(chessBoardListener!=null){
            chessBoardListener.onChessRemoved(chess);
        }
    }

    @Override
    public void moveChess(IChess chess, Point from, Point to) {
        chess.moveTo(to);
        if(chessBoardListener!=null){
            chessBoardListener.onChessMoved(chess,from,to);
        }
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
             chessBoardListener=listener;

    }

    @Override
    public void unListenChessBoard(ChessBoardListener listener) {
            if(chessBoardListener==listener) {
                chessBoardListener = null;
            }
    }
}
