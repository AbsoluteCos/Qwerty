package org.sphinx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Lesson
{
    private final String name;
    protected Path location;
    protected ArrayList<Content> contents = new ArrayList<>();
    protected String rawContent;
    protected static int numberLessons;
    private int height;
    private int index;
    private List<String> pages;

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

    public List<String> getPages() {
        return pages;
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
        Elements titles = document.select("h1");
        Elements body = document.select("p");
        Elements code = document.select("code");
        Elements btn = document.select("download[src]");

        for (Element title : titles)
        {
            contents.add(new ContentTitle(title.text()));
        }

        for(Element b : body)
        {
            contents.add(new ContentParagraph(b.text()));
        }

        for(Element c : code)
        {
            contents.add(new ContentCode(c.text()));
        }

        for(Element button : btn)
        {
            contents.add(new ContentButton(button.attr("abs:src")));
        }
    }
}
