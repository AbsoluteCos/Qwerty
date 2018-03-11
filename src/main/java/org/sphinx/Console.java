package org.sphinx;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/10/2018
 */
public class Console {
    private final Logger logger = Logger.getLogger("Console");

    public String log(Level level, String message, Exception e){
        StringBuilder builder = new StringBuilder();
        if(message != null){
            builder.append(message);
        }

        if(message != null && e != null){
            builder.append("\n");
        }

        if(e != null){
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            builder.append(writer.toString());
        }

        logger.log(level, builder.toString());
        return builder.toString();
    }

    public String log(Level level, String message){
        return log(level, message, null);
    }

    public String log(Level level, Exception e){
        return log(level, null, e);
    }
}
