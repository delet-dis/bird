package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

  public void setupThread(Context context) {
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


  public void setGameEventListener(DrawingThread.GameEventListener listener) {
	drawingThread.gameEventListener = listener;
  }

}

class DrawingThread extends Thread {


  Mario mario = new Mario();
  Enemy enemy = new Enemy();
  DrawingThread.GameEventListener gameEventListener;
  private float marioX = 0;
  private float marioY = 0;
  private float goalX = 0;
  private float goalY = 0;
  private float enemyX = 700;
  private float enemyY = 700;
  private boolean running = true;
  private SurfaceHolder surfaceHolder;
  private Context context;

  public void setGoal(float goalX, float goalY) {
	this.goalX = goalX;
	this.goalY = goalY;
  }

//  GameScreen.GameEventListener;

  public void setContext(Context context) {
	this.context = context;
  }

  public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
	this.surfaceHolder = surfaceHolder;
  }

  public void stopDrawing() {
	running = false;
  }

  public Rect getRectForMario() {
	return new Rect((int) marioX, (int) marioY, (int) marioX + 150, (int) marioY + 150);
  }

  public Rect getRectForEnemy() {
	return new Rect((int) enemyX, (int) enemyY, (int) enemyX + 150, (int) enemyY + 150);
  }

  @Override
  public void run() {

	while (running) {
	  Canvas canvas = surfaceHolder.lockCanvas();

	  if (canvas != null) {

		canvas.drawColor(Color.WHITE);

		canvas.drawBitmap(mario.getNextMario(context), marioX, marioY, new Paint());

		canvas.drawBitmap(enemy.getNextEnemy(context), enemyX, enemyY, new Paint());

		if (marioX < goalX) {
		  marioX += Consts.marioSpeed;
		} else if (marioX > goalX) {
		  marioX -= Consts.marioSpeed;
		}
		if (Math.abs(marioX - goalX) < Consts.marioSpeed) {
		  marioX = goalX;
		}

		if (marioY < goalY) {
		  marioY += Consts.marioSpeed;
		} else if (marioY > goalY) {
		  marioY -= Consts.marioSpeed;
		}
		if (Math.abs(marioY - goalY) < Consts.marioSpeed) {
		  marioY = goalY;
		}

		if (enemyX < marioX) {
		  enemyX += Consts.enemySpeed;
		} else if (marioX < enemyX) {
		  enemyX -= Consts.enemySpeed;
		}
		if (Math.abs(marioX - enemyX) < Consts.enemySpeed) {
		  marioX = enemyX;
		}

		if (enemyY < marioY) {
		  enemyY += Consts.enemySpeed;
		} else if (marioY < enemyY) {
		  enemyY -= Consts.enemySpeed;
		}
		if (Math.abs(marioY - enemyY) < Consts.enemySpeed) {
		  marioY = enemyY;
		}

		if (getRectForMario().intersect(getRectForEnemy())) {
		  running = false;
		  gameEventListener.gameStopped();
		}
		surfaceHolder.unlockCanvasAndPost(canvas);
	  }
	}
  }

  interface GameEventListener {
	void gameStarted();

	void gameStopped();
  }
}
