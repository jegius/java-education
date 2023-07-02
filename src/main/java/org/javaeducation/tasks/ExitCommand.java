package org.javaeducation.tasks;

import com.googlecode.lanterna.screen.Screen;
import org.javaeducation.api.Command;

public class ExitCommand implements Command {
    private static ExitCommand instance;

    private ExitCommand() {
    }

    public static synchronized ExitCommand getInstance() {
        if (instance == null) {
            instance = new ExitCommand();
        }
        return instance;
    }

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Command execute(Screen screen) {
        return null;
    }
}
