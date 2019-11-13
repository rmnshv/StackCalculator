package ru.spbstu.icst.calculator.operation;

import ru.spbstu.icst.calculator.Operation;
import ru.spbstu.icst.calculator.OperationFactory;
import ru.spbstu.icst.calculator.exception.SyntaxException;

import java.util.Arrays;

public class OperationFactoryImpl implements OperationFactory {
    @Override
    public Operation create(String input) throws SyntaxException {
        final String[] parts = input.split("\\s+");
        final String operation = parts[0];
        final String[] operationArgs = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[]{};

        switch (operation.toLowerCase()) {
            case "define":
                return new DefineOperation(operationArgs);

            case "push":
                return new PushOperation(operationArgs);

            case "exp":
                return new ExpOperation(operationArgs);

            case "print":
                return new PrintOperation();

            case "+":
                return new PlusOperation();

            case "-":
                return new MinusOperation();

            case "*":
                return new MultiplyOperation();

            case "/":
                return new DivideOperation();

            case "!":
                return new SqrtOperation();

            case "pop":
                return new PopOperation();
        }

        throw new SyntaxException(String.format("unknown command '%s'", operation));
    }
}