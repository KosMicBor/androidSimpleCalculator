package gu_android_1089.simplecalculator.ui;

import gu_android_1089.simplecalculator.main_logic.Calc;
import gu_android_1089.simplecalculator.main_logic.Operations;

public class CalcPresenter {
    private Calc calculator;
    private CalcViewInterface view;

    public CalcPresenter(Calc calculator, CalcViewInterface view) {
        this.calculator = calculator;
        this.view = view;
    }

    public double calculateResult(double arg1, double arg2, char operand) {

        switch (operand) {
            case '+':
                return calculator.arithmeticOperation(arg1, arg2, Operations.ADD);
            case '-':
                return calculator.arithmeticOperation(arg1, arg2, Operations.SUBTRACTION);
            case '/':
                return calculator.arithmeticOperation(arg1, arg2, Operations.DIVIDE);
            case '*':
                return calculator.arithmeticOperation(arg1, arg2, Operations.MULTIPLY);
        }
        return 0;
    }
}
