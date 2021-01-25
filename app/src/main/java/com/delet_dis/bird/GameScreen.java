package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class GameScreen extends SurfaceView implements SurfaceHolder.Callback {

  DrawingThread drawingThread;
  DrawingThread.GameEventListener listener;

  Timer timer;

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
//	timer.start();
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

  class Timer extends CountDownTimer {

	public Timer() {
	  super(Integer.MAX_VALUE, drawingThread.getLevelChangeTimeLocal() / 10);
	}

	@Override
	public void onTick(long millisUntilFinished) {
	  drawingThread.setLevelChangeTimeLocal(drawingThread.getLevelChangeTimeLocal() - 1);
	  drawingThread.setMarioSpeedLocal(drawingThread.getMarioSpeedLocal() + 1);
	  drawingThread.setEnemySpeedLocal(drawingThread.getEnemySpeedLocal() + 2);

	  drawingThread.setGameScore(drawingThread.getGameScore() + 100);
	}

	@Override
	public void onFinish() {

	}
  }


}

class DrawingThread extends Thread {

  private final SpritesCreatorHelper.Mario mario = new SpritesCreatorHelper.Mario();
  private final SpritesCreatorHelper.Enemy enemy = new SpritesCreatorHelper.Enemy();
  private final SpritesCreatorHelper.Bonus bonus = new SpritesCreatorHelper.Bonus();
  DrawingThread.GameEventListener gameEventListener;
  int marioSpeedLocal = GameStartingValues.marioSpeed;
  int enemySpeedLocal = GameStartingValues.enemySpeed;
  int levelChangeTimeLocal = GameStartingValues.levelChangeTime;
  int gameScore = 0;
  private float bonusX;
  private float bonusY;
  private float marioX = 0;
  private float marioY = 0;
  private float goalX = 0;
  private float goalY = 0;
  private float screenX;
  private float screenY;
  private float enemyX = generateRandomEnemyStartCoordinates();
  private float enemyY = generateRandomEnemyStartCoordinates();
  private boolean running = true;
  private Canvas canvas;
  private SurfaceHolder surfaceHolder;
  private Context context;

  public int getMarioSpeedLocal() {
	return marioSpeedLocal;
  }

  public void setMarioSpeedLocal(int marioSpeedLocal) {
	this.marioSpeedLocal = marioSpeedLocal;
  }

  public int getEnemySpeedLocal() {
	return enemySpeedLocal;
  }

  public void setEnemySpeedLocal(int enemySpeedLocal) {
	this.enemySpeedLocal = enemySpeedLocal;
  }

  public int getLevelChangeTimeLocal() {
	return levelChangeTimeLocal;
  }

  public void setLevelChangeTimeLocal(int levelChangeTimeLocal) {
	this.levelChangeTimeLocal = levelChangeTimeLocal;
  }

  public int getGameScore() {
	return gameScore;
  }

  public void setGameScore(int gameScore) {
	this.gameScore = gameScore;
	setGameScore(gameScore);
  }

  public void setGoal(float goalX, float goalY) {
	this.goalX = goalX;
	this.goalY = goalY;
  }

  private int generateRandomEnemyStartCoordinates() {
	return (int) ((Math.random() * (900 - 150)) + 150);
  }

  private int generateRandomBonusCoordinatesX() {
	return (int) ((Math.random() * (screenX - 150)) + 150);
  }

  private int generateRandomBonusCoordinatesY() {
	return (int) ((Math.random() * (screenY - 150)) + 150);
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

  private void drawBonus() {
	canvas.drawBitmap(bonus.getBonusBitmap(context), generateRandomBonusCoordinatesX(), generateRandomBonusCoordinatesY(), new Paint());
  }

//  public Rect getRectForBonus() {
//
//	return new Rect(( int ))
//  }


  @Override
  public void run() {

	while (running) {
	  canvas = surfaceHolder.lockCanvas();

	  if (canvas != null) {

		screenX = canvas.getWidth();
		screenY = canvas.getHeight();

		canvas.drawColor(Color.WHITE);

		canvas.drawBitmap(mario.getNextMario(context), marioX, marioY, new Paint());

		canvas.drawBitmap(enemy.getNextEnemy(context), enemyX, enemyY, new Paint());

		canvas.drawBitmap(bonus.getBonusBitmap(context), 900, 900, new Paint());


		if (marioX < goalX) {
		  marioX += GameStartingValues.marioSpeed;
		} else if (marioX > goalX) {
		  marioX -= GameStartingValues.marioSpeed;
		}
		if (Math.abs(marioX - goalX) < GameStartingValues.marioSpeed) {
		  marioX = goalX;
		}

		if (marioY < goalY) {
		  marioY += GameStartingValues.marioSpeed;
		} else if (marioY > goalY) {
		  marioY -= GameStartingValues.marioSpeed;
		}
		if (Math.abs(marioY - goalY) < GameStartingValues.marioSpeed) {
		  marioY = goalY;
		}

		if (enemyX < marioX) {
		  enemyX += GameStartingValues.enemySpeed;
		} else if (marioX < enemyX) {
		  enemyX -= GameStartingValues.enemySpeed;
		}
		if (Math.abs(marioX - enemyX) < GameStartingValues.enemySpeed) {
		  marioX = enemyX;
		}

		if (enemyY < marioY) {
		  enemyY += GameStartingValues.enemySpeed;
		} else if (marioY < enemyY) {
		  enemyY -= GameStartingValues.enemySpeed;
		}
		if (Math.abs(marioY - enemyY) < GameStartingValues.enemySpeed) {
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
}




