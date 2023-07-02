package org.javaeducation.api;

import com.googlecode.lanterna.TerminalPosition;
import org.javaeducation.api.models.WrappedString;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TemplateStringWrapper implements Iterable<WrappedString> {
    private final List<String> lines;
    private final int column;
    private final int baseRow;
    private static final String STRING_DIVIDER = "<br>";

    public TemplateStringWrapper(String template, Integer column, Integer baseRow) {
        this.column = column;
        this.baseRow = baseRow;
        this.lines = Arrays
                .stream(template.split(STRING_DIVIDER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<WrappedString> iterator() {
        return new StringIterator();
    }

    public Integer getSize() {
        return lines.size();
    }

    private class StringIterator implements Iterator<WrappedString> {
        private int currentRow = 0;

        public StringIterator() {
        }

        @Override
        public boolean hasNext() {
            return currentRow < lines.size();
        }

        @Override
        public WrappedString next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return new WrappedString(new TerminalPosition(column, baseRow + currentRow), lines.get(currentRow++));
        }
    }
}
