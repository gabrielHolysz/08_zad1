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
    public static final String EXTRA_NUMBER = "extra_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView tvSummary = findViewById(R.id.tvSummary);

        // Odczytanie danych z intencji jawnej
        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        String course = intent.getStringExtra(EXTRA_COURSE);
        int level = intent.getIntExtra(EXTRA_LEVEL, 0);
        boolean newsletter = intent.getBooleanExtra(EXTRA_NEWSLETTER, false);

        String newsletterText = newsletter ? getString(R.string.yes_short) : getString(R.string.no_short);

        // Sformatowanie tekstu podsumowania
        String summaryText = getString(R.string.summary_format, name, email, course, level, newsletterText);
        tvSummary.setText(summaryText);

        // Przycisk POTWIERDŹ - zwrot numeru zgłoszenia
        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_NUMBER, "2026/001");
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Przycisk POPRAW DANE - powrót bez zapisu
        findViewById(R.id.btnEdit).setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        // ==========================================
        // COMMMIT 4: UDOSTĘPNIANIE (INTENCJA NIEJAWNA)
        // ==========================================
        findViewById(R.id.btnShare).setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, summaryText);

            // Wywołanie okna wyboru aplikacji (Chooser)
            Intent chooser = Intent.createChooser(shareIntent, getString(R.string.share_via));
            startActivity(chooser);
        });
    }
}