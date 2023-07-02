package org.javaeducation.api;

import com.googlecode.lanterna.screen.Screen;
import org.javaeducation.api.models.MenuItem;
import org.javaeducation.utils.MenuUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface Command {
    String getName();

    String getDescription();

    Command execute(Screen screen) throws IOException;

    default Command createItems(List<Command> commands, Screen screen) throws IOException {
        List<MenuItem> items = commands
                .stream()
                .map(item -> new MenuItem(item.getName(), item))
                .toList();
        return MenuUtils.select(items, screen, this);
    }

    default String getReadme(String path) {

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("""
                    src/main/java/org/javaeducation/%s/README.md""".formatted(path)));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return Arrays.stream(lines.toArray(new String[0]))
                .reduce("", (result, line) -> result + """
                        | %s <br>""".formatted(line));
    }
}
