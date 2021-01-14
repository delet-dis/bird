package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class House extends View {

  public House(Context context) {
	super(context);
  }

  @Override
  protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);

	Paint paint = new Paint();
	paint.setAntiAlias(true);

	float sizeCoeff = getWidth() * 0.02f;
	float leftTopPadding = getWidth() * 0.2f;

	paint.setColor(Color.DKGRAY);
	canvas.drawRect(leftTopPadding, leftTopPadding, 30 * sizeCoeff, 30 * sizeCoeff, paint);

	Path path = new Path();
	path.moveTo(leftTopPadding, leftTopPadding);
	path.lineTo(20 * sizeCoeff, 0);
	path.lineTo(30 * sizeCoeff, leftTopPadding);

	paint.setColor(Color.RED);
	canvas.drawPath(path, paint);

	paint.setColor(Color.BLACK);
	paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeWidth(3);
	canvas.drawCircle(leftTopPadding + sizeCoeff * 10, leftTopPadding - 4 * sizeCoeff, sizeCoeff * 3, paint);


	paint.setStyle(Paint.Style.FILL_AND_STROKE);
	paint.setColor(Color.WHITE);
	canvas.drawRect(leftTopPadding * 1.1f, leftTopPadding * 1.2f, leftTopPadding + 10 * sizeCoeff, leftTopPadding + 13 * sizeCoeff, paint);

	path.reset();

	for (int i = 2; i < 12; i += 2) {
	  path.moveTo(leftTopPadding * 1.1f + 0 * sizeCoeff, leftTopPadding * 1.2f + i * sizeCoeff);
	  path.lineTo(leftTopPadding * 1.1f + 9 * sizeCoeff, leftTopPadding * 1.2f + i * sizeCoeff);
	}

	for (int i = 2; i < 12; i += 2) {
	  path.moveTo(leftTopPadding * 1f + i * sizeCoeff, leftTopPadding * 1.2f + 0 * sizeCoeff);
	  path.lineTo(leftTopPadding * 1f + i * sizeCoeff, leftTopPadding * 1.4f + 9 * sizeCoeff);
	}
	paint.setColor(Color.BLACK);
	canvas.drawPath(path, paint);

	canvas.drawRect(leftTopPadding * 2.1f, leftTopPadding * 1.2f, 29 * sizeCoeff, 30 * sizeCoeff, paint);

	path.reset();
	for (int i = 0; i < 18; i += 2) {
	  path.moveTo(leftTopPadding * 2.1f, leftTopPadding * 1.2f + i * 22);
	  path.lineTo(29 * sizeCoeff - i * 10, 30 * sizeCoeff);
	}

	for (int i = 0; i > -18; i -= 2) {
	  path.moveTo(29 * sizeCoeff, 30 * sizeCoeff + i * 22);
	  path.lineTo(leftTopPadding * 2.1f - i * 10, leftTopPadding * 1.2f);
	}
	paint.setColor(Color.WHITE);
	canvas.drawPath(path, paint);

  }
}
