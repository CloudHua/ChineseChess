package hkz.chinesechess.model;

/**
 * Created by wind on 2016/1/14.
 */
public interface IPlayer {

    int getType();

    void onTurn(IController controller, IChessBoard chessBoard);

}
