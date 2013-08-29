package com.britensw.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {

    public static String readFile(final File file) {
        try {
            final FileInputStream stream = new FileInputStream(file);
            return readFile(stream);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(final InputStream stream) {
        return readFile(stream, true);
    }

    public static String readFile(final InputStream stream, final boolean insertLineBreak) {
        String line;
        final StringBuilder builder = new StringBuilder();
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                if (insertLineBreak) {
                    builder.append('\n');
                }
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        return builder.toString();

    }

    public static byte[] readFileBytes(final InputStream stream) {
        try {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(stream.available());
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toByteArray();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

}
