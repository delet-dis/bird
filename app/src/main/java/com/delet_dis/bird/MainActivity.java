package com.delet_dis.bird;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  Button restartButton;
  TextView gameText;
  GameScreen gameScreen;
  TextView gameScore;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
//	setContentView(new House(this));


	setContentView(R.layout.activity_main);

	restartButton = findViewById(R.id.restartButton);
	gameText = findViewById(R.id.gameText);

	gameScreen = findViewById(R.id.gameScreen);

	gameScore = findViewById(R.id.gameScore);

	gameScreen.setListener(new DrawingThread.GameEventListener() {

	  GameScreen.Timer timer;

	  @Override
	  public void gameStopped() {
		runOnUiThread(() -> {
		  restartButton.setVisibility(View.VISIBLE);
		  restartButton.setOnClickListener(v -> {
			gameScreen.setupThread(getApplicationContext());
			restartButton.setVisibility(View.INVISIBLE);
			gameText.setVisibility(View.INVISIBLE);
		  });
		  gameText.setVisibility(View.VISIBLE);
		  gameText.setText(R.string.gameOverText);
		  timer.cancel();
		});
	  }

	  @Override
	  public void scoreChanged(int score) {
		runOnUiThread(() -> gameScore.setText(String.valueOf(score)));
	  }

	  @Override
	  public void throwTimer(GameScreen.Timer timer) {
		this.timer = timer;
	  }

	  @Override
	  public void gamePaused() {
		gameText.setVisibility(View.VISIBLE);
		gameText.setText(R.string.gamePausedText);
	  }

	  @Override
	  public void gameResumed() {
		gameText.setVisibility(View.INVISIBLE);
	  }
	});

  }

}