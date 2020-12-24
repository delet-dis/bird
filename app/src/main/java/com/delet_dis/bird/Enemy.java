package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Enemy {

  private int currentEnemyNumber = 0;

  public Bitmap getNextEnemy(Context context) {
	Bitmap enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_1);

	switch (currentEnemyNumber) {
	  case 0:
		enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_1);
		break;
	  case 1:
		enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_2);
		break;
	  case 2:
		enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_3);
		break;
	  case 3:
		enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_4);
		break;
	  case 4:
		enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_5);
		break;
	  case 5:
		enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_6);
		break;
	  case 6:
		enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_7);
		break;
	  case 7:
		enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_8);
		break;
	}

	enemy = Bitmap.createScaledBitmap(enemy, 200, 200, false);

	currentEnemyNumber++;

	if (currentEnemyNumber > 2) {
	  currentEnemyNumber = 0;
	}

	return enemy;
  }
}
