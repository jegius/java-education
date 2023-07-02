package org.javaeducation.api;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class TaskRunner {

    private Command command;
    private Screen screen;

    public TaskRunner(Command startCommand) {
        this.command = startCommand;

        try {
            Terminal terminal = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(150, 50))
                    .createTerminal();

            this.screen = new TerminalScreen(terminal);
            screen.startScreen();
        } catch (IOException exception) {
            System.out.printf("Problem with start application: %s", exception);
        }
    }

    public void run() {
        while (command != null) {
            try {
                command = command.execute(screen);
            } catch (IOException exception) {
                System.out.printf("Problem with next command: %s", exception);
                command = null;
            }
        }
        try {
            screen.stopScreen();
        } catch (IOException exception) {
            System.out.printf("Problem with stop application screen: %s", exception);
        }
    }
}
