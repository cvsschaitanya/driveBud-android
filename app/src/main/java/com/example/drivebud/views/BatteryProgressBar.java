package com.example.drivebud.views;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

public class BatteryProgressBar extends View {
    private float TOP_MARGIN;
    private Canvas canvas;
    private float LEFT_MARGIN;
    private float HEIGHT;
    private float WIDTH;
    private int progress;

    public BatteryProgressBar(Context context) {
        super(context);
        init(null);
    }

    public BatteryProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BatteryProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public BatteryProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private float THICKNESS;
    private RectF outer_rect, inner_rect;
    private Paint paint;

    private void init(AttributeSet attrs) {

        if(attrs!=null){
            this.progress = attrs.getAttributeIntValue("app","progress",0);
        }

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        HEIGHT = getMeasuredHeight();
        WIDTH = getMeasuredWidth();
        THICKNESS = WIDTH/20;
        TOP_MARGIN = LEFT_MARGIN = WIDTH/20;

        outer_rect = new RectF(LEFT_MARGIN, TOP_MARGIN, getMeasuredWidth() - LEFT_MARGIN, getMeasuredHeight() - TOP_MARGIN);
        inner_rect = new RectF(LEFT_MARGIN + THICKNESS, TOP_MARGIN + THICKNESS, getMeasuredWidth() - LEFT_MARGIN - THICKNESS, getMeasuredHeight() - TOP_MARGIN - THICKNESS);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawDoubleRoundRect(outer_rect,
//                2 * THICKNESS, 2 * THICKNESS,
                THICKNESS, THICKNESS,
                inner_rect,
                0, 0,
//                THICKNESS,THICKNESS,
                paint);
        setProgress(progress);
    }

    public synchronized void setProgress(int progress) {
        this.progress=progress;

        if(this.canvas==null)
            return;

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        int height = (int) (0.01 * progress * (HEIGHT-2*TOP_MARGIN-2*THICKNESS));
        RectF rect = new RectF(LEFT_MARGIN+THICKNESS,HEIGHT-TOP_MARGIN-THICKNESS-height,WIDTH-LEFT_MARGIN-THICKNESS,HEIGHT-TOP_MARGIN-THICKNESS);
        canvas.drawRect(rect, paint);

    }
}
