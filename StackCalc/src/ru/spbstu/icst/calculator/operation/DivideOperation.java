package ru.spbstu.icst.calculator.operation;

import ru.spbstu.icst.calculator.ExecutionContext;
import ru.spbstu.icst.calculator.Operation;
import ru.spbstu.icst.calculator.exception.ExecutionException;

public class DivideOperation implements Operation {
    @Override
    public void execute(ExecutionContext context) throws ExecutionException {
        try {
            double b = context.stack().pop();
            double a = context.stack().pop();
            context.stack().add(a / b);
        } catch (RuntimeException e) {
            throw new ExecutionException("stack error", e);
        }
    }
}
