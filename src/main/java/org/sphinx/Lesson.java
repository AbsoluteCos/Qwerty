package org.sphinx;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class Lesson
{
    protected Path path;
    protected String title;
    protected ArrayList<Content> contents = new ArrayList<Content>();
    protected static int numberLessons;
    protected Lesson(String lessontitle)
    {
        lessontitle.toUpperCase();

        path = Paths.get("c:\\Users\\public\\Lessons");

        if (!folderExists())
        {
            createFolder();
        }

        /*String link = "https://raw.githubusercontent.com/SphinxCombine/Qwerty/" + lessontitle + ".html";
        URL gitlink = new URL(link);
        HttpURLConnection githttp = (HttpURLConnection) gitlink.openConnection();
        Map<String, List<String>>  This will download the html file off github*/

        numberLessons++;

        path = Paths.get("c:\\Users\\public\\Lessons\\" + lessontitle + ".html");

    }

    private boolean folderExists()
    {
        if (Files.exists(path))
            return true;
        return false;
    }

    private void createFolder()
    {
        File lessonDir = new File(path.toString());             //may not work
        lessonDir.mkdirs();
    }

    public static int getNumberLessons()
    {
        return numberLessons;
    }
}
