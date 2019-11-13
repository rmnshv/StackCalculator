package ru.spbstu.icst.calculator;

import ru.spbstu.icst.calculator.exception.CalculatorException;
import ru.spbstu.icst.calculator.exception.SyntaxException;

import java.io.InputStream;
import java.util.Scanner;

public class Calculator {

    private final ExecutionContext context;

    public Calculator(OperationFactory factory) {
        context = new ExecutionContext(factory);
    }

    public void run(InputStream source) throws CalculatorException {
        try (Scanner sc = new Scanner(source)) {
            while (true) {
                // ask user for input
                String token = sc.nextLine().trim();
                if (token.isEmpty()) {
                    continue;
                }

                // graceful shutdown
                if ("exit".equals(token.toLowerCase())) {
                    break;
                }

                try {
                    Operation op = context.factory().create(token);
                    op.execute(context);
                } catch (SyntaxException e) {
                    // print syntax problem and continue
                    System.err.println("Got error: " + e.getMessage());
                }
            }
        }
    }
}
