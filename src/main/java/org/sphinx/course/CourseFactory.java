package org.sphinx.course;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.sphinx.Main.instance;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class CourseFactory {
    private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder builder;

    static {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            instance.getConsole().log(Level.SEVERE, e);
        }
    }

    private CourseFactory() {
    }

    public static Course load(Path path) throws IOException, SAXException {
        Path indexXML = path.resolve("index.xml");
        Document document = builder.parse(Files.newInputStream(indexXML));
        NodeList nameList = document.getElementsByTagName("name");

        String name = ((Element) nameList.item(0)).getAttribute("value");

        NodeList pagesList = document.getElementsByTagName("page");

        List<Path> pagesPath = new ArrayList<>();
        for (int i = 0; i < pagesList.getLength(); i++) {
            Element element = (Element) pagesList.item(i);

            String value = element.getTextContent();
            pagesPath.add(Paths.get(".\\" + value));
        }

        return new Course(name, pagesPath);
    }
}
