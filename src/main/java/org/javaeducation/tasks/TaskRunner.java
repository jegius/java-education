package org.javaeducation.tasks;

import org.javaeducation.api.Command;

import java.io.IOException;

public class TaskRunner {

    private Command command;

    public TaskRunner(Command startCommand) {
        this.command = startCommand;
    }

    public void run() {
        while (command != null) {
            try {
                command = command.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
