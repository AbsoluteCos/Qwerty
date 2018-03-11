package org.sphinx;

public class ContentCode extends Content
{
    String value;

    public ContentCode(String stuff)
    {
        value = stuff;
    }

    public String get()
    {
        return value;
    }
}
