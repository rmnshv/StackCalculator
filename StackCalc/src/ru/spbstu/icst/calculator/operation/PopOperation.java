package ru.spbstu.icst.calculator.operation;

import ru.spbstu.icst.calculator.ExecutionContext;
import ru.spbstu.icst.calculator.Operation;
import ru.spbstu.icst.calculator.exception.ExecutionException;

public class PopOperation implements Operation {
    @Override
    public void execute(ExecutionContext context) throws ExecutionException{
        context.stack().pop();
    }
}
