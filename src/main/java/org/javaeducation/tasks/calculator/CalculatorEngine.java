package org.javaeducation.tasks.calculator;

public class CalculatorEngine implements Calculator {
    private double result = 0;

    @Override
    public Calculator sum(double operand) {
        result += operand;
        return this;
    }

    @Override
    public Calculator minus(double operands) {
        return this;
    }

    @Override
    public Calculator divide(double operands) {
        return this;
    }

    @Override
    public Calculator multiply(double operands) {
        return this;
    }

    @Override
    public Calculator pow(double operand) {
        return this;
    }

    @Override
    public Calculator sqrt(double operand) {
        return this;
    }

    @Override
    public Calculator log(int base, double operand) {
        return this;
    }

    @Override
    public Calculator modulo(int base, double divider) {
        return this;
    }

    @Override
    public double getResult() {
        return result;
    }

    @Override
    public void clearValue() {
        result = 0;
    }
}
