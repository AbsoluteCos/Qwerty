package org.sphinx;

import java.net.MalformedURLException;
import java.net.URL;

public class ContentButton
{

    String value;

    public ContentButton(String input)
    {
        input = value;
    }

    public URL get() throws MalformedURLException
    {
        return new URL(value);
    }
}
