package hkz.chinesechess.model;

import hkz.chinesechess.model.base.IChessBoard;
import hkz.chinesechess.model.base.IController;
import hkz.chinesechess.model.base.IPlayer;

/**
 * Created by Administrator on 2016/1/26.
 */
public class Player implements IPlayer {
    int type;

    public Player(int type) {
        this.type = type;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void onTurn(IController controller, IChessBoard chessBoard) {

    }
}
