package org.javaeducation.tasks.calculator;

import com.googlecode.lanterna.screen.Screen;
import org.javaeducation.api.Command;

import java.io.IOException;

public class CalculatorCommand implements Command {
    private static CalculatorCommand instance;

    private CalculatorCommand() {
    }

    public static synchronized CalculatorCommand getInstance() {
        if (instance == null) {
            instance = new CalculatorCommand();
        }
        return instance;
    }

    @Override
    public String getName() {
        return "Start Task";
    }

    @Override
    public String getDescription() {
        return "Cal";
    }

    @Override
    public Command execute(Screen screen) throws IOException {
        return null;
    }
}
