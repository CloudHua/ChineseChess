package hkz.chinesechess.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import hkz.chinesechess.R;
import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.model.base.TYPE;
import hkz.chinesechess.model.chess.BaseChess;
import hkz.chinesechess.ui.widget.ChessBoardView;

/**
 * Created by pc on 2016/1/22.
 */
public class ChessActivity extends AppCompatActivity {

    @Bind(R.id.container)
    FrameLayout container;

    ChessBoardView mChessBoardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        int width = (int) (getResources().getDisplayMetrics().widthPixels
                - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()));
        int height = width / 9 * 10;
        mChessBoardView = new ChessBoardView(this, mChessBoardViewCallback);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        params.gravity = Gravity.CENTER;
        mChessBoardView.setLayoutParams(params);
        mChessBoardView.addChess(new BaseChess(TYPE.BLACK, new Point(5, 4), null));
        mChessBoardView.addChess(new BaseChess(TYPE.BLACK, new Point(4, 4), null));
        mChessBoardView.addChess(new BaseChess(TYPE.BLACK, new Point(4, 5), null));

        container.addView(mChessBoardView);
    }

    private IChessBoardView.ChessBoardViewCallback mChessBoardViewCallback = new IChessBoardView.ChessBoardViewCallback() {
        @Override
        public boolean canDragChess(IChess chess, View chessView) {
            return true;
        }

        @Override
        public boolean canDropChess(IChess chess, View chessView, Point point) {
            return true;
        }

        @Override
        public void onChessMoved(IChess chess, Point from, Point to) {

        }
    };
}
