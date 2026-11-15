package com.example.inte1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    private EditText etName, etEmail;
    private Spinner spCourse;
    private SeekBar sbLevel;
    private CheckBox cbNewsletter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnNext).setOnClickListener(v -> openSummary());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void openSummary() {
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra(SummaryActivity.EXTRA_NAME, etName.getText().toString().trim());
        intent.putExtra(SummaryActivity.EXTRA_EMAIL, etEmail.getText().toString().trim());
        intent.putExtra(SummaryActivity.EXTRA_COURSE, spCourse.getSelectedItem().toString());
        intent.putExtra(SummaryActivity.EXTRA_LEVEL, sbLevel.getProgress());
        intent.putExtra(SummaryActivity.EXTRA_NEWSLETTER, cbNewsletter.isChecked());

        summaryLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> summaryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String number = result.getData().getStringExtra(SummaryActivity.EXTRA_NUMBER);
                    showRegistered(number);
                }

            });

    private void showRegistered(String number) {
        Snackbar.make(findViewById(R.id.main),
                getString(R.string.registered, number),
                Snackbar.LENGTH_LONG).show();
    }
}