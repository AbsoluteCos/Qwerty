package org.sphinx.course;

import java.nio.file.Path;
import java.util.List;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class Course {
    private final String name;
    private final List<Path> pagesPath;

    public Course(String name, List<Path> pagesPath) {
        this.name = name;
        this.pagesPath = pagesPath;
    }

    public String getName() {
        return name;
    }

    public List<Path> getPagesPath() {
        return pagesPath;
    }
}
