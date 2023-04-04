package viewModel;

import models.CalcProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {
    private static final String USER_HOME_DIR = System.getProperty("user.home");
    private static final String PROPERTIES_PATH = "/SmartCalcFiles/config.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesHandler.class);
    public CalcProperties getProperties() {
        CalcProperties calcProperties = new CalcProperties();

        try (FileInputStream input = new FileInputStream(USER_HOME_DIR + PROPERTIES_PATH)) {

            Properties prop = new Properties();
            prop.load(input);
            calcProperties.setBgColor1(prop.getProperty("background.color1"));
            calcProperties.setBgColor2(prop.getProperty("background.color2"));
            calcProperties.setFontSize(prop.getProperty("font.size"));
            calcProperties.setFontColor(prop.getProperty("font.color"));
            calcProperties.setStatus("OK");
            LOGGER.info("[getProperties] response " + calcProperties);

        } catch (IOException ex) {

            calcProperties.setStatus("Error");
            calcProperties.setMessage(ex.getMessage());
            LOGGER.error("[getProperties] just an error: " + ex.getMessage());

        }

        return calcProperties;
    }
}
