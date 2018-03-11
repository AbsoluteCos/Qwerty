package org.sphinx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Lesson
{
    private final String name;
    private Path location;
    private ArrayList<ContentButton> contents = new ArrayList<>();
    private String rawContent;
    private static int numberLessons;
    private int height;
    private int index;

    public Lesson(String name, Path location, int height, int index) throws IOException
    {
        this.name = name;
        this.location = location;
        this.height = height;
        this.index = index;

        //Downloads file off Github
        if(true)
        {
            String link = "https://raw.githubusercontent.com/SphinxCombine/Qwerty/" + name + ".html";
            URL gitlink = new URL(link);
            HttpURLConnection githttp = (HttpURLConnection) gitlink.openConnection();
            InputStream gitStream = githttp.getInputStream();
            rawContent = gitStringFromString(gitStream);
        }else{

        }
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

    public String getRawContent() { return rawContent; }

    public URL[] getDownloadLinks() throws MalformedURLException
    {
        URL[] toReturn = new URL[contents.size()];
        for(int i = 0; i < contents.size(); i++)
        {
            toReturn[i] = contents.get(i).get();
        }
        return toReturn;
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

    private void parseHTML(String rawContent)
    {
        Document document = Jsoup.parse(rawContent);
        Elements btn = document.select("download[src]");

        for(Element button : btn)
        {
            contents.add(new ContentButton(button.attr("abs:src")));
        }
    }



}
