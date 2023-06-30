package org.javaeducation.api;

public record MenuItem(String expectedInput, String text, Command toExecute, Boolean isActive) {
}
