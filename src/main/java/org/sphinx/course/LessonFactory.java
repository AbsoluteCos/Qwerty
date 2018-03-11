package org.sphinx.course;

import org.sphinx.Lesson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.DocFlavor;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

import static org.sphinx.Main.instance;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/11/2018
 */
public class LessonFactory {
    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder builder;

    static {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            instance.getConsole().log(Level.WARNING, e);
        }
    }

    public static Lesson load(Path path) throws IOException, SAXException {
        //TODO: lesson injection

        Document document = builder.parse(Files.newInputStream(path));
        Element root = document.getDocumentElement();

        String name = null;
        NodeList nameList = root.getElementsByTagName("name");
        if (nameList.getLength() > 1) {
            throw new IllegalStateException("Cannot have more than 1 name tag");
        } else if (nameList.getLength() == 1) {
            name = ((Element) nameList.item(0)).getAttribute("value");
        }

        int height = -1;
        int index = -1;
        NodeList posList = root.getElementsByTagName("position");
        if (posList.getLength() > 1) {
            throw new IllegalStateException("Cannot have more than 1 position tag");
        } else if (posList.getLength() == 1) {
            Element element = ((Element) posList.item(0));
            height = Integer.parseInt(element.getAttribute("height"));
            index = Integer.parseInt(element.getAttribute("index"));
        }

        String description = null;
        NodeList descList = root.getElementsByTagName("description");
        if(descList.getLength() > 1){
            throw new IllegalStateException("Cannot have more than 1 description tag");
        } else if(descList.getLength() == 1){
            Element element = ((Element) descList.item(0));
            description = element.getTextContent();
        }

        Lesson lesson = new Lesson(name, path, height, index);
        if(description != null){
            lesson.setDescription(description);
        }
        return lesson;
    }
}
