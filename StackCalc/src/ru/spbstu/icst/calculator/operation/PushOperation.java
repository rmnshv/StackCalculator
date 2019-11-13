package ru.spbstu.icst.calculator.operation;

import ru.spbstu.icst.calculator.ExecutionContext;
import ru.spbstu.icst.calculator.Operation;
import ru.spbstu.icst.calculator.exception.ExecutionException;
import ru.spbstu.icst.calculator.exception.SyntaxException;

public class PushOperation implements Operation {

    private String pushArg; // to push (name of constant or pure value)

    public PushOperation(String... args) throws SyntaxException {
        // validate preconditions
        if (args.length < 1) {
            throw new SyntaxException("Not enough arguments for \"push\" operation");
        }

        pushArg = args[0];

        if (!DefineOperation.NAME_PATTERN.matcher(pushArg).matches()
                && !DefineOperation.DOUBLE_PATTERN.matcher(pushArg).matches()) {
            throw new SyntaxException(String.format("Invalid argument '%s' for \"push\" operation", pushArg));
        }
    }

    @Override
    public void execute(ExecutionContext context) throws ExecutionException {
        if (DefineOperation.NAME_PATTERN.matcher(pushArg).matches()) {
            if (context.table().containsKey(pushArg)) {
                context.stack().add(context.table().get(pushArg));
            } else {
                throw new ExecutionException(String.format("Undefined constant '%s'", pushArg));
            }
        } else {
            context.stack().add(Double.valueOf(pushArg));
        }
    }
}
