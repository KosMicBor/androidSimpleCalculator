package gu_android_1089.simplecalculator.main_logic;

public class Calculator implements Calc {

    @Override
    public double arithmeticOperation(double argOne, double argTwo, Operations operation) {

        switch (operation) {
            case ADD:
                return argOne + argTwo;
            case SUBTRACTION:
                return argOne - argTwo;
            case DIVIDE:
                return argOne / argTwo;
            case MULTIPLY:
                return argOne * argTwo;
        }

        return 0;
    }
}
