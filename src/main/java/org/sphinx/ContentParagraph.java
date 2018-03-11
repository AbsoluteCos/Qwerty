package org.sphinx;

public class ContentParagraph extends Content
{
    String value;

    public ContentParagraph(String stuff)
    {
        value = stuff;
    }

    public String get()
    {
        return value;
    }
}
