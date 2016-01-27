package hkz.chinesechess.model.util;

import android.graphics.Color;

import hkz.chinesechess.model.base.IChess;
import hkz.chinesechess.model.base.TYPE;
import hkz.chinesechess.model.chess.Bing;
import hkz.chinesechess.model.chess.Jiang;
import hkz.chinesechess.model.chess.Ju;
import hkz.chinesechess.model.chess.Ma;
import hkz.chinesechess.model.chess.Pao;
import hkz.chinesechess.model.chess.Shi;
import hkz.chinesechess.model.chess.Xiang;

/**
 * Created by Administrator on 2016/1/26.
 */
public class ChessUtil {
    public static String getChessName(IChess chess){
        if (chess instanceof Bing) {
            return "兵";
        }else if(chess instanceof Jiang){
            return "将";
        }else if(chess instanceof Ju){
            return "车";
        }else if(chess instanceof Ma){
            return "马";
        }else if(chess instanceof Pao){
            return "炮";
        }else if(chess instanceof Shi){
            return "士";
        }else if(chess instanceof Xiang){
            return "象";
        }
        return null;
    }
    public static int getChessColor(IChess chess){
        return chess.getType()== TYPE.RED? Color.RED:Color.BLACK;
    }
}
