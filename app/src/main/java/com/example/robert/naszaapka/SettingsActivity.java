package com.example.robert.naszaapka;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private String testDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        testDifficulty = "";
        final Button easy_button = findViewById(R.id.easy_button);
        final Button hard_button = findViewById(R.id.hard_button);

        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDifficulty = "easy";
                v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_clicked_easy));
                hard_button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_hard));
            }
        });

        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDifficulty = "hard";
                v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_clicked_hard));
                easy_button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_easy));
            }
        });

        final Button start_button = findViewById(R.id.start_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!testDifficulty.equals("")) {
                    Intent intent = new Intent(SettingsActivity.this, TestActivity.class);
                    intent.putExtra("EXTRA_TEST_DIFFICULTY", testDifficulty);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
                else {
                    Toast t = Toast.makeText(getApplicationContext(), R.string.difficulty_settings, Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                }
            }
        });
    }
}
