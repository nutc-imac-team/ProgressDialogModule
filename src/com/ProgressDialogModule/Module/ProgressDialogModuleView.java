package com.ProgressDialogModule.Module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

public class ProgressDialogModuleView extends View {
	private Context context;
	private WH WH;

	private int progress;
	private int finishColor = Color.BLUE;
	private int unfinishColor = Color.GRAY;
	private int circleBackgroundColor = Color.WHITE;

	private int max;
	private int textColor;
	private float textSize;

	private Paint finishedPaint;
	private Paint unfinishedPaint;
	private Paint innerCirclePaint;
	protected Paint textPaint;

	private float finishPaintWidth;
	private float unfinishPaintWidth;

	private RectF finishedOuterRect = new RectF();
	private RectF unfinishedOuterRect = new RectF();

	public ProgressDialogModuleView(Context context) {
		super(context);
		this.context = context;
		init();
		initPaints();
	}

	private void init() {
		WH = new WH(context);
		progress = 0;

		max = 100;
		textColor = Color.BLUE;
		textSize = WH.getTextSize(20);

		finishPaintWidth = 15f;
		unfinishPaintWidth = 15f;
	}

	@Override
	public void invalidate() {
		initPaints();
		super.invalidate();
	}

	protected void initPaints() {
		textPaint = new TextPaint();
		textPaint.setColor(textColor);
		textPaint.setTextSize(textSize);
		textPaint.setAntiAlias(true);

		finishedPaint = new Paint();
		finishedPaint.setColor(finishColor);
		finishedPaint.setStyle(Paint.Style.STROKE);
		finishedPaint.setAntiAlias(true);
		finishedPaint.setStrokeWidth(finishPaintWidth);

		unfinishedPaint = new Paint();
		unfinishedPaint.setColor(unfinishColor);
		unfinishedPaint.setStyle(Paint.Style.STROKE);
		unfinishedPaint.setAntiAlias(true);
		unfinishedPaint.setStrokeWidth(unfinishPaintWidth);

		innerCirclePaint = new Paint();
		innerCirclePaint.setColor(circleBackgroundColor);
		innerCirclePaint.setAntiAlias(true);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		finishedOuterRect.set(finishPaintWidth / 2,
				finishPaintWidth / 2,
                getWidth() - finishPaintWidth / 2,
                getHeight() - finishPaintWidth / 2);
		
		unfinishedOuterRect.set(unfinishPaintWidth / 2,
				unfinishPaintWidth / 2,
                getWidth() - unfinishPaintWidth / 2,
                getHeight() - unfinishPaintWidth / 2);

		float innerCircleRadius = (getWidth() - finishPaintWidth) / 2f;
		canvas.drawCircle(getWidth() / 2f, getHeight() / 2f,
				innerCircleRadius, innerCirclePaint);
		canvas.drawArc(finishedOuterRect, 0, getProgressAngle(), false,
				finishedPaint);
		canvas.drawArc(unfinishedOuterRect, getProgressAngle(),
				360 - getProgressAngle(), false, unfinishedPaint);

		String text = "" + progress + "%";
		if (!TextUtils.isEmpty(text)) {
			float textHeight = textPaint.descent() + textPaint.ascent();
			canvas.drawText(text,
					(getWidth() - textPaint.measureText(text)) / 2f,
					(getWidth() - textHeight) / 2f, textPaint);
		}
	}

	// 目前百分比數
	public int getProgress() {
		return this.progress;
	}

	// 設定百分比數
	public void setProgress(int progress) {
		this.progress = progress;
		if (this.progress > getMax()) {
            this.progress = this.progress % getMax();
        }
		invalidate();
	}

	// 目前已完成區塊顏色
	public int getFinishColor() {
		return this.finishColor;
	}

	// 設定已完成區塊顏色
	public void setFinishColor(int finishColor) {
		this.finishColor = finishColor;
		invalidate();
	}

	// 目前未完成區塊顏色
	public int getUnfinishColor() {
		return this.unfinishColor;
	}

	// 設定未完成區塊顏色
	public void setUnfinishColor(int unfinishColor) {
		this.unfinishColor = unfinishColor;
		invalidate();
	}

	// 目前中間圓形顏色
	public int getCircleBackgroundColor() {
		return this.circleBackgroundColor;
	}

	// 設定中間圓形顏色
	public void setCircleBackgroundColor(int circleBackgroundColor) {
		this.circleBackgroundColor = circleBackgroundColor;
	}

	// 目前最大值
	public int getMax() {
		return this.max;
	}

	// 設定最大值
	public void setMax(int max) {
		this.max = max;
	}

	// 目前中間字體的大小
	public float getTextSize() {
		return textSize;
	}

	// 設定中間字體的大小
	public void setTextSize(float textSize) {
		this.textSize = textSize;
		invalidate();
	}

	// 目前中間字體顏色
	public int getTextColor() {
		return textColor;
	}

	// 設定中間字體顏色
	public void setTextColor(int textColor) {
		this.textColor = textColor;
		invalidate();
	}

	// 換算百分比成圓形
	private float getProgressAngle() {
		return getProgress() / (float) max * 360f;
	}
}
