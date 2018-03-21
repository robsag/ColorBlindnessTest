package com.example.robert.naszaapka;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings2Activity extends AppCompatActivity {

    private String test2Difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        test2Difficulty = "";
        final Button basic_button = findViewById(R.id.basic_button);
        final Button extended_button = findViewById(R.id.extended_button);

        basic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2Difficulty = "basic";
                v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_clicked_easy));
                extended_button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_hard));
            }
        });

        extended_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2Difficulty = "extended";
                v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_clicked_hard));
                basic_button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_easy));
            }
        });

        final Button start2_button = findViewById(R.id.start2_button);
        start2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!test2Difficulty.equals("")) {
                    Intent intent = new Intent(Settings2Activity.this, Test2Activity.class);
                    intent.putExtra("EXTRA_TEST_DIFFICULTY", test2Difficulty);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
                else {
                    Toast t = Toast.makeText(getApplicationContext(), R.string.difficulty_settings2, Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                }
            }
        });
    }
}
