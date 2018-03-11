package org.sphinx;

import java.net.MalformedURLException;
import java.net.URL;

public class contentbtn extends Content
{

    String value;

    public contentbtn(String input)
    {
        input = value;
    }

    public URL get() throws MalformedURLException
    {
        return new URL(value);
    }
}
