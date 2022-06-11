package com.example.drivebud.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.drivebud.R;

public class BatteryProgressBar extends View {
    private float VERTICAL_MARGIN;
    private Canvas canvas;
    private float HORIZONITAL_MARGIN;
    private float HEIGHT;
    private float WIDTH;
    private float TOP_GAP;
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
    private RectF big_rect, cap_rect;
    private Paint paint;

    private void init(AttributeSet attrs) {

        if (attrs != null) {
            this.progress = attrs.getAttributeIntValue("app", "progress", 0);
        }

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progress = 93;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        HEIGHT = getMeasuredHeight();
        WIDTH = getMeasuredWidth();
        THICKNESS = WIDTH / 16;
        VERTICAL_MARGIN = HORIZONITAL_MARGIN = WIDTH / 20;
        TOP_GAP = HEIGHT / 20;

        big_rect = new RectF(HORIZONITAL_MARGIN, TOP_GAP + VERTICAL_MARGIN, WIDTH - HORIZONITAL_MARGIN, HEIGHT - VERTICAL_MARGIN);
        cap_rect = new RectF(WIDTH/4,0,3*WIDTH/4,TOP_GAP+VERTICAL_MARGIN);
        paint.setStrokeWidth(THICKNESS);



    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        this.canvas = canvas;
        paint.setColor(getResources().getColor(R.color.button_background_color));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(big_rect, paint);

        paint.setColor(getResources().getColor(R.color.button_background_color));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(cap_rect, paint);

        setProgress(progress);
    }

    public synchronized void setProgress(int progress) {
        this.progress = progress;

        if (this.canvas == null)
            return;


        int height = (int) (0.01 * progress * (HEIGHT - 2 * VERTICAL_MARGIN - TOP_GAP - 2 * THICKNESS));
        RectF rect = new RectF(HORIZONITAL_MARGIN + THICKNESS,
                HEIGHT - VERTICAL_MARGIN - THICKNESS - height,
                WIDTH - HORIZONITAL_MARGIN - THICKNESS,
                HEIGHT - VERTICAL_MARGIN - THICKNESS);

        paint.setColor(Color.RED);
        canvas.drawRect(rect, paint);

    }
}
