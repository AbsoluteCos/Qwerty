package org.sphinx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;

public class Lesson
{
    private final String name;
    protected Path location;
    protected ArrayList<Content> contents = new ArrayList<>();
    protected String rawContent;
    protected static int numberLessons;
    private int height;
    private int index;

    public Lesson(String name, Path location) throws IOException
    {
        this.name = name;
        this.location = location;

        //Downloads file off Github
        String link = "https://raw.githubusercontent.com/SphinxCombine/Qwerty/" + name + ".html";
        URL gitlink = new URL(link);
        HttpURLConnection githttp = (HttpURLConnection) gitlink.openConnection();
        InputStream gitStream = githttp.getInputStream();
        rawContent = gitStringFromString(gitStream);
        parseHTML(rawContent);
        numberLessons++;
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

    public static int getNumberLessons()
    {
        return numberLessons;
    }

    public void parseHTML(String rawContent)
    {
        Document document = Jsoup.parse(rawContent);
        //TODO: work on document
    }
}
