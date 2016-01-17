package hkz.chinesechess.model.base;

import hkz.chinesechess.model.base.IChessBoard;
import hkz.chinesechess.model.base.IController;

/**
 * Created by wind on 2016/1/14.
 */
public interface IPlayer {

    int getType();

    void onTurn(IController controller, IChessBoard chessBoard);

}
