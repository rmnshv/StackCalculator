package ru.spbstu.icst.calculator;

import ru.spbstu.icst.calculator.exception.SyntaxException;

public interface OperationFactory {
    Operation create(String input) throws SyntaxException;
}
