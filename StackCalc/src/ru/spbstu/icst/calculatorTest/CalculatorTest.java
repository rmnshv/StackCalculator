package ru.spbstu.icst.calculatorTest;

import org.junit.*;
import ru.spbstu.icst.calculator.ExecutionContext;
import ru.spbstu.icst.calculator.Operation;
import ru.spbstu.icst.calculator.exception.CalculatorException;


import static org.junit.Assert.*;

public class CalculatorTest {
    private ExecutionContext context;
    private String next;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After class");
    }

    @Before
    public void initTest() {
        context = new ExecutionContext();
        next = "";
    }

    @After
    public void afterTest() {
        context = null;
        next = "";
    }

    @Test
    public void testPUSH() throws CalculatorException {
        next = "PUSH 20";
        Operation op = context.factory().create(next);
        op.execute(context);
        assertEquals( 20, context.stack().peek(), 1e-4);
    }

    @Test
    public void testDEFINE() throws CalculatorException {
        next = "DEFINE a 4";
        Operation op = context.factory().create(next);
        op.execute(context);
        assertEquals(true, context.table().containsKey("a"));
    }

    @Test
    public void testPLUS() throws CalculatorException {
        next = "EXP 2+4";
        Operation op = context.factory().create(next);
        op.execute(context);
        assertEquals(6, context.stack().peek(), 1e-4);
    }

    @Test
    public void testDIVIDE() throws CalculatorException {
        next = "EXP 9/3";
        Operation op = context.factory().create(next);
        op.execute(context);
        assertEquals(3, context.stack().peek(), 1e-4);
    }

    @Test
    public void testSQRT() throws CalculatorException {
        next = "EXP 9!";
        Operation op = context.factory().create(next);
        op.execute(context);
        assertEquals(3, context.stack().peek(), 1e-4);
    }

    @Test
    public void testEXP() throws CalculatorException {
        next = "EXP 2 + 2 * 2";
        Operation op = context.factory().create(next);
        op.execute(context);
        assertEquals(6, context.stack().peek(), 1e-4);
    }
}