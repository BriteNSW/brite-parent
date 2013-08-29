package com.britensw.util;

import java.util.Collection;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class Unroller {

    public static final String NEW_LINE = "<br/>\n";
    /**
     * Unrolls a stack trace
     *
     * @param builder The result
     * @param throwable The exception to be unrolled
     * @return
     */
    public static StringBuilder unroll(final StringBuilder builder, Throwable throwable, final String newLine) {

        builder.append(newLine)
                .append(newLine);

        do {
            builder.append("Caused by: ")
                    .append(throwable.getMessage())
                    .append(newLine);
            for (final StackTraceElement element : throwable.getStackTrace()) {
                builder.append(element.toString())
                        .append(newLine);
            }
            builder.append(newLine);
        } while ((throwable = throwable.getCause()) != null);

        return builder;
    }

    public static StringBuilder unroll(final StringBuilder builder, final Throwable throwable) {
        return unroll(builder, throwable, NEW_LINE);
    }

    public static StringBuilder unroll(final Throwable throwable) {
        return unroll(new StringBuilder(), throwable, NEW_LINE);
    }


    /**
     * Unrolls a list of strings.
     *
     * @param builder The result
     * @param messages The messages to be unrolled
     * @return
     */
    public static <T extends Collection<String>> StringBuilder unroll(final StringBuilder builder, final T messages, final String newLine) {

        builder.append(newLine)
                .append(newLine);

        for (final String message : messages) {
            builder.append(message).append(newLine);
        }

        return builder;
    }

    public static <T extends Collection<String>> StringBuilder unroll(final StringBuilder builder, final T messages) {
        return unroll(builder, messages, NEW_LINE);
    }

    public static <T extends Collection<String>> StringBuilder unroll(final T messages) {
        return unroll(new StringBuilder(), messages, NEW_LINE);
    }
}
