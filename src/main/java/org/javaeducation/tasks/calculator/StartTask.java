package org.javaeducation.tasks.calculator;

import com.googlecode.lanterna.screen.Screen;
import org.javaeducation.api.Command;
import org.javaeducation.tasks.SelectTaskCommand;

import java.io.IOException;
import java.util.List;

public class StartTask implements Command {
    private static StartTask instance;

    private StartTask() {
    }

    public static synchronized StartTask getInstance() {
        if (instance == null) {
            instance = new StartTask();
        }
        return instance;
    }


    @Override
    public String getName() {
        return "Calculator";
    }

    @Override
    public String getDescription() {
        return getReadme("tasks/calculator");
    }

    @Override
    public Command execute(Screen screen) throws IOException {
        screen.clear();
        return createItems(List.of(
                CalculatorCommand.getInstance(),
                SelectTaskCommand.getInstance()
        ), screen);
    }
}
