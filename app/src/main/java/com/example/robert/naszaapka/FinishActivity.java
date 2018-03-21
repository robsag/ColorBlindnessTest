package com.example.robert.naszaapka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Intent intent = getIntent();
        Double testResult = Double.parseDouble(intent.getStringExtra("EXTRA_TEST_RESULT"));

        final TextView testResultView = findViewById(R.id.test_result);
        String testResultMessage = testResultView.getText() + " " + testResult + "%";
        testResultView.setText(testResultMessage);

        final TextView testSummaryView = findViewById(R.id.test_summary);
        if (testResult > 75) {
            testSummaryView.setText(R.string.summary1);
        } else if (testResult > 60) {
            testSummaryView.setText(R.string.summary2);
        } else {
            testSummaryView.setText(R.string.summary3);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FinishActivity.this, MenuActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
