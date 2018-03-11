package org.sphinx;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lesson
{
    private final String name;
    private Path location;
    private String rawContent;
    private int height;
    private int index;

    public Lesson(String name, Path location, int height, int index) throws IOException
    {
        this.name = name;
        this.location = location;
        this.height = height;
        this.index = index;

        StringBuilder data = new StringBuilder();
        BufferedReader reader = Files.newBufferedReader(location);
        String line;
        while ((line = reader.readLine()) != null) {
            data.append(line);
        }
        reader.close();

        rawContent = data.toString();
    }

    public Path getLocation() {
        return location;
    }

    public String getRawContent() {
        return rawContent;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getIndex() {
        return index;
    }
}
