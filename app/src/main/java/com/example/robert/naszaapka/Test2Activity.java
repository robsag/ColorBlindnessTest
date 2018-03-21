package com.example.robert.naszaapka;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Test2Activity extends AppCompatActivity {

    private class TestQuestion {
        private int background;
        private int foreground;
        private String text;
        private String answer1;
        private String answer2;

        TestQuestion(int background, int foreground, String text, String answer1, String answer2) {
            this.background = background;
            this.foreground = foreground;
            this.text = text;
            this.answer1 = answer1;
            this.answer2 = answer2;
        }
        int getBackground() { return background; }
        int getForeground() { return foreground; }
        String getText() { return text; }
        String getAnswer1() { return answer1; }
        String getAnswer2() { return answer2; }
    }

    private ArrayList<TestQuestion> testQuestions;
    private Integer testPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        testPoints = 0;
        testQuestions = new ArrayList<>();

        Intent intent = getIntent();
        final String testDifficulty = intent.getStringExtra("EXTRA_TEST_DIFFICULTY");
        initializeTest(testDifficulty);
        Collections.shuffle(testQuestions);

        final View testBackground = findViewById(R.id.test2_background);
        final TextView testText = findViewById(R.id.test2_text);
        final Button answer1 = findViewById(R.id.answer1_button);
        final Button answer2 = findViewById(R.id.answer2_button);

        testBackground.setBackgroundColor(testQuestions.get(0).getBackground());
        testText.setTextColor(testQuestions.get(0).getForeground());
        testText.setText(testQuestions.get(0).getText());
        answer1.setText(testQuestions.get(0).getAnswer1());
        answer2.setText(testQuestions.get(0).getAnswer2());

        final TextView testTimer = findViewById(R.id.test2_timer);
        final CountDownTimer timer = new CountDownTimer(3000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                testTimer.setText("" + millisUntilFinished / 100 / 10.0);
            }
            @Override
            public void onFinish() {
                testTimer.setText("0.0");
                testQuestions.remove(0);
                if (testQuestions.size() > 0) {
                    testBackground.setBackgroundColor(testQuestions.get(0).getBackground());
                    testText.setTextColor(testQuestions.get(0).getForeground());
                    testText.setText(testQuestions.get(0).getText());
                    answer1.setText(testQuestions.get(0).getAnswer1());
                    answer2.setText(testQuestions.get(0).getAnswer2());
                    start();
                }
                else {
                    cancel();
                    Double testResult = (int)(testPoints / 8.0 * 10000) / 100.0;
                    Intent intent = new Intent(Test2Activity.this, FinishActivity.class);
                    intent.putExtra("EXTRA_TEST_RESULT", testResult.toString());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        }.start();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer1.getText().equals(testText.getText())) {
                    testPoints++;
                }
                testQuestions.remove(0);
                if (testQuestions.size() > 0) {
                    testBackground.setBackgroundColor(testQuestions.get(0).getBackground());
                    testText.setTextColor(testQuestions.get(0).getForeground());
                    testText.setText(testQuestions.get(0).getText());
                    answer1.setText(testQuestions.get(0).getAnswer1());
                    answer2.setText(testQuestions.get(0).getAnswer2());
                    timer.start();
                }
                else {
                    timer.cancel();
                    Double testResult = (int)(testPoints / 8.0 * 10000) / 100.0;
                    Intent intent = new Intent(Test2Activity.this, FinishActivity.class);
                    intent.putExtra("EXTRA_TEST_RESULT", testResult.toString());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer2.getText().equals(testText.getText())) {
                    testPoints++;
                }
                testQuestions.remove(0);
                if (testQuestions.size() > 0) {
                    testBackground.setBackgroundColor(testQuestions.get(0).getBackground());
                    testText.setTextColor(testQuestions.get(0).getForeground());
                    testText.setText(testQuestions.get(0).getText());
                    answer1.setText(testQuestions.get(0).getAnswer1());
                    answer2.setText(testQuestions.get(0).getAnswer2());
                    timer.start();
                }
                else {
                    timer.cancel();
                    Double testResult = (int)(testPoints / 8.0 * 10000) / 100.0;
                    Intent intent = new Intent(Test2Activity.this, FinishActivity.class);
                    intent.putExtra("EXTRA_TEST_RESULT", testResult.toString());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {}

    private void initializeTest(String testDifficulty) {
        switch (testDifficulty) {
            case "basic":
                initializeBasicTest();
                break;
            case "extended":
                initializeExtendedTest();
                break;
        }
    }

    private void initializeBasicTest() {
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorBasicBackgroundTest1),
                getResources().getColor(R.color.colorBasicForegroundTest1),
                getResources().getString(R.string.testBasic1),
                getResources().getString(R.string.testBasic1Answer1),
                getResources().getString(R.string.testBasic1Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorBasicBackgroundTest2),
                getResources().getColor(R.color.colorBasicForegroundTest2),
                getResources().getString(R.string.testBasic2),
                getResources().getString(R.string.testBasic2Answer1),
                getResources().getString(R.string.testBasic2Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorBasicBackgroundTest3),
                getResources().getColor(R.color.colorBasicForegroundTest3),
                getResources().getString(R.string.testBasic3),
                getResources().getString(R.string.testBasic3Answer1),
                getResources().getString(R.string.testBasic3Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorBasicBackgroundTest4),
                getResources().getColor(R.color.colorBasicForegroundTest4),
                getResources().getString(R.string.testBasic4),
                getResources().getString(R.string.testBasic4Answer1),
                getResources().getString(R.string.testBasic4Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorBasicBackgroundTest5),
                getResources().getColor(R.color.colorBasicForegroundTest5),
                getResources().getString(R.string.testBasic5),
                getResources().getString(R.string.testBasic5Answer1),
                getResources().getString(R.string.testBasic5Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorBasicBackgroundTest6),
                getResources().getColor(R.color.colorBasicForegroundTest6),
                getResources().getString(R.string.testBasic6),
                getResources().getString(R.string.testBasic6Answer1),
                getResources().getString(R.string.testBasic6Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorBasicBackgroundTest7),
                getResources().getColor(R.color.colorBasicForegroundTest7),
                getResources().getString(R.string.testBasic7),
                getResources().getString(R.string.testBasic7Answer1),
                getResources().getString(R.string.testBasic7Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorBasicBackgroundTest8),
                getResources().getColor(R.color.colorBasicForegroundTest8),
                getResources().getString(R.string.testBasic8),
                getResources().getString(R.string.testBasic8Answer1),
                getResources().getString(R.string.testBasic8Answer2)));
    }

    private void initializeExtendedTest() {
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorExtendedBackgroundTest1),
                getResources().getColor(R.color.colorExtendedForegroundTest1),
                getResources().getString(R.string.testExtended1),
                getResources().getString(R.string.testExtended1Answer1),
                getResources().getString(R.string.testExtended1Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorExtendedBackgroundTest2),
                getResources().getColor(R.color.colorExtendedForegroundTest2),
                getResources().getString(R.string.testExtended2),
                getResources().getString(R.string.testExtended2Answer1),
                getResources().getString(R.string.testExtended2Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorExtendedBackgroundTest3),
                getResources().getColor(R.color.colorExtendedForegroundTest3),
                getResources().getString(R.string.testExtended3),
                getResources().getString(R.string.testExtended3Answer1),
                getResources().getString(R.string.testExtended3Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorExtendedBackgroundTest4),
                getResources().getColor(R.color.colorExtendedForegroundTest4),
                getResources().getString(R.string.testExtended4),
                getResources().getString(R.string.testExtended4Answer1),
                getResources().getString(R.string.testExtended4Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorExtendedBackgroundTest5),
                getResources().getColor(R.color.colorExtendedForegroundTest5),
                getResources().getString(R.string.testExtended5),
                getResources().getString(R.string.testExtended5Answer1),
                getResources().getString(R.string.testExtended5Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorExtendedBackgroundTest6),
                getResources().getColor(R.color.colorExtendedForegroundTest6),
                getResources().getString(R.string.testExtended6),
                getResources().getString(R.string.testExtended6Answer1),
                getResources().getString(R.string.testExtended6Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorExtendedBackgroundTest7),
                getResources().getColor(R.color.colorExtendedForegroundTest7),
                getResources().getString(R.string.testExtended7),
                getResources().getString(R.string.testExtended7Answer1),
                getResources().getString(R.string.testExtended7Answer2)));
        testQuestions.add(new TestQuestion(
                getResources().getColor(R.color.colorExtendedBackgroundTest8),
                getResources().getColor(R.color.colorExtendedForegroundTest8),
                getResources().getString(R.string.testExtended8),
                getResources().getString(R.string.testExtended8Answer1),
                getResources().getString(R.string.testExtended8Answer2)));
    }

}
