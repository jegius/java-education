package org.javaeducation.api;

public record MenuItem(String text, Command toExecute, Boolean isActive) {
}
