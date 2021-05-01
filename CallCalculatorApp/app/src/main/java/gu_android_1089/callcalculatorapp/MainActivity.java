package gu_android_1089.callcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static final String TEXT = "CALL HIM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.callButton);

        button.setOnClickListener(v -> {
            Uri uri = Uri.parse("example://intent");
            Intent runCalculator = new Intent(Intent.ACTION_VIEW, uri);
            runCalculator.putExtra(TEXT, "There is a job for Black Tulip");

            @SuppressLint("QueryPermissionsNeeded")
            ActivityInfo activityInfo = runCalculator.resolveActivityInfo(getPackageManager(),
                    runCalculator.getFlags());

            if (activityInfo != null){
                startActivity(runCalculator);
            }
        });
    }
}