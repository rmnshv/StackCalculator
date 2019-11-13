package ru.spbstu.icst.calculator;

import ru.spbstu.icst.calculator.exception.CalculatorException;
import ru.spbstu.icst.calculator.exception.ExecutionException;

public interface Operation {
    void execute(ExecutionContext context) throws ExecutionException, CalculatorException;
}
