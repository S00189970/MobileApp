package edu.psherlock.paulgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int red = 1;
    private final int blue = 2;
    private final int orange = 3;
    private final int green = 4;

    int score = 0;

    Animation anim;

    StringBuilder sb;
    Button btnBlue, btnRed,btnOrange,btnGreen;
    TextView tvResult;

    int sequenceCount = 4, n = 0;
    int[] gameSequence = new int[120];
    int arrayIndex = 0;

    CountDownTimer ct = new CountDownTimer(6000,1500) {
        @Override
        public void onTick(long l) {
            oneButton();
        }

        @Override
        public void onFinish() {
            for (int i = 0; i < arrayIndex; i++){
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            }
            StartIntent();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DatabaseHandler db = new DatabaseHandler(this);
//
//        db.emptyHiScores();

        btnRed = findViewById(R.id.btnRed);
        btnBlue = findViewById(R.id.btnBlue);
        btnOrange = findViewById(R.id.btnOrange);
        btnGreen = findViewById(R.id.btnGreen);



  //      score = getIntent().getExtras().getInt("Score");
    }

    private void oneButton(){
        n = getRandom(sequenceCount);

        switch (n) {
            case 1:
                flashButton(btnRed);
                gameSequence[arrayIndex++] = red;
                break;
            case 2:
                flashButton(btnBlue);
                gameSequence[arrayIndex++] = blue;
                break;
            case 3:
                flashButton(btnOrange);
                gameSequence[arrayIndex++] = orange;
                break;
            case 4:
                flashButton(btnGreen);
                gameSequence[arrayIndex++] = green;
                break;
            default:
                break;
        }
    }
    private void flashButton(Button button) {

        anim = new AlphaAnimation(1,0);
        anim.setDuration(1000);

        anim.setRepeatCount(0);
        button.startAnimation(anim);

    }

    private int getRandom(int maxValue) {
        return ((int) ((Math.random() * maxValue) + 1));
    }

    public void doPlay(View view) {
        ct.start();

    }
    public void StartIntent() {
        Intent pageTwo = new Intent(this,MainActivity2.class);
        pageTwo.putExtra("Score", score);
        pageTwo.putExtra("Sequence", gameSequence);
        startActivity(pageTwo);
    }
}