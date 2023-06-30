package org.javaeducation.tasks;

import org.javaeducation.api.Command;
import org.javaeducation.api.MenuItem;
import org.javaeducation.utils.MenuUtils;

import java.util.Arrays;
import java.util.List;

public class SelectTaskCommand implements Command {
    private static SelectTaskCommand instance;

    private SelectTaskCommand() {
    }

    public static synchronized SelectTaskCommand getInstance() {
        if (instance == null) {
            instance = new SelectTaskCommand();
        }
        return instance;
    }
    @Override
    public Command execute() {
        List<MenuItem> items = Arrays.asList(
                new MenuItem("1", "text", SelectTaskCommand.getInstance(), true),
                new MenuItem("2", "text", SelectTaskCommand.getInstance(), false),
                new MenuItem("3", "text", SelectTaskCommand.getInstance(), false)
        );

        return MenuUtils.printCommands(items);
    }
}
