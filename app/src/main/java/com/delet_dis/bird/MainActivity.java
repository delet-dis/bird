package com.delet_dis.bird;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  Button restartButton;
  TextView gameOverText;
  GameScreen gameScreen;
  TextView gameScore;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
//	setContentView(new House(this));


	setContentView(R.layout.activity_main);

	restartButton = findViewById(R.id.restartButton);
	gameOverText = findViewById(R.id.gameOverText);

	gameScreen = findViewById(R.id.gameScreen);

	gameScore = findViewById(R.id.gameScore);

	gameScreen.setListener(new DrawingThread.GameEventListener() {

	  @Override
	  public void gameStopped() {
		runOnUiThread(() -> {
		  restartButton.setVisibility(View.VISIBLE);
		  restartButton.setOnClickListener(v -> {
			gameScreen.setupThread(getApplicationContext());
			restartButton.setVisibility(View.INVISIBLE);
			gameOverText.setVisibility(View.INVISIBLE);
		  });
		  gameOverText.setVisibility(View.VISIBLE);
		});
	  }

	  @Override
	  public void scoreChanged(int score) {
		runOnUiThread(() -> gameScore.setText(score));
	  }
	});

  }

}