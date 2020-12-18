package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GameScreen extends SurfaceView implements SurfaceHolder.Callback {


  DrawingThread drawingThread;

  public GameScreen(Context context) {
	super(context);
	getHolder().addCallback(this);
  }

  public GameScreen(Context context, @Nullable AttributeSet attrs) {
	super(context, attrs);
	getHolder().addCallback(this);
  }

  public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
	super(context, attrs, defStyleAttr);
	getHolder().addCallback(this);
  }

  private void setupThread(Context context) {
	drawingThread = new DrawingThread();
	drawingThread.setSurfaceHolder(getHolder());
	drawingThread.setContext(context);
	drawingThread.start();
  }


  @Override
  public void surfaceCreated(@NonNull SurfaceHolder holder) {
	setupThread(getContext());
  }

  @Override
  public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
	if (event.getAction() == MotionEvent.ACTION_DOWN) {
	  drawingThread.setGoal(event.getX(), event.getY());
	}
	return super.onTouchEvent(event);
  }
}

class DrawingThread extends Thread {

  private float birdX = 0;
  private float birdY = 0;

  private float goalX = 0;
  private float goalY = 0;

  private boolean running = true;
  private SurfaceHolder surfaceHolder;
  private Context context;

  public void setGoal(float goalX, float goalY) {
	this.goalX = goalX;
	this.goalY = goalY;
  }

  public void setContext(Context context) {
	this.context = context;
  }

  public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
	this.surfaceHolder = surfaceHolder;
  }

  public void stopDrawing() {
	running = false;
  }

  @Override
  public void run() {

	while (running) {
	  Canvas canvas = surfaceHolder.lockCanvas();

	  if (canvas != null) {

	    canvas.drawColor(Color.WHITE);

		Bitmap bird = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);

		canvas.drawBitmap(bird, birdX-(float)bird.getWidth()/2, birdY-(float)bird.getHeight()/2, new Paint());

		if (birdX < goalX) {
		  birdX += 10;
		} else if (birdX > goalX) {
		  birdX -= 10;
		}
		if (Math.abs(birdX - goalX) < 10) {
		  birdX = goalX;
		}

		if (birdY < goalY) {
		  birdY += 10;
		} else if (birdY > goalY) {
		  birdY -= 10;
		}
		if (Math.abs(birdY - goalY) < 10) {
		  birdY = goalY;
		}

		surfaceHolder.unlockCanvasAndPost(canvas);
	  }
	}
  }
}
