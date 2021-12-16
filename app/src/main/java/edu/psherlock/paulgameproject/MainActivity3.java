package edu.psherlock.paulgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    TextView tvScore;
    EditText etName;
    int score;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    String currentDateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        score = getIntent().getExtras().getInt("Score");

        tvScore = findViewById(R.id.tvScore);
        etName = findViewById(R.id.etName);

        tvScore.setText(String.valueOf(score));
    }

    public void doSee(View view) {
        Intent page4 = new Intent(this,MainActivity4.class);
        String name = etName.getText().toString();
        DatabaseHandler db = new DatabaseHandler(this);
        db.addHiScore(new HiScore(currentDateandTime, String.valueOf(name), score));
        startActivity(page4);
    }

    public void doAgain(View view) {
        Intent page1 = new Intent(this,MainActivity.class);
        String name = String.valueOf(etName);
        DatabaseHandler db = new DatabaseHandler(this);
        db.addHiScore(new HiScore(currentDateandTime, name, score));
        startActivity(page1);
    }
}