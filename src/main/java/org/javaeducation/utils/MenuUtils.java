package org.javaeducation.utils;

import org.javaeducation.api.Command;
import org.javaeducation.api.MenuItem;

import java.util.List;

public class MenuUtils {
    public static Command printCommands(List<MenuItem> menuItems) {
        printItems(menuItems);
        return null;
    }

    private static void printItems(List<MenuItem> items) {
        System.out.flush();
        System.out.println("=======================================================");
        items.forEach(item -> {
            if (item.isActive()) {
                System.out.printf("\u001b[32m %s - %s \u001b[0m%n", item.expectedInput(), item.text());
            } else {
                System.out.printf(" %s - %s %n", item.expectedInput(), item.text());
            }
        });
        System.out.printf("%s%n=======================================================", "");
    }
}