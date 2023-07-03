package org.javaeducation.tasks;

import com.googlecode.lanterna.screen.Screen;
import org.javaeducation.api.Command;
import org.javaeducation.tasks.calculator.StartTask;

import java.io.IOException;
import java.util.List;

public class SelectTaskCommand implements Command {
    private static SelectTaskCommand instance;
    private static final String PATH = "tasks";

    private SelectTaskCommand() {
    }

    public static synchronized SelectTaskCommand getInstance() {
        if (instance == null) {
            instance = new SelectTaskCommand();
        }
        return instance;
    }

    @Override
    public String getName() {
        return getReadmeHeader(PATH);
    }

    @Override
    public String getDescription() {
        return getReadme(PATH);
    }

    @Override
    public Command execute(Screen screen) throws IOException {
        screen.clear();
        return createItems(List.of(
                StartTask.getInstance(),
                ExitCommand.getInstance()
        ), screen);
    }
}
