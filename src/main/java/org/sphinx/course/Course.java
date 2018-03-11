package org.sphinx.course;

import org.sphinx.Lesson;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class Course {
    private final Path location;
    private final String name;
    private final List<Lesson> lessons;

    public Course(String name, List<Path> pagesPath, Path location) throws IOException, SAXException {
        this.name = name;
        this.location = location;

        this.lessons = new ArrayList<>();
        for (int i = 0; i < pagesPath.size(); i++) {
            Path path = pagesPath.get(i);

            //TODO: impl
            lessons.add(LessonFactory.load(path));
        }
    }

    public String getName() {
        return name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Path getLocation() {
        return location;
    }
}
