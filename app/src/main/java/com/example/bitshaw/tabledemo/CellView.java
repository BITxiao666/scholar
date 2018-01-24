package com.example.bitshaw.tabledemo;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.TextView;

/**
 * Created by BITshaw on 2018/1/24.
 * 绘制每一个课程单元格
 */

public class CellView extends android.support.v7.widget.AppCompatTextView {
    private int mBgColor = 0; //背景颜色
    private int mCellSize = 0; //圆角大小

    public CellView(Context context, int bgColor, int cornerSize) {
        super(context);
        mBgColor = bgColor;
        mCellSize = cornerSize;
    }
    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mBgColor);
        paint.setAlpha(180);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), mCellSize, mCellSize, paint);

        super.onDraw(canvas);
    }

}
