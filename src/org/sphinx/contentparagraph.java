package org.sphinx;

public class contentparagraph extends Content
{
    String value;

    public contentparagraph(String stuff)
    {
        value = stuff;
    }

    public String get()
    {
        return value;
    }
}
