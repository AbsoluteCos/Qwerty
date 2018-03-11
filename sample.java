import java.io.*;

public class CreateHtmlDocument {

    public static void main(String[] args)
    {
        try {
          <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>$title</title>
</head>
<body>$body
</body>
</html>
File htmlTemplateFile = new File("path/template.html");
String htmlString = FileUtils.readFileToString(htmlTemplateFile);
String title = "New Page";
String body = "This is Body";
htmlString = htmlString.replace("$title", title);
htmlString = htmlString.replace("$body", body);
File newHtmlFile = new File("path/new.html");
FileUtils.writeStringToFile(newHtmlFile, htmlString);

            //define a HTML String Builder
            StringBuilder htmlStringBuilder=new StringBuilder();
            //append html header and title
            htmlStringBuilder.append("<html><head><title>Selenium Test </title></head>");
            //append body
            htmlStringBuilder.append("<body>");
            //append table
            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
            //append row
            htmlStringBuilder.append("<tr><td><b>TestId</b></td><td><b>TestName</b></td><td><b>TestResult</b></td></tr>");
            //append row
            htmlStringBuilder.append("<tr><td>001</td><td>Login</td><td>Passed</td></tr>");
            //append row
            htmlStringBuilder.append("<tr><td>002</td><td>Logout</td><td>Passed</td></tr>");
            //close html file
            htmlStringBuilder.append("</table></body></html>");
            //write html string content to a file
            WriteToFile(htmlStringBuilder.toString(),"testfile.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    import htmlflow.HtmlView;

import model.Priority;
import model.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class App {

    private static HtmlView<Task> taskDetailsView(){
        HtmlView<Task> taskView = new HtmlView<>();
        taskView
                .head()
                .title("Task Details")
                .linkCss("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css");
        taskView
                .body().classAttr("container")
                .heading(1, "Task Details")
                .hr()
                .div()
                .text("Title: ").text(Task::getTitle)
                .br()
                .text("Description: ").text(Task::getDescription)
                .br()
                .text("Priority: ").text(Task::getPriority);
        return taskView;
    }

    public static void main(String [] args) throws IOException{
        HtmlView<Task> taskView = taskDetailsView();
        Task task =  new Task("Special dinner", "Have dinner with someone!", Priority.Normal);

        try(PrintStream out = new PrintStream(new FileOutputStream("Task.html"))){
            taskView.setPrintStream(out).write(task);
            Desktop.getDesktop().browse(URI.create("Task.html"));
        }
    }
}
    public static void WriteToFile(String fileContent, String file Name) throws  {
        String project Path = System.getProperty("user.dir");
        String temp File = project Path + File.separator+fileName;
        File file = new File(temp File);
        // if file does exists, then delete and create a new file
        if (file.exists()) {
            try {
                File new File Name = new File(project Path + File.separator+ "backup_"+file Name);
                file.renameTo (new File Name);
                file.create New File();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //write to file with OutputStreamWriter
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        Writer writer=new OutputStreamWriter(outputStream);
        writer.write(fileContent);
        writer.close();
        public static ///}


    }
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class TexttoHTML

{

    public static void main(String[] args) {

     try {

            BufferedReader txtfile = new BufferedReader(new FileReader("c:\\test.txt"));
            OutputStream htmlfile= new FileOutputStream(new File("c:\\test.html"));
            PrintStream printhtml = new PrintStream(htmlfile);

            String[] txtbyLine = new String[500];
            String temp = "";
            String txtfiledata = ""
            String htmlheader="<html><head>";
            htmlheader+="<title>Equivalent HTML</title>";
            htmlheader+="</head><body>";
            String htmlfooter="</body></html>";
            int linenum = 0 ;

            while ((txtfiledata = txtfile.readLine())!= null)
               {
                    txtbyLine[linenum] = txtfiledata;
                    linenum++;
                }
            for(int i=0;i<linenum;i++)
                {
                    if(i == 0)
                    {
                        temp = htmlheader + txtbyLine[0];
                        txtbyLine[0] = temp;
                    }
                    if(linenum == i+1)
                    {
                        temp = txtbyLine[i] + htmlfooter;
                        txtbyLine[i] = temp;
                    }
                    printhtml.println(txtbyLine[i]);
                }
properties++ (import fileContent)
printhtml.close();
        htmlfile.close();
        txtfile.close();

    }

    catch (Exception e) {}
}
}
import junit.framework.TestCase;
public printStackTrace
access all fileContent
counter++
String content = "";
     {
        BufferedReader in = new BufferedReader(new FileReader("mypage.html"));
        String str;
        while ((str = in.readLine()) != null) {
            content +=str;
        }
        in.close();
    } catch (IOException e) {
    }
    StringBuilder contentBuilder = new StringBuilder();
try {
    BufferedReader in = new BufferedReader(new FileReader("mypage.html"));
    String str;
    while ((str = in.readLine()) != null) {
        contentBuilder.append(str);
    }
    in.close();
} catch (IOException e) {
}
String content = contentBuilder.toString();
import java.net.*;
import java.io.*;
import java.util.*;

public class FileServer
{
    public static void main(String[] args)
    {
        // read arguments
        if (args.length!=2) {
            System.out.println("Usage: java FileServer <port> <wwwhome>");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0]);
        String wwwhome = args[1];

        // open server socket
        ServerSocket socket = null;
        Html html = new Html(null) {{
    new Head(this);
    new Body(this,
        new ClassAttribute("body-styles-cls"));
}};

Body body = TagRepository.findOneTagAssignableToTag(Body.class, html);
body.appendChild(new Div(null));

System.out.println(html.toHtmlString());
//directly writes to file
html.toOutputStream(new FileOutputStream("/home/user/filepath/filename.html"), "UTF-8");
prints (in minified format):-
        try {
          Document doc = Jsoup.parse("<html></html>");
doc.body().addClass("body-styles-cls");
doc.body().appendElement("div");
System.out.println(doc.toString());
            socket = new ServerSocket(port);
        } catch (IOException e) {
          socket = new ServerSocket(port);
     } catch (IOException e) {
         System.err.println("Could not start server: " + e);
         System.exit(-1);
     }
     System.out.println("FileServer accepting connections on port " + port);
Begin request-response loop:
// request handler loop
        while (true) {
            Socket connection = null;
            try {
                // wait for request
                connection = socket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                PrintStream pout = new PrintStream(out);
                // read first line of request (ignore the rest)
                String request = in.readLine();
                if (request==null)
                    continue;
                log(connection, request);
                while (true) {
                    String misc = in.readLine();
                    if (misc==null || misc.length()==0)
                        break;
                        // parse the line
                if (!request.startsWith("GET") || request.length()<14 ||
                    !(request.endsWith("HTTP/1.0") || request.endsWith("HTTP/1.1"))) {
                    // bad request
                    errorReport(pout, connection, "400", "Bad Request",
                                "Your browser sent a request that " +
                                "this server could not understand.");
                                <html>
 <head></head>
 <body class=" body-styles-cls">
  <div></div>
 </body>
</html>
                } else {
                    String req = request.substring(4, request.length()-9).trim();
                    if (req.indexOf("..")!=-1 ||
                        req.indexOf("/.ht")!=-1 || req.endsWith("~")) {
                        // evil hacker trying to read non-wwwhome or secret file
                        errorReport(pout, connection, "403", "Forbidden",
                                    "You don't have permission to access the requested URL.");
                    } else {
                        String path = wwwhome + "/" + req;
                        File f = new File(path);
                        if (f.isDirectory() && !path.endsWith("/")) {
                            // redirect browser if referring to directory without final '/'
                            pout.print("HTTP/1.0 301 Moved Permanently\r\n" +
                                       "Location: http://" +
                                       connection.getLocalAddress().getHostAddress() + ":" +
                                       connection.getLocalPort() + "/" + req + "/\r\n\r\n");
                            log(connection, "301 Moved Permanently");
                        } else {
                            if (f.isDirectory()) {
                                // if directory, implicitly add 'index.html'
                                path = path + "index.html";
                                f = new File(path);
                            }
                            try {
                                // send file
                                InputStream file = new FileInputStream(f);
                                pout.print("HTTP/1.0 200 OK\r\n" +
                                           "Content-Type: " + guessContentType(path) + "\r\n" +
                                           "Date: " + new Date() + "\r\n" +
                                           "Server: FileServer 1.0\r\n\r\n");
                                sendFile(file, out); // send raw file
                                log(connection, "200 OK");
                            } catch (FileNotFoundException e) {
                                // file not found
                                errorReport(pout, connection, "404", "Not Found",
                                            "The requested URL was not found on this server.");
                            }
                        }
                    }
                }
                out.flush();
                publicvoid access
                allow accesspoint to network InputStreamReader
              } catch (IOException e) { System.err.println(e); }
         try {
             if (connection != null) connection.close();
         } catch (IOException e) { System.err.println(e); }
     }
 }
 private static String guessContentType(String path)
   {
       if (path.endsWith(".html") || path.endsWith(".htm"))
           return "text/html";
       else if (path.endsWith(".txt") || path.endsWith(".java"))
           return "text/plain";
       else if (path.endsWith(".gif"))
           return "image/gif";
       else if (path.endsWith(".class"))
           return "application/octet-stream";
       else if (path.endsWith(".jpg") || path.endsWith(".jpeg"))
           return "image/jpeg";
       else
           return "text/plain";
   }

   private static void sendFile(InputStream file, OutputStream out)
   {
       try {
           byte[] buffer = new byte[1000];
           while (file.available()>0)
               out.write(buffer, 0, file.read(buffer));
       } catch (IOException e) { System.err.println(e); }
   }
}
