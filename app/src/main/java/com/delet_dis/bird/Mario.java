package com.delet_dis.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Mario {

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
