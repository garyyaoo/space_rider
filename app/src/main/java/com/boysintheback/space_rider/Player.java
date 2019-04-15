package com.boysintheback.space_rider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Player {

    private boolean progress;
    public boolean isCollided;
    private int x;
    private int y;
    private static int speed = 10;
    public Bitmap image;
    private int angle =0 ;



    public Player(Bitmap bmp, int x, int y) {
        Bitmap resizedBMP = Bitmap.createScaledBitmap(bmp, 128, 128, false);
        image = resizedBMP;
        this.x = x;
        this.y = y;
        progress = true;
        isCollided = false;
    }

    public void draw(Canvas c) {
        c.drawBitmap(image, x, y, null);
    }

    public void update(boolean left) {
        if (left){
            this.x -= 30;
        } else this.x += 30;
    }

    public void render(Canvas c) {
    }
}
