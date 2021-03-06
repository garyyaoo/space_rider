package com.boysintheback.space_rider;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final GamePanel g = new GamePanel(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getRealSize(p);
        g.size = p;
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                g.seconds++;
                g.alreadyDone = false;
                g.doneReset = false;
                g.fuelBool = false;
            }
        };
        Timer timer = new Timer();
        timer.schedule(t, 1000, 1000);
        setContentView(g);
    }
}
