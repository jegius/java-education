package org.javaeducation.utils;

import org.javaeducation.api.Command;
import org.javaeducation.api.MenuItem;

import java.io.IOException;
import java.util.List;


public class MenuUtils {
    public static final String CONSOLE_INSTRUCTION = "Please select number of task, or Q for exit";
    public static final String CONSOLE_TEMPLATE = """
            =======================================================
            %s
            =======================================================""";

    public static Command printCommands(List<MenuItem> menuItems) throws IOException {
        return select(menuItems);
    }

    private static Command select(List<MenuItem> menuItems) throws IOException {

        return menuItems.get(0).toExecute();
    }
    private static String printItems(List<MenuItem> menuItems) {

        String pattern = menuItems
                .stream()
                .map(MenuUtils::convertItemsToString)
                .reduce("", MenuUtils::reduceString);
        return CONSOLE_TEMPLATE.formatted(pattern);
    }

    private static String reduceString(String previous, String next) {
        return """ 
                %s%s
                """.formatted(previous, next);
    }

    private static String convertItemsToString(MenuItem item) {
        String itemPattern = item.isActive()
                ? """
                \u001b[32m- %s\u001b[0m"""
                : """ 
                - %s""";
        return itemPattern.formatted(item.text());
    }
}