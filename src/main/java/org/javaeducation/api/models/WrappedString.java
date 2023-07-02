package org.javaeducation.api.models;

import com.googlecode.lanterna.TerminalPosition;

public record WrappedString (TerminalPosition terminalPosition, String row) {
}
