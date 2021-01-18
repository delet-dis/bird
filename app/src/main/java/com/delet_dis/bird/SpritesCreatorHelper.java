package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpritesCreatorHelper {

  public static class Enemy {

	private int currentEnemyNumber = 0;

	public Bitmap getNextEnemy(Context context) {
	  Bitmap enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_1);

	  switch (currentEnemyNumber) {
		case 0:
		  enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_1);
		  break;
		case 2:
		  enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_2);
		  break;
		case 4:
		  enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_3);
		  break;
		case 6:
		  enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_4);
		  break;
		case 8:
		  enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_5);
		  break;
		case 10:
		  enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_6);
		  break;
		case 12:
		  enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_7);
		  break;
		case 14:
		  enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_8);
		  break;
	  }

	  enemy = Bitmap.createScaledBitmap(enemy, 200, 200, false);

	  currentEnemyNumber++;

	  if (currentEnemyNumber > 14) {
		currentEnemyNumber = 0;
	  }

	  return enemy;
	}
  }

  public static class Mario {

	private int currentMarioNumber = 0;

	public Bitmap getNextMario(Context context) {
	  Bitmap mario = BitmapFactory.decodeResource(context.getResources(), R.drawable.mario_1);

	  switch (currentMarioNumber) {
		case 0:
		  mario = BitmapFactory.decodeResource(context.getResources(), R.drawable.mario_1);
		  break;
		case 1:
		  mario = BitmapFactory.decodeResource(context.getResources(), R.drawable.mario_2);
		  break;
		case 2:
		  mario = BitmapFactory.decodeResource(context.getResources(), R.drawable.mario_3);
		  break;
	  }

	  mario = Bitmap.createScaledBitmap(mario, 200, 200, false);

	  currentMarioNumber++;

	  if (currentMarioNumber > 2) {
		currentMarioNumber = 0;
	  }

	  return mario;
	}
  }

  public static class Bonus{
    public Bitmap getBonusBitmap(Context context){
	  return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bonus), 200, 200, false);
	}
  }

}
