package ru.spbstu.icst;

import ru.spbstu.icst.calculator.Calculator;
import ru.spbstu.icst.calculator.OperationFactory;
import ru.spbstu.icst.calculator.exception.CalculatorException;
import ru.spbstu.icst.calculator.operation.OperationFactoryImpl;

import java.io.IOException;
import java.util.logging.*;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        System.out.println("Stack calculator. Enter commands one per line, type \"exit\" when finished:");
        OperationFactory factory = new OperationFactoryImpl();
        try {
            FileHandler fh = new FileHandler("FileHandler");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.info("Start logging\n");
            new Calculator(factory).run(System.in);
        } catch (CalculatorException e) {
            logger.warning(e.getLocalizedMessage());
            e.printStackTrace();
        }
        catch(IOException e){
            logger.info(e.getLocalizedMessage());
        }
    }
}
