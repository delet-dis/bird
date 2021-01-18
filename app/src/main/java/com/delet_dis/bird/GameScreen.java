package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class GameScreen extends SurfaceView implements SurfaceHolder.Callback {

  DrawingThread drawingThread;
  DrawingThread.GameEventListener listener;

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

  public void setListener(DrawingThread.GameEventListener listener) {
	this.listener = listener;
  }

  public void setupThread(Context context) {
	drawingThread = new DrawingThread();
	drawingThread.setSurfaceHolder(getHolder());
	drawingThread.setContext(context);
	drawingThread.start();
	drawingThread.gameEventListener = listener;
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
    Log.d("SENDING_SOMETHING", "bzz bzz");
	if (event.getAction() == MotionEvent.ACTION_DOWN) {
	  drawingThread.setGoal(event.getX(), event.getY());
	}
	performClick();
	return true;
  }

  @Override
  public boolean performClick() {
	return super.performClick();
  }

  public void setGameEventListener(DrawingThread.GameEventListener listener) {
	drawingThread.gameEventListener = listener;
  }


}

class DrawingThread extends Thread {

  private final Timer timer = new Timer();
  SpritesCreatorHelper.Mario mario = new SpritesCreatorHelper.Mario();
  SpritesCreatorHelper.Enemy enemy = new SpritesCreatorHelper.Enemy();
  SpritesCreatorHelper.Bonus bonus = new SpritesCreatorHelper.Bonus();
  DrawingThread.GameEventListener gameEventListener;
  int marioSpeedLocal = GameConsts.marioSpeed;
  int enemySpeedLocal = GameConsts.enemySpeed;
  int levelChangeTimeLocal = GameConsts.levelChangeTime;
  int gameScore = 0;
  private float bonusX;
  private float bonusY;
  private float marioX = 0;
  private float marioY = 0;
  private float goalX = 0;
  private float goalY = 0;
  private float enemyX = generateRandomEnemyStartCoordinates();
  private float enemyY = generateRandomEnemyStartCoordinates();
  private boolean running = true;
  private SurfaceHolder surfaceHolder;
  private Context context;

  public void setGoal(float goalX, float goalY) {
	this.goalX = goalX;
	this.goalY = goalY;
  }

  private int generateRandomEnemyStartCoordinates() {
	return (int) ((Math.random() * (800 - 150)) + 150);
  }

  public void setContext(Context context) {
	this.context = context;
  }

  public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
	this.surfaceHolder = surfaceHolder;
  }

  public void stopDrawing() {
	running = false;
	gameEventListener.gameStopped();
  }

  public Rect getRectForMario() {
	return new Rect((int) marioX + 35, (int) marioY + 20, (int) marioX + 115, (int) marioY + 170);
  }

  public Rect getRectForEnemy() {
	return new Rect((int) enemyX, (int) enemyY, (int) enemyX + 150, (int) enemyY + 150);
  }

//  public Rect getRectForBonus() {
//
//	return new Rect(( int ))
//  }


  @Override
  public void run() {

	timer.start();

	while (running) {
	  Canvas canvas = surfaceHolder.lockCanvas();

	  if (canvas != null) {

		canvas.drawColor(Color.WHITE);

		canvas.drawBitmap(mario.getNextMario(context), marioX, marioY, new Paint());

		canvas.drawBitmap(enemy.getNextEnemy(context), enemyX, enemyY, new Paint());

//		canvas.drawBitmap(bonus.getBonusBitmap(context), 900, 900, new Paint());

		if (marioX < goalX) {
		  marioX += GameConsts.marioSpeed;
		} else if (marioX > goalX) {
		  marioX -= GameConsts.marioSpeed;
		}
		if (Math.abs(marioX - goalX) < GameConsts.marioSpeed) {
		  marioX = goalX;
		}

		if (marioY < goalY) {
		  marioY += GameConsts.marioSpeed;
		} else if (marioY > goalY) {
		  marioY -= GameConsts.marioSpeed;
		}
		if (Math.abs(marioY - goalY) < GameConsts.marioSpeed) {
		  marioY = goalY;
		}

		if (enemyX < marioX) {
		  enemyX += GameConsts.enemySpeed;
		} else if (marioX < enemyX) {
		  enemyX -= GameConsts.enemySpeed;
		}
		if (Math.abs(marioX - enemyX) < GameConsts.enemySpeed) {
		  marioX = enemyX;
		}

		if (enemyY < marioY) {
		  enemyY += GameConsts.enemySpeed;
		} else if (marioY < enemyY) {
		  enemyY -= GameConsts.enemySpeed;
		}
		if (Math.abs(marioY - enemyY) < GameConsts.enemySpeed) {
		  marioY = enemyY;
		}

		if (getRectForMario().intersect(getRectForEnemy())) {
		  stopDrawing();
		}
		surfaceHolder.unlockCanvasAndPost(canvas);
	  }
	}
  }

  interface GameEventListener {
	void gameStopped();

	void scoreChanged(int score);
  }

  class Timer extends CountDownTimer {

	public Timer() {
	  super(Integer.MAX_VALUE, levelChangeTimeLocal / 10);
	}

	@Override
	public void onTick(long millisUntilFinished) {
	  levelChangeTimeLocal -= 1;
	  marioSpeedLocal += 1;
	  enemySpeedLocal += 2;

//	  gameEventListener.scoreChanged(gameScore += 100);
	}

	@Override
	public void onFinish() {

	}
  }
}



