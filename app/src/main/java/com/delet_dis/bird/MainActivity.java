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


  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
//	setContentView(new House(this));


	setContentView(R.layout.activity_main);

	restartButton = findViewById(R.id.button);
	gameOverText = findViewById(R.id.gameOverText);

	gameScreen = findViewById(R.id.gameScreen);


	gameScreen.setGameEventListener(new DrawingThread.GameEventListener() {
	  @Override
	  public void gameStarted() {
		restartButton.setVisibility(View.INVISIBLE);
		gameOverText.setVisibility(View.INVISIBLE);
	  }

	  @Override
	  public void gameStopped() {
		restartButton.setVisibility(View.VISIBLE);
		restartButton.setOnClickListener(v -> {

		});
		gameOverText.setVisibility(View.VISIBLE);
	  }
	});

  }

}