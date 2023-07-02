package org.javaeducation.utils;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.javaeducation.api.Command;
import org.javaeducation.api.TemplateStringWrapper;
import org.javaeducation.api.models.MenuItem;
import org.javaeducation.api.models.WrappedString;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MenuUtils {
    private static final String CONSOLE_INSTRUCTION = """
            <b> .____                                                 </b><br>
            <b> |    |       ____   _____    _______    ____          </b><br>
            <b> |    |     _/ __ \\  \\__  \\   \\_  __ \\  /    \\   </b><br>
            <b> |    |___  \\  ___/   / __ \\_  |  | \\/ |   |  \\    </b><br>
            <b> |_______ \\  \\___  > (____  /  |__|    |___|  /      </b><br>
            <b>        \\/      \\/       \\/                \\/      </b><br>
            <b>      ____.    _____    ____   ____    _____           </b><br>
            <b>     |    |   /  _  \\   \\   \\ /   /   /  _  \\      </b><br>
            <b>     |    |  /  /_\\  \\   \\   V   /   /  /_\\  \\    </b><br>
            <b> /\\__|    | /    |    \\   \\     /   /    |    \\    </b><br>
            <b> \\________| \\____|__  /    \\___/    \\____|__  /    </b><br>
            <b>                    \\/                      \\/       </b><br>
            <b>-------------------------------------------------------</b><br>
            Please select task with using <b>W/S</b> or Arrow <b>UP/DOWN</b> keys<br>
            and press on <b>Enter</b> key for task section, or <b>Q</b> for exit<br>
            -------------------------------------------------------<br>
            <b>%s</b><br>
            -------------------------------------------------------<br>""";

    private static final String BOLD_PATTERN = "<b>(.*?)</b>";

    public static Command select(List<MenuItem> menuItems, Screen screen, Command command) throws IOException {
        int FIRST = 0;
        int selectedCommand = chooseItem(screen, menuItems, FIRST, command);
        return selectedCommand == -1 ? null : menuItems.get(selectedCommand).toExecute();
    }

    public static TemplateStringWrapper printTemplateOnScreen(String template, Screen screen, Integer xPosition, Integer yPosition) {
        TemplateStringWrapper stringWrapper = new TemplateStringWrapper(template, xPosition, yPosition);
        TextGraphics textGraphics = screen.newTextGraphics();

        for (WrappedString wrappedString : stringWrapper) {
            String row = wrappedString.row();
            TerminalPosition position = wrappedString.terminalPosition();
            printByPattern(row, BOLD_PATTERN, position, textGraphics);
        }
        return stringWrapper;
    }

    public static void printByPattern(String row, String pattern, TerminalPosition position, TextGraphics textGraphics) {
        String[] partsOfRow = row.split(pattern);
        List<String> matches = findMatches(pattern, row);
        BiFunction<Integer, String, Integer> wrapper = boldWrapperCarried.apply(textGraphics, position.getRow());

        int xPos = position.getColumn();
        if (partsOfRow.length == 0) {
            printMatches(xPos, matches, wrapper);
        } else {
            for (String partOfTemplate : partsOfRow) {
                textGraphics.putString(xPos, position.getRow(), partOfTemplate);
                xPos += partOfTemplate.length();

                if (!matches.isEmpty()) {
                    String boldPart = matches.remove(0);
                    xPos = wrapper.apply(xPos, boldPart);
                }
            }
        }
    }

    private static final BiFunction<TextGraphics, Integer, BiFunction<Integer, String, Integer>> boldWrapperCarried = (graphics, yPosition) ->
            (xPosition, wrappedPart) -> addBoldAndUpdatePosition(graphics, xPosition, yPosition, wrappedPart);

    private static void printMatches(Integer basePosition, List<String> matches, BiFunction<Integer, String, Integer> renderer) {
        for (String partOfTemplate : matches) {
             renderer.apply(basePosition, partOfTemplate);
        }
    }

    public static boolean isExit(KeyStroke keyStroke) {
        Character input = keyStroke.getCharacter();

        if (input == null) return false;
        return input.equals('Q') || input.equals('q');
    }

    public static boolean isUp(KeyStroke keyStroke) {
        Character input = keyStroke.getCharacter();
        KeyType type = keyStroke.getKeyType();

        if (input != null) {
            return input.equals('w') || input.equals('W') || input.equals('Ц') || input.equals('ц');
        }
        return type == KeyType.ArrowUp;
    }

    public static boolean isDown(KeyStroke keyStroke) {
        Character input = keyStroke.getCharacter();
        KeyType type = keyStroke.getKeyType();

        if (input != null) {
            return input.equals('s') || input.equals('S') || input.equals('ы') || input.equals('Ы');
        }
        return type == KeyType.ArrowDown;
    }

    public static boolean isEnter(KeyStroke keyStroke) {
        return keyStroke.getKeyType() == KeyType.Enter;
    }

    private static int addBoldAndUpdatePosition(TextGraphics textGraphics, int baseXPosition, int yPosition, String part) {
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(baseXPosition, yPosition, part);
        textGraphics.disableModifiers(SGR.BOLD);
        return baseXPosition + part.length();
    }

    private static List<String> findMatches(String pattern, String row) {
        Matcher matcher = Pattern.compile(pattern).matcher(row);
        List<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group(1));
        }
        return matches;
    }

    private static int chooseItem(Screen screen, List<MenuItem> menuItems, int currentSelection, Command command) throws IOException {
        screen.clear();

        Integer PADDING = 2;
        int BASE_X = 4;
        Integer BASE_Y = 1;

        TemplateStringWrapper stringWrapper = printTemplateOnScreen(CONSOLE_INSTRUCTION.formatted(command.getName()), screen, BASE_X, BASE_Y);

        if (command.getDescription() != null) {
            OptionalInt maxLength = menuItems
                    .stream()
                    .map(MenuItem::text)
                    .mapToInt(String::length)
                    .max();
            int xPosition = maxLength.isPresent() ? maxLength.getAsInt() + BASE_X * 2 : BASE_X;

            printTemplateOnScreen(command.getDescription(), screen, xPosition, BASE_Y + stringWrapper.getSize());
        }

        printItems(menuItems, screen, currentSelection, stringWrapper.getSize() + PADDING);
        screen.refresh();

        KeyStroke keyStroke = screen.readInput();

        if (isUp(keyStroke)) {
            currentSelection = (currentSelection - 1 + menuItems.size()) % menuItems.size();
        } else if (isDown(keyStroke)) {
            currentSelection = (currentSelection + 1) % menuItems.size();
        } else if (isExit(keyStroke)) {
            screen.stopScreen();
            return -1;
        } else if (isEnter(keyStroke)) {
            return currentSelection;
        }
        return chooseItem(screen, menuItems, currentSelection, command);
    }

    private static void printItems(List<MenuItem> menuItems, Screen screen, int currentSelection, int startPosition) {
        for (int index = 0; index < menuItems.size(); index++) {
            TerminalPosition position = new TerminalPosition(4, startPosition + index);
            TextGraphics textGraphics = screen.newTextGraphics().enableModifiers(SGR.BOLD);

            if (index == currentSelection) {
                textGraphics
                        .setForegroundColor(TextColor.ANSI.GREEN)
                        .putString(position, """
                                > %s""".formatted(menuItems.get(index).text()));
            } else {
                textGraphics
                        .putString(position, """
                                \s\s%s""".formatted(menuItems.get(index).text()));
            }
        }
    }
}