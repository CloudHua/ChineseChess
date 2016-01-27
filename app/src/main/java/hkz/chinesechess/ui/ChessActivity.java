package hkz.chinesechess.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import hkz.chinesechess.R;
import hkz.chinesechess.model.Controller;
import hkz.chinesechess.model.Player;
import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.model.base.IChessBoard;
import hkz.chinesechess.model.base.IController;
import hkz.chinesechess.model.base.IPlayer;
import hkz.chinesechess.model.base.TYPE;
import hkz.chinesechess.ui.widget.ChessBoardView;

/**
 * Created by pc on 2016/1/22.
 */
public class ChessActivity extends AppCompatActivity {

    FrameLayout container;
    IController iController;
    ChessBoardView mChessBoardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);
        container = (FrameLayout) findViewById(R.id.container);
        iController=new Controller();
        iController.registerControllerListener(listener);
        iController.registerTurnListener(turnListener);
        initView();
        iController.init(new Player(TYPE.BLACK),new Player(TYPE.RED));
        iController.start();
    }

    private void initView() {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = width / 9 * 10;
        mChessBoardView = new ChessBoardView(this, mChessBoardViewCallback);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        params.gravity = Gravity.CENTER;
        mChessBoardView.setLayoutParams(params);

        container.addView(mChessBoardView);
    }
    private IController.TurnListener turnListener=new IController.TurnListener() {
        @Override
        public void onTurn(IPlayer player) {

        }
    };
    private IController.ControllerListener listener=new IController.ControllerListener() {
        @Override
        public void onInit(IChessBoard chessBoard) {
           chessBoard.listenChessBoard(new IChessBoard.ChessBoardListener() {
               @Override
               public void onChessAdded(IChess chess) {
                   mChessBoardView.addChess(chess);
               }

               @Override
               public void onChessRemoved(IChess chess) {
                  mChessBoardView.removeChess(chess);
               }

               @Override
               public void onChessMoved(IChess chess, Point from, Point to) {
                  mChessBoardView.moveChess(chess, from, to);
               }
           });
        }

        @Override
        public void onStart(IChessBoard chessBoard) {

        }

        @Override
        public void onPause(IChessBoard chessBoard) {

        }

        @Override
        public void onResume(IChessBoard chessBoard) {

        }

        @Override
        public void onStop(IChessBoard chessBoard) {

        }
    };
    private IChessBoardView.Callback mChessBoardViewCallback = new IChessBoardView.Callback() {
        @Override
        public boolean canDragChess(IChess chess, View chessView) {
            return iController.getState()==Controller.ON&&chess.getType()==iController.getTurnedPlayer().getType();
        }

        @Override
        public boolean canDropChess(IChess chess, View chessView, Point point) {
            return chess.canReach(point);
        }

        @Override
        public void onChessMoved(IChess chess, Point from, Point to) {
            Log.i("Chess", "onChessMoved from " + from + " to " + to);
            iController.commitMovement(iController.getTurnedPlayer(),chess,from,to);
        }
    };
}
