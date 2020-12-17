package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameScreen extends View {

  Timer timer;

  private float birdX = 0;
  private float birdY = 0;

  public GameScreen(Context context) {
	super(context);
	timer = new Timer();
	timer.start();
  }

  public GameScreen(Context context, @Nullable AttributeSet attrs) {
	super(context, attrs);
	timer = new Timer();
	timer.start();
  }

  public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
	super(context, attrs, defStyleAttr);
	timer = new Timer();
	timer.start();
  }

  public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
	super(context, attrs, defStyleAttr, defStyleRes);
	timer = new Timer();
	timer.start();
  }


  @Override
  protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);

	Bitmap bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);

	canvas.drawBitmap(bird, birdX, birdY, new Paint());

	birdX++;
	birdY++;
  }



  class Timer extends CountDownTimer {

	public Timer() {
	  super(Integer.MAX_VALUE, 1000 / 60);
	}

	@Override
	public void onTick(long millisUntilFinished) {
	  GameScreen.this.invalidate();
	}

	@Override
	public void onFinish() {

	}
  }
}
