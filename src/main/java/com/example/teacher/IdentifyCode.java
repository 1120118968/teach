package com.example.teacher;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;
public class IdentifyCode {
    private static final char[] CHARS={
            '0','1','2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    private static IdentifyCode identifyCode;
    public static IdentifyCode getInstance(){
        if(identifyCode == null){
            identifyCode = new IdentifyCode();
        }
        return identifyCode;
    }
    private static final int CODE_LENGTH = 4 ;
    private static final int LINE_NUMBER = 5;
    private static  final int FONT_SIZE = 50;
    private static final int BASE_PADDING_LEFT = 10,RANGE_PADDING_LEFT=100,BASE_PADDING_TOP=75, RANGE_PADDING_TOP = 50;
    private static final int DEFAULT_WIDTH = 400,DEFAULT_HEIGHT = 150;
    private int width = DEFAULT_WIDTH,height = DEFAULT_HEIGHT;
    private int base_padding_left=BASE_PADDING_LEFT,range_padding_left=RANGE_PADDING_LEFT, base_padding_top=BASE_PADDING_TOP,range_padding_top=RANGE_PADDING_TOP;
    private int codeLengt = CODE_LENGTH,lineNUmber = LINE_NUMBER,fonSize = FONT_SIZE;
    private  String code;
    private int padding_left,padding_top;
    private Random random = new Random();
    public String getCode() {
        return code;
    }
    public Bitmap createBitmap(){
        padding_left = 0;
        padding_top = 0;
        Bitmap bp = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bp);
        code = createCode();
        c.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(fonSize);
        for (int i=0;i<code.length();i++){
            randomTextStyle(p);
            randomPadding();
            c.drawText(code.charAt(i)+"",padding_left,padding_top,p);
        }
        for (int i = 0; i<lineNUmber;i++){
            drawLine(c,p);
        }
        c.save();
        c.restore();
        return bp;
    }
    private void drawLine(Canvas c, Paint p) {
        int color = randomColor();
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int stopX = random.nextInt(width);
        int stopY = random.nextInt(height);
        p.setStrokeWidth(1);
        p.setColor(color);
        c.drawLine(startX,startY,stopX,stopY,p);
    }
    private int randomColor() {
        return randomColor(1);
    }
    private int randomColor(int rate) {
        int red = random.nextInt(256)/rate;
        int green = random.nextInt(256)/rate;
        int blue = random.nextInt(256)/rate;
        return Color.rgb(red,green,blue);
    }
    private void randomPadding() {
        padding_left += base_padding_left +random.nextInt(range_padding_left);
        padding_top = base_padding_top+random.nextInt(range_padding_top);
    }
    private void randomTextStyle(Paint p) {
        int color = randomColor();
        p.setColor(color);
        p.setFakeBoldText(random.nextBoolean());
        double skew = random.nextInt(11)/10;
        skew = random.nextBoolean()? skew:-skew;
        p.setUnderlineText(true);//下划线
        p.setStrikeThruText(true);//删除线
    }
    private String createCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<codeLengt;i++){
            sb.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return sb.toString();
    }
}
