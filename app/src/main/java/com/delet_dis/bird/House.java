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

	paint.setColor(Color.DKGRAY);

	float sizeCoeff = getWidth() * 0.02f;
	float leftTopPadding = getWidth() * 0.2f;

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
	paint.setColor(Color.BLUE);
	canvas.drawRect(leftTopPadding * 1.1f, leftTopPadding * 1.2f, leftTopPadding + 10 * sizeCoeff, leftTopPadding + 13 * sizeCoeff, paint);

	path.reset();

	for (int i = 1; i < 13; i += 2) {
	  path.moveTo(leftTopPadding * 1.1f + 0 * sizeCoeff, leftTopPadding * 1.2f + i * sizeCoeff);
	  path.lineTo(leftTopPadding * 1.1f + 9 * sizeCoeff, leftTopPadding * 1.2f + i * sizeCoeff);
	}

//	for(int i = 0; i<)

	paint.setColor(Color.BLACK);
	canvas.drawPath(path, paint);
  }
}
