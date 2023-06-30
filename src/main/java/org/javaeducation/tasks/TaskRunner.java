package org.javaeducation.tasks;

import org.javaeducation.api.Command;

public class TaskRunner {

    private Command command;

    public TaskRunner(Command startCommand) {
        this.command = startCommand;
    }

    public void run() {
        while (command != null) {
            command = command.execute();
        }
    }
}
