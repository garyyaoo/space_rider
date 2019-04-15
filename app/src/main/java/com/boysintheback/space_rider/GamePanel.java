package com.boysintheback.space_rider;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public Point size;
    public int seconds = 0;
    long startTime;
    public boolean alreadyDone = false;
    public boolean alreadyDoneInitial = false;

    private MainThread thread;

    private Player spaceship;

    public Handler handler;


    public GamePanel(Context context) {
        super(context);
        startTime = System.nanoTime();
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

@Override
public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

}

@Override
public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
        spaceship = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.spaceship), size.x/2, size.y/2);
        handler = new Handler(spaceship);
}

@Override
public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace();}
            retry = false;
        }
}

@Override
public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
}

public void update() {
        if (seconds == 0) {
            if (!alreadyDoneInitial) {
                for (int i = 0; i < 50; ++i) {
                    Random r = new Random();
                    int nextPosX = r.nextInt(size.x);
                    int nextPosY = r.nextInt(size.y);
                    handler.addObject(new BGStar(nextPosX, nextPosY, 5));
                    alreadyDoneInitial = true;
                }
            }
        }
        if ((System.nanoTime() - startTime)%7.50 == 0) {
            Random r = new Random();
            int nextStar = r.nextInt(size.x);
            handler.addObject(new BGStar(nextStar, 0, 5));

        }
        if (seconds % 5 == 0){
            if (alreadyDone == false) {
                Random r = new Random();
                int nextObject = r.nextInt(size.x);
                handler.addObject(new Asteroid(nextObject, 0, 15, BitmapFactory.decodeResource(getResources(), R.drawable.asteroid_m)));
                alreadyDone = true;
            }
        }
        handler.updateObjects(this);
}

public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas != null){
            canvas.drawColor(Color.BLACK);
            spaceship.draw(canvas);
            handler.renderObjects(canvas);
        }


}
}
