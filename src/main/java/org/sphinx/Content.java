package org.sphinx;

import java.net.MalformedURLException;

public abstract class Content
{
    public Content()
    {
    }

    public abstract Object get() throws MalformedURLException;
}
