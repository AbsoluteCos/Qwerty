package org.sphinx;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lesson
{
    private final String name;
    private Path location;
    private int height;
    private int index;

    public Lesson(String name) {
        /*for (int i = 0; i < name.length(); i++)
        {
            char c = name.charAt(i);

        }*/

        this.name = name;

        location = Paths.get("c:\\Users\\public\\Lessons");
        if (!folderExists())
        {
            createFolder();
        }
    }

    public int getHeight() {
        return height;
    }

    public int getIndex() {
        return index;
    }

    private boolean folderExists()
    {
        return Files.exists(location);
    }

    private void createFolder()
    {
        File lessonDir = new File(location.toString());             //may not work
        lessonDir.mkdirs();
    }

    public String getName() {
        return name;
    }
}
