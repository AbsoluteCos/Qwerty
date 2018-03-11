package org.sphinx;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class Lesson
{
    protected Path path;
    protected String title;
    protected ArrayList<Content> contents = new ArrayList<Content>();
    protected String rawContent;
    protected static int numberLessons;

    protected Lesson(String lessontitle) throws IOException
    {
        lessontitle = lessontitle.toUpperCase();

        path = Paths.get("c:\\Users\\public\\Lessons");

        if (!folderExists())
        {
            createFolder();
        }

        //Downloads file off Github
        String link = "https://raw.githubusercontent.com/SphinxCombine/Qwerty/" + lessontitle + ".html";
        URL gitlink = new URL(link);
        HttpURLConnection githttp = (HttpURLConnection) gitlink.openConnection();
        InputStream gitStream = githttp.getInputStream();
        rawContent = gitStringFromString(gitStream);
        parseHTML(rawContent);
        numberLessons++;
    }

    private String gitStringFromString(InputStream stream) throws IOException
    {
        if(stream != null)
        {
            Writer writer = new StringWriter();
            char[] writerBuffer = new char[2048];
            try{
                Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                int counter;
                while ((counter = reader.read(writerBuffer)) != -1)
                {
                    writer.write(writerBuffer, 0, counter);
                }
            } finally
            {
                stream.close();
            }

            return writer.toString();
        } else {
            return "No Contents";
        }
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

    public void parseHTML(String rawContent)
    {

    }
}
