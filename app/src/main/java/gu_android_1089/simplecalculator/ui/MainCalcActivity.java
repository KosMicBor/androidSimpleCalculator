package gu_android_1089.simplecalculator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gu_android_1089.simplecalculator.R;
import gu_android_1089.simplecalculator.main_logic.Calculator;
import gu_android_1089.simplecalculator.main_logic.ThemesVariants;

public class MainCalcActivity extends AppCompatActivity implements CalcViewInterface {
    private final int REQUEST_THEME_TYPE = 255;
    private final int ONE_VALUE = 1;
    private final static String KEY_DATA_SAVER = "DataSaver";
    private final static  String TEXT = "CALL HIM";

    private CalcPresenter presenter;
    private ThemeSaver themeSaver;
    private TextView resultFrame;
    private double result = 0;
    private ArrayList<String> resultList = new ArrayList<>();
    private double arg1 = 0;
    private double arg2 = 0;
    private char operand = ' ';
    private CalcDataSaver dataSaver = new CalcDataSaver(arg1, arg2, result, resultList);
    private final int[] arrayButtonsIDs = {
            R.id.button_zero, R.id.button_one, R.id.button_two, R.id.button_three,
            R.id.button_four, R.id.button_five, R.id.button_six, R.id.button_seven,
            R.id.button_eight, R.id.button_nine
    };

    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        themeSaver = new ThemeSaver(this);
        setTheme(themeSaver.getCurrentTheme().getResource());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_main);
        resultFrame = findViewById(R.id.resultShowingField);
        Button buttonDot = findViewById(R.id.button_dot);
        Button buttonReset = findViewById(R.id.button_reset);
        Button buttonBackspace = findViewById(R.id.button_backspace);
        Button buttonDivide = findViewById(R.id.button_divide);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonMinus = findViewById(R.id.button_minus);
        Button buttonPlus = findViewById(R.id.button_plus);
        Button buttonEquals = findViewById(R.id.button_equals);
        Button buttonPercent = findViewById(R.id.button_percent);
        Button chooseThemeButton = findViewById(R.id.chooseThemeButton);
        presenter = new CalcPresenter(new Calculator(), this);


        setNumberButtonListeners();

        buttonDot.setOnClickListener(v -> {
            resultList.add(".");
            viewResult(getStringOfArray(resultList));
        });

        buttonPercent.setOnClickListener(v -> {
            resultList.add("%");
            viewResult(getStringOfArray(resultList));
        });

        buttonPlus.setOnClickListener(v -> {
            try {
                if (resultList.isEmpty()) {
                    arg1 = result;
                } else {
                    arg1 = Double.parseDouble(getStringOfArray(resultList));
                    operand = '+';
                }
                resultList.clear();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введено слишком большое число",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonDivide.setOnClickListener(v -> {
            try {
                if (resultList.isEmpty()) {
                    arg1 = result;
                } else {
                    arg1 = Double.parseDouble(getStringOfArray(resultList));
                    operand = '/';
                }
                resultList.clear();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введено слишком большое число",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonMinus.setOnClickListener(v -> {
            try {
                if (resultList.isEmpty()) {
                    arg1 = result;
                } else {
                    arg1 = Double.parseDouble(getStringOfArray(resultList));
                    operand = '-';
                }
                resultList.clear();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введено слишком большое число",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonMultiply.setOnClickListener(v -> {
            try {
                if (resultList.isEmpty()) {
                    arg1 = result;
                } else {
                    arg1 = Double.parseDouble(getStringOfArray(resultList));
                    operand = '*';
                }
                resultList.clear();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введено слишком большое число",
                        Toast.LENGTH_LONG).show();
            }
        });


        buttonEquals.setOnClickListener(v -> {
            try {
                if (resultList != null && !resultList.isEmpty()) {
                    arg2 = Double.parseDouble(getStringOfArray(resultList));
                    result = presenter.calculateResult(arg1, arg2, operand);
                    viewResult(Double.toString(result));
                    resultList.clear();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введено слишком большое число",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonReset.setOnClickListener(v -> {
            resultList.clear();
            viewResult("0");
        });

        buttonBackspace.setOnClickListener(v -> {
            final int resListSize = resultList.size() - ONE_VALUE;
            if (!resultList.isEmpty()) {
                resultList.remove(resListSize);
                viewResult(getStringOfArray(resultList));
            }

            if (resultList.isEmpty()) {
                viewResult("0");
            }

            showTextFromSecondApp();

        });

        chooseThemeButton.setOnClickListener(v -> {
            Intent runThemeChooser = new Intent(MainCalcActivity.this,
                    ThemeChooserActivity.class);
            startActivityForResult(runThemeChooser, REQUEST_THEME_TYPE);
        });
    }

    @Override
    public void viewResult(String result) {
        resultFrame.setText(result);
    }

    private String getStringOfArray(ArrayList<String> list) {
        StringBuilder builder = new StringBuilder();

        for (String elem : list) {
            builder.append(elem);
        }

        return builder.toString();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        dataSaver.setArg1(arg1);
        dataSaver.setArg2(arg2);
        dataSaver.setResult(result);
        dataSaver.setArgList(resultList);
        outState.putParcelable(KEY_DATA_SAVER, dataSaver);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle outState) {
        super.onRestoreInstanceState(outState);
        dataSaver = outState.getParcelable(KEY_DATA_SAVER);
        arg1 = dataSaver.getArg1();
        arg1 = dataSaver.getArg2();
        result = dataSaver.getResult();
        resultList = (ArrayList<String>) dataSaver.getArgList().clone();
        viewResult(getStringOfArray(resultList));
        if (resultList.isEmpty()) {
            viewResult(String.valueOf(result));
        }

    }

    private void setNumberButtonListeners() {

        for (int i = 0; i < arrayButtonsIDs.length; i++) {
            int index = i;
            findViewById(arrayButtonsIDs[i]).setOnClickListener(v -> {
                resultList.add(String.valueOf(index));
                viewResult(getStringOfArray(resultList));
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_THEME_TYPE) {
            if (resultCode == Activity.RESULT_OK) {
                ThemesVariants theme = ThemesVariants.valueOf(data.getStringExtra("theme"));
                themeSaver.setCurrentTheme(theme);
                recreate();
            }
        }
    }

     protected void showTextFromSecondApp()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle == null){
            return;
        }
        String text = bundle.getString(TEXT);Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }
}