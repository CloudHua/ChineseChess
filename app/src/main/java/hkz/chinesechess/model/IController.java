package hkz.chinesechess.model;

import android.graphics.Point;

/**
 * Created by wind on 2016/1/14.
 */
public interface IController {

    interface ControllerListener {

        void onInit(IChessBoard chessBoard);

        void onStart(IChessBoard chessBoard);

        void onPause(IChessBoard chessBoard);

        void onResume(IChessBoard chessBoard);

        void onStop(IChessBoard chessBoard);
    }

    interface TurnListener {
        void onTurn(IPlayer player);
    }

    void init(IPlayer player0, IPlayer player1);

    void start();

    void pause();

    void resume();

    void stop();

    int getState();

    boolean commitMovement(IPlayer player, IChess chess, Point from, Point to);

    IPlayer getTurnedPlayer();

    void registerControllerListener(ControllerListener listener);

    void removeControllerListener(ControllerListener listener);

    void registerTurnListener(TurnListener listener);

    void removeTurnListener(TurnListener listener);

}
