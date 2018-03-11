package org.sphinx.course;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        NodeList nameList = root.getElementsByTagName("name");
        String name = ((Element) nameList.item(0)).getAttribute("value");

        NodeList pageList = root.getElementsByTagName("page");
        Element pageElement = (Element) pageList.item(0);
        String textContent = pageElement.getTextContent();
        String[] pathArray = textContent.split("\n");
        for (int i = 0; i < pathArray.length; i++) {
            pathArray[i] = pathArray[i].trim();
        }

        List<Path> paths = new ArrayList<>();
        for (String aPathArray : pathArray) {
            if (!aPathArray.equals("")) {
                paths.add(path.resolve(aPathArray));
            }
        }

        return new Course(name, paths);
    }
}
