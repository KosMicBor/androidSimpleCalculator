package gu_android_1089.simplecalculator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gu_android_1089.simplecalculator.R;
import gu_android_1089.simplecalculator.main_logic.Calculator;

public class MainCalcActivity extends AppCompatActivity implements CalcViewInterface {
    private final int ONE_VALUE = 1;
    private final static String KEY_DATA_SAVER = "DataSaver";

    private CalcPresenter presenter;
    private TextView resultFrame;
    private double result = 0;
    private ArrayList<String> resultList = new ArrayList<>();
    private double arg1 = 0;
    private double arg2 = 0;
    private char operand = ' ';
    private CalcDataSaver dataSaver = new CalcDataSaver(arg1, arg2, result, resultList);

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_main);
        resultFrame = findViewById(R.id.resultShowingField);
        Button buttonOne = findViewById(R.id.button_one);
        Button buttonTwo = findViewById(R.id.button_two);
        Button buttonThree = findViewById(R.id.button_three);
        Button buttonFour = findViewById(R.id.button_four);
        Button buttonFive = findViewById(R.id.button_five);
        Button buttonSix = findViewById(R.id.button_six);
        Button buttonSeven = findViewById(R.id.button_seven);
        Button buttonEight = findViewById(R.id.button_eight);
        Button buttonNine = findViewById(R.id.button_nine);
        Button buttonZero = findViewById(R.id.button_zero);
        Button buttonDot = findViewById(R.id.button_dot);
        Button buttonReset = findViewById(R.id.button_reset);
        Button buttonBackspace = findViewById(R.id.button_backspace);
        Button buttonDivide = findViewById(R.id.button_divide);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonMinus = findViewById(R.id.button_minus);
        Button buttonPlus = findViewById(R.id.button_plus);
        Button buttonEquals = findViewById(R.id.button_equals);
        Button buttonPercent = findViewById(R.id.button_percent);

        presenter = new CalcPresenter(new Calculator(), this);

        buttonOne.setOnClickListener(v -> {
            resultList.add("1");
            viewResult(getStringOfArray(resultList));
        });

        buttonTwo.setOnClickListener(v -> {
            resultList.add("2");
            viewResult(getStringOfArray(resultList));
        });

        buttonThree.setOnClickListener(v -> {
            resultList.add("3");
            viewResult(getStringOfArray(resultList));
        });

        buttonFour.setOnClickListener(v -> {
            resultList.add("4");
            viewResult(getStringOfArray(resultList));
        });

        buttonFive.setOnClickListener(v -> {
            resultList.add("5");
            viewResult(getStringOfArray(resultList));
        });

        buttonSix.setOnClickListener(v -> {
            resultList.add("6");
            viewResult(getStringOfArray(resultList));
        });

        buttonSeven.setOnClickListener(v -> {
            resultList.add("7");
            viewResult(getStringOfArray(resultList));
        });

        buttonEight.setOnClickListener(v -> {
            resultList.add("8");
            viewResult(getStringOfArray(resultList));
        });

        buttonNine.setOnClickListener(v -> {
            resultList.add("9");
            viewResult(getStringOfArray(resultList));
        });

        buttonZero.setOnClickListener(v -> {
            resultList.add("0");
            viewResult(getStringOfArray(resultList));
        });

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
                arg1 = Double.parseDouble(getStringOfArray(resultList));
                operand = '+';
                resultList.clear();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введено слишком большое число",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonDivide.setOnClickListener(v -> {
            try {
                arg1 = Double.parseDouble(getStringOfArray(resultList));
                operand = '/';
                resultList.clear();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введено слишком большое число",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonMinus.setOnClickListener(v -> {
            try {
                arg1 = Double.parseDouble(getStringOfArray(resultList));
                operand = '-';
                resultList.clear();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введено слишком большое число",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonMultiply.setOnClickListener(v -> {
            try {
                arg1 = Double.parseDouble(getStringOfArray(resultList));
                operand = '*';
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
            if (resultList != null && !resultList.isEmpty()) {
                resultList.remove(resListSize);
                viewResult(getStringOfArray(resultList));
            }

            if (resultList.isEmpty()) {
                viewResult("0");
            }

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
}