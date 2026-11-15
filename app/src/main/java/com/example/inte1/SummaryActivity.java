package com.example.inte1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_COURSE = "extra_course";
    public static final String EXTRA_LEVEL = "extra_level";
    public static final String EXTRA_NEWSLETTER = "extra_newsletter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView tvSummary = findViewById(R.id.tvSummary);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        String course = intent.getStringExtra(EXTRA_COURSE);
        int level = intent.getIntExtra(EXTRA_LEVEL, 0);
        boolean newsletter = intent.getBooleanExtra(EXTRA_NEWSLETTER, false);

        String newsletterText = newsletter ? getString(R.string.yes_short) : getString(R.string.no_short);
        String summaryText = getString(R.string.summary_format, name, email, course, level, newsletterText);
        tvSummary.setText(summaryText);
    }
}