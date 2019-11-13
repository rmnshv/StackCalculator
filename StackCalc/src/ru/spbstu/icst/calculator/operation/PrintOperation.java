package ru.spbstu.icst.calculator.operation;

import ru.spbstu.icst.calculator.ExecutionContext;
import ru.spbstu.icst.calculator.Operation;
import ru.spbstu.icst.calculator.exception.ExecutionException;

public class PrintOperation implements Operation {

    @Override
    public void execute(ExecutionContext context) throws ExecutionException {
        try {
            System.out.println(context.stack().peek());
        } catch (RuntimeException e) {
            throw new ExecutionException("stack error", e);
        }
    }
}
