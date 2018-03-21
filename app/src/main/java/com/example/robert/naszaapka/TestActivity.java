package com.example.robert.naszaapka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    private class Pair<F, S> {
        private F first;
        private S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
        F getFirst() { return first; }
        S getSecond() { return second; }
    }

    private Integer redPoints;
    private Integer greenPoints;
    private Integer bluePoints;

    private ArrayList<Pair<Integer, Integer>> testImagesRed;
    private ArrayList<Pair<Integer, Integer>> testImagesGreen;
    private ArrayList<Pair<Integer, Integer>> testImagesBlue;

    private ArrayList<Pair<Pair<Integer, Integer>, String>> test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        redPoints = 0;
        greenPoints = 0;
        bluePoints = 0;
        testImagesRed = new ArrayList<>();
        testImagesGreen = new ArrayList<>();
        testImagesBlue = new ArrayList<>();
        test = new ArrayList<>();

        Intent intent = getIntent();
        String testDifficulty = intent.getStringExtra("EXTRA_TEST_DIFFICULTY");
        initializeTestImagesArray(testDifficulty);
        generateTest();
        Collections.shuffle(test);

        final ImageView test_image = findViewById(R.id.test_image);
        final EditText answer = findViewById(R.id.answer);

        test_image.setImageResource(test.get(0).getFirst().getFirst());

        answer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE && !v.getText().toString().equals("")) {
                    if (Integer.parseInt(v.getText().toString()) == test.get(0).getFirst().getSecond()) {
                        switch (test.get(0).getSecond()) {
                            case "red":
                                redPoints++;
                                break;
                            case "green":
                                greenPoints++;
                                break;
                            case "blue":
                                bluePoints++;
                                break;
                        }
                        Toast t = Toast.makeText(getApplicationContext(), R.string.correct_answer, Toast.LENGTH_SHORT);
                        t.setGravity(Gravity.CENTER, 0, 0);
                        t.show();
                    } else {
                        Toast t = Toast.makeText(getApplicationContext(), R.string.incorrect_answer, Toast.LENGTH_SHORT);
                        t.setGravity(Gravity.CENTER, 0, 0);
                        t.show();
                    }
                }
                else {
                    Toast t = Toast.makeText(getApplicationContext(), R.string.incorrect_answer, Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                }
                test.remove(0);
                if (test.size() > 0) {
                    answer.setText("");
                    test_image.setImageResource(test.get(0).getFirst().getFirst());
                }
                else {
                    Double testResult = (int)((redPoints + greenPoints + bluePoints) / 30.0 * 10000) / 100.0;
                    Intent intent = new Intent(TestActivity.this, FinishActivity.class);
                    intent.putExtra("EXTRA_TEST_RESULT", testResult.toString());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {}

    private void initializeTestImagesArray(String testDifficulty) {
        switch (testDifficulty){
            case "easy":
                initializeEasy();
                break;
            case "hard":
                initializeHard();
                break;
        }
    }

    private void generateTest() {
        Random random = new Random();

        for(int i = 0; i < 10; i++) {
            int r = random.nextInt(testImagesRed.size());
            test.add(new Pair<>(testImagesRed.get(r), "red"));
            testImagesRed.remove(r);
        }

        for(int i = 0; i < 10; i++) {
            int r = random.nextInt(testImagesGreen.size());
            test.add(new Pair<>(testImagesGreen.get(r), "green"));
            testImagesGreen.remove(r);
        }

        for(int i = 0; i < 10; i++) {
            int r = random.nextInt(testImagesBlue.size());
            test.add(new Pair<>(testImagesBlue.get(r), "blue"));
            testImagesBlue.remove(r);
        }
    }

    private void initializeEasy() {
        initializeRedEasy();
        initializeGreenEasy();
        initializeBlueEasy();
    }

    private void initializeHard() {
        initializeRedHard();
        initializeGreenHard();
        initializeBlueHard();
    }

    private void initializeRedEasy() {
        testImagesRed.add(new Pair<>(R.drawable.red_easy_003 , 3));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_03 , 3));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_09 , 9));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_10 , 10));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_14 , 14));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_16 , 16));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_21 , 21));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_23 , 23));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_29 , 29));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_32 , 32));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_36 , 36));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_38 , 38));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_45 , 45));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_52 , 52));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_56 , 56));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_061 ,61 ));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_61 , 61));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_63 , 63));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_69 , 69));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_72 , 72));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_80 , 80));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_89 , 89));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_90 , 90));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_092 ,92 ));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_92 , 92));
        testImagesRed.add(new Pair<>(R.drawable.red_easy_94 , 94));
    }

    private void initializeRedHard() {
        testImagesRed.add(new Pair<>(R.drawable.red_hard_001, 1));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_01, 1));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_07, 7));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_12, 12));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_18, 18));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_25, 25));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_32, 32));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_043, 43));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_43, 43));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_50, 50));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_54, 54));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_61, 61));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_63, 63));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_65, 65));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_074, 74));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_74, 74));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_85, 85));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_87, 87));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_89, 89));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_0090, 90));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_090, 90));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_90, 90));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_92, 92));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_96, 96));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_098, 98));
        testImagesRed.add(new Pair<>(R.drawable.red_hard_98, 98));
    }

    private void initializeGreenEasy() {
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_05, 5));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_07, 7));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_12, 12));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_16, 16));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_18, 18));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_25, 25));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_27, 27));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_30, 30));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_32, 32));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_34, 34));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_36, 36));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_038, 38));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_38, 38));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_41, 41));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_43, 43));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_47, 47));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_050, 50));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_50, 50));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_52, 52));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_58, 58));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_61, 61));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_70, 70));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_72, 72));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_76, 76));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_78, 78));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_83, 83));
        testImagesGreen.add(new Pair<>(R.drawable.green_easy_94, 94));
    }

    private void initializeGreenHard() {
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_07, 7));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_009, 9));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_09, 9));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_11, 11));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_16, 16));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_018, 18));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_18, 18));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_21, 21));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_27, 27));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_32, 32));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_38, 38));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_41, 41));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_45, 45));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_47, 47));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_49, 49));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_52, 52));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_56, 56));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_63, 63));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_65, 65));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_069, 69));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_69, 69));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_70, 70));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_076, 76));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_76, 76));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_078, 78));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_78, 78));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_89, 89));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_90, 90));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_93, 93));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_94, 94));
        testImagesGreen.add(new Pair<>(R.drawable.green_hard_96, 96));
    }

    private void initializeBlueEasy() {
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_09, 9));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_14, 14));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_16, 16));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_21, 21));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_25, 25));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_029, 29));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_29, 29));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_32, 32));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_38, 38));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_41, 41));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_45, 45));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_47, 47));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_049, 49));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_49, 49));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_50, 50));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_52, 52));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_56, 56));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_58, 58));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_67, 67));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_69, 69));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_76, 76));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_81, 81));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_89, 89));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_90, 90));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_92, 92));
        testImagesBlue.add(new Pair<>(R.drawable.blue_easy_94, 94));
    }

    private void initializeBlueHard() {
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_01, 1));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_14, 14));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_16, 16));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_025, 25));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_25, 25));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_27, 27));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_29, 29));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_30, 30));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_34, 34));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_36, 36));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_38, 38));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_43, 43));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_049, 49));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_49, 49));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_52, 52));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_054, 54));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_54, 54));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_56, 56));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_58, 58));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_63, 63));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_65, 65));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_0067, 67));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_067, 67));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_67, 67));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_72, 72));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_74, 74));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_92, 92));
        testImagesBlue.add(new Pair<>(R.drawable.blue_hard_96, 96));
    }
}
