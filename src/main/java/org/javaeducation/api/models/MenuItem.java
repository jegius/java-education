package org.javaeducation.api.models;

import org.javaeducation.api.Command;

public record MenuItem(String text, Command toExecute) {
}
