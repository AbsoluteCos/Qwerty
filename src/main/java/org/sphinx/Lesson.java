package org.sphinx;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        String path = location.toString();
        path = path.substring(0, path.length() - 4);

        Path indexFile = Paths.get(path, "index.html");

        StringBuilder data = new StringBuilder();
        BufferedReader reader = Files.newBufferedReader(indexFile);
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
