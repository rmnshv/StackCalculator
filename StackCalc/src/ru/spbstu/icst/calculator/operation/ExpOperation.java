package ru.spbstu.icst.calculator.operation;

import ru.spbstu.icst.calculator.ExecutionContext;
import ru.spbstu.icst.calculator.Operation;
import ru.spbstu.icst.calculator.exception.CalculatorException;
import ru.spbstu.icst.calculator.exception.ExecutionException;
import ru.spbstu.icst.calculator.exception.SyntaxException;

import java.util.Map;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class ExpOperation implements Operation {

    private final List<String> lexems = new ArrayList<>();
    private final String expression;

    public ExpOperation(String... args) throws SyntaxException {
        this.expression = String.join("", args);
    }
    private boolean isOperand (char c){
        return Character.isDigit(c) || DefineOperation.NAME_PATTERN.matcher(String.valueOf(c)).matches();
    }
    private void operationCalc(ExecutionContext context) throws CalculatorException {
        String allowedSymbols = "+!-/*";
        Stack<Character> st = new Stack<Character>();
        int i = 0;
        while (i < expression.length()){
            char c = expression.charAt(i);
            if (isOperand(c)){
                String operand = "";
                while (i < expression.length() && isOperand(c)){
                    operand += c;
                    i++;
                    c = expression.charAt(i);
                }
                lexems.add(operand);
                i--;
            }
            else if (c == '('){
                st.push(c);
            }
            else if (c == ')'){
                while(!st.empty() && st.peek() != '('){
                    lexems.add(String.valueOf(st.pop()));
                }
                if (!st.empty()){
                    st.pop();
                }
                else throw new SyntaxException("Boundaries error!");
            }
            else if (allowedSymbols.contains(String.valueOf(c))){
                while(!st.empty() && allowedSymbols.contains(String.valueOf(c)) &&
                        prior(c) <= prior(st.peek())){
                    lexems.add(String.valueOf(st.pop()));
                }
                st.push(c);
            }
            else{
                throw new SyntaxException ("Non-allowed symbols!");
            }
            i++;
        }
        while (!st.empty()){
            lexems.add(String.valueOf(st.pop()));
        }
    }
    private static int prior(char c){
        int ans = 0;
        if (c == '+' || c == '-'){
            ans = 1;
        }
        if (c == '*' || c == '/'){
            ans = 2;
        }
        if (c == '!'){
            ans = 3;
        }
        return ans;
    }
    @Override
    public void execute(ExecutionContext context) throws CalculatorException {
        operationCalc(context);
        for (String token : lexems) {
            Operation op;
            try {
                if (DefineOperation.NAME_PATTERN.matcher(token).matches()
                        || DefineOperation.DOUBLE_PATTERN.matcher(token).matches()) {
                    op = context.factory().create("push " + token);
                } else {
                    op = context.factory().create(token);
                }
            } catch (SyntaxException e) {
                throw new ExecutionException(String.format("failed to process '%s'", token));
            }
            op.execute(context);
        }
    }
}
