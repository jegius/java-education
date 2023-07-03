package org.javaeducation.tasks.calculator;

public interface Calculator {
    Calculator sum(double operands);
    Calculator minus(double operands);
    Calculator divide(double operands);
    Calculator multiply(double operands);
    Calculator pow(double operand);
    Calculator sqrt(double operand);
    Calculator log(int base, double operand);
    Calculator modulo(int base, double divider);
    double getResult();
    void clearValue();
}
