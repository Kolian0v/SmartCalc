package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import viewModel.Controller;

import javax.swing.*;
import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackageClasses = Controller.class)
public class App {
    private static final String PORT = "2121";

    public static void main(String[] args) throws IOException {

        System.setProperty("logging.file.name", LoggerCreator.getLogFile());

        JFrame frame = new JFrame();
        frame.setVisible(true);

        SpringApplication strApp = new SpringApplication(App.class);
        strApp.setDefaultProperties(Collections.singletonMap("server.port", PORT));
        strApp.run();

        Runtime runTime = Runtime.getRuntime();
        String url = "http://localhost:" + PORT;
        String operationSystem = System.getProperty("os.name");
        System.out.println(operationSystem);
        if (operationSystem.contains("Mac")) {
            runTime.exec("open " + url);
        } else if (operationSystem.contains("nix") || operationSystem.contains("nux")) {
            String[] browsers = { "google-chrome", "opera", "links",
                    "lynx", "firefox", "mozilla", "epiphany",
                    "konqueror",  "netscape" };

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < browsers.length; i++)
                if(i == 0)
                    stringBuilder.append(    String.format("%s \"%s\"", browsers[i], url));
                else
                    stringBuilder.append(String.format(" || %s \"%s\"", browsers[i], url));

            runTime.exec(new String[]
                    { "sh", "-c", stringBuilder.toString()}
            );
        } else if (operationSystem.contains("Win")) {
            runTime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        }

        frame.setVisible(false);
        frame.dispose();
    }
}

