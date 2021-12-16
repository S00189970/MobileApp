package edu.psherlock.paulgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mSensor;

    private final int red = 1;
    private final int blue = 2;
    private final int orange = 3;
    private final int green = 4;

    float x;
    float y;
    float z;

    int score;
    int[] gameSequence;

    int counter;
    int s1;
    int s2;
    int s3;
    int s4;

    CountDownTimer ct = new CountDownTimer(10000,1) {
        @Override
        public void onTick(long l) {

            switch(counter){
                case 0:
                    checkSequence(s1);
                    break;
                case 1:
                    checkSequence(s2);
                    break;
                case 2:
                    checkSequence(s3);
                    break;
                case 3:
                    checkSequence(s4);
                    break;
                default:
                    ct.cancel();
                    StartIntent();
                    break;
            }
        }
        @Override
        public void onFinish() {
            StartIntent();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        counter = 0;
        score = getIntent().getExtras().getInt("Score");
        gameSequence = getIntent().getIntArrayExtra("Sequence");

        s1 = gameSequence[0];
        s2 = gameSequence[1];
        s3 = gameSequence[2];
        s4 = gameSequence[3];

        ct.start();

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    private void checkSequence(int seq) {

        switch (seq){

            case 1:
                if(y > -0.3f){
                    score++;
                    counter++;
                }
                break;
            case 2:
                if(x < 0.4f){
                    score++;
                    counter++;
                }
                break;
            case 3:
                if(x > 0.5f){
                    score++;
                    counter++;
                }
                break;
            case 4:
                if(y < -0.5f){
                    score++;
                    counter++;
                }
                break;
            default:
                break;
        }

    }
    public void StartIntent() {


        if(counter == 4){
            Intent page1 = new Intent(this,MainActivity.class);
            page1.putExtra("Score", score);
            startActivity(page1);
        }
        else {
            Intent page3 = new Intent(this,MainActivity3.class);
            page3.putExtra("Score", score);
            startActivity(page3);
        }
    }
}