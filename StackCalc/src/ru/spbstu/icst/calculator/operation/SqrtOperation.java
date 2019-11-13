package ru.spbstu.icst.calculator.operation;

import ru.spbstu.icst.calculator.ExecutionContext;
import ru.spbstu.icst.calculator.Operation;
import ru.spbstu.icst.calculator.exception.ExecutionException;

public class SqrtOperation implements Operation {
    @Override
    public void execute(ExecutionContext context) throws ExecutionException {
        try{
           context.stack().add(Math.sqrt(context.stack().pop()));
        }
        catch (RuntimeException e) {
            throw new ExecutionException("stack error", e);
        }
    }
}
