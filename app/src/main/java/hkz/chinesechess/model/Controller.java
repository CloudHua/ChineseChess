package hkz.chinesechess.model;

import android.graphics.Point;

import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.model.base.IController;
import hkz.chinesechess.model.base.IPlayer;

/**
 * Created by Administrator on 2016/1/20.
 */
public class Controller implements IController {
    @Override
    public void init(IPlayer player0, IPlayer player1) {

    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {

    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public boolean commitMovement(IPlayer player, IChess chess, Point from, Point to) {
        return false;
    }

    @Override
    public IPlayer getTurnedPlayer() {
        return null;
    }

    @Override
    public void registerControllerListener(ControllerListener listener) {

    }

    @Override
    public void removeControllerListener(ControllerListener listener) {

    }

    @Override
    public void registerTurnListener(TurnListener listener) {

    }

    @Override
    public void removeTurnListener(TurnListener listener) {

    }
}
