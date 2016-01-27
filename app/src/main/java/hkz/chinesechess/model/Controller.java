package hkz.chinesechess.model;

import android.graphics.Point;
import android.util.Log;

import java.util.Random;

import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.model.base.IController;
import hkz.chinesechess.model.base.IPlayer;
import hkz.chinesechess.model.base.TYPE;
import hkz.chinesechess.model.chess.Bing;
import hkz.chinesechess.model.chess.Jiang;
import hkz.chinesechess.model.chess.Ju;
import hkz.chinesechess.model.chess.Ma;
import hkz.chinesechess.model.chess.Pao;
import hkz.chinesechess.model.chess.Shi;
import hkz.chinesechess.model.chess.Xiang;

/**
 * Created by Administrator on 2016/1/24.
 */
public class Controller implements IController {
    ChessBoard chessBoard;
    IPlayer[] iPlayers;
    ControllerListener controllerListener;
    TurnListener turnListener;
    int state = PAUSE;
    int turn = 0;
    public static final int ON = 1;
    public static final int PAUSE = 0;

    @Override
    public void init(IPlayer player0, IPlayer player1) {
        chessBoard = new ChessBoard();
        iPlayers = new IPlayer[]{player0, player1};
        controllerListener.onInit(chessBoard);
        chessBoard.addChess(new Ju(TYPE.RED, new Point(0, 0), chessBoard));
        chessBoard.addChess(new Ma(TYPE.RED, new Point(1, 0), chessBoard));
        chessBoard.addChess(new Xiang(TYPE.RED, new Point(2, 0), chessBoard));
        chessBoard.addChess(new Shi(TYPE.RED, new Point(3, 0), chessBoard));
        chessBoard.addChess(new Jiang(TYPE.RED, new Point(4, 0), chessBoard));
        chessBoard.addChess(new Shi(TYPE.RED, new Point(5, 0), chessBoard));
        chessBoard.addChess(new Xiang(TYPE.RED, new Point(6, 0), chessBoard));
        chessBoard.addChess(new Ma(TYPE.RED, new Point(7, 0), chessBoard));
        chessBoard.addChess(new Ju(TYPE.RED, new Point(8, 0), chessBoard));
        chessBoard.addChess(new Pao(TYPE.RED, new Point(1, 2), chessBoard));
        chessBoard.addChess(new Pao(TYPE.RED, new Point(7, 2), chessBoard));
        chessBoard.addChess(new Bing(TYPE.RED, new Point(0, 3), chessBoard));
        chessBoard.addChess(new Bing(TYPE.RED, new Point(2, 3), chessBoard));
        chessBoard.addChess(new Bing(TYPE.RED, new Point(4, 3), chessBoard));
        chessBoard.addChess(new Bing(TYPE.RED, new Point(6, 3), chessBoard));
        chessBoard.addChess(new Bing(TYPE.RED, new Point(8, 3), chessBoard));

        chessBoard.addChess(new Ju(TYPE.BLACK, new Point(8, 9), chessBoard));
        chessBoard.addChess(new Ma(TYPE.BLACK, new Point(7, 9), chessBoard));
        chessBoard.addChess(new Xiang(TYPE.BLACK, new Point(6, 9), chessBoard));
        chessBoard.addChess(new Shi(TYPE.BLACK, new Point(5, 9), chessBoard));
        chessBoard.addChess(new Jiang(TYPE.BLACK, new Point(4, 9), chessBoard));
        chessBoard.addChess(new Shi(TYPE.BLACK, new Point(3, 9), chessBoard));
        chessBoard.addChess(new Xiang(TYPE.BLACK, new Point(2, 9), chessBoard));
        chessBoard.addChess(new Ma(TYPE.BLACK, new Point(1, 9), chessBoard));
        chessBoard.addChess(new Ju(TYPE.BLACK, new Point(0, 9), chessBoard));
        chessBoard.addChess(new Pao(TYPE.BLACK, new Point(7, 7), chessBoard));
        chessBoard.addChess(new Pao(TYPE.BLACK, new Point(1, 7), chessBoard));
        chessBoard.addChess(new Bing(TYPE.BLACK, new Point(8, 6), chessBoard));
        chessBoard.addChess(new Bing(TYPE.BLACK, new Point(6, 6), chessBoard));
        chessBoard.addChess(new Bing(TYPE.BLACK, new Point(4, 6), chessBoard));
        chessBoard.addChess(new Bing(TYPE.BLACK, new Point(2, 6), chessBoard));
        chessBoard.addChess(new Bing(TYPE.BLACK, new Point(0, 6), chessBoard));

    }

    @Override
    public void start() {
        state = ON;
        controllerListener.onStart(chessBoard);
        turnListener.onTurn(iPlayers[turn]);
        iPlayers[turn].onTurn(this, chessBoard);
    }

    @Override
    public void pause() {
        state = PAUSE;
        controllerListener.onPause(chessBoard);
    }

    @Override
    public void resume() {
        state = ON;
        controllerListener.onResume(chessBoard);
    }

    @Override
    public void stop() {
        state = PAUSE;
        controllerListener.onStop(chessBoard);
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public boolean commitMovement(IPlayer player, IChess chess, Point from, Point to) {
        if (state != ON || iPlayers[turn] != player || !chess.canReach(to)) {
            return false;
        }
        if (chessBoard.isChessHere(to)) {
            chessBoard.removeChess(chessBoard.getChessOnPoint(to));
        }
        chessBoard.moveChess(chess, from, to);
        turn = (turn + 1) % 2;
        Log.i("Chess", "turn: " + turn);
        turnListener.onTurn(iPlayers[turn]);
        iPlayers[turn].onTurn(this, chessBoard);
        return true;
    }

    @Override
    public IPlayer getTurnedPlayer() {
        return iPlayers[turn];
    }

    @Override
    public void registerControllerListener(ControllerListener listener) {
        controllerListener = listener;
    }

    @Override
    public void removeControllerListener(ControllerListener listener) {
        controllerListener = null;
    }

    @Override
    public void registerTurnListener(TurnListener listener) {
        turnListener = listener;
    }

    @Override
    public void removeTurnListener(TurnListener listener) {
        turnListener = null;
    }
}
