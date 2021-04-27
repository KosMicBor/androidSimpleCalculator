package gu_android_1089.simplecalculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gu_android_1089.simplecalculator.R;
import gu_android_1089.simplecalculator.main_logic.ThemesVariants;

public class ThemeChooserActivity extends AppCompatActivity {
    private ThemeSaver themeSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeSaver = new ThemeSaver(this);
        setContentView(R.layout.activity_theme_chooser);

        Button buttonBlueTheme = findViewById(R.id.firstStyleButton);
        Button buttonDarkTheme = findViewById(R.id.nightStyleButton);
        Button buttonPunkTheme = findViewById(R.id.secondStyleButton);
        Button buttonGreenTheme = findViewById(R.id.thirdStyleButton);

        buttonBlueTheme.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("theme", "THEME_BLUE_STANDARD");
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });

        buttonDarkTheme.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("theme", "THEME_NIGHT");
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });

        buttonPunkTheme.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("theme", "THEME_PUNK");
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });

        buttonGreenTheme.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("theme", "THEME_GREEN");
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
    }
}