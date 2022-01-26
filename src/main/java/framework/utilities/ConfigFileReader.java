package framework.utilities;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import framework.exception.ConfigFileReaderException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    public static Properties properties;

    public Properties configFile() throws ConfigFileReaderException {
        properties = new Properties();

        try {

            BufferedReader br = new BufferedReader(new FileReader(".\\src\\test\\resources\\com\\properties\\config.properties"));
            properties.load(br);

        } catch (FileNotFoundException ex) {
           throw new ConfigFileReaderException(ex.getMessage(),ex);
        } catch (IOException e) {
            throw new ConfigFileReaderException(e.getMessage(),e);
        } catch (Exception exc) {
            throw new ConfigFileReaderException(exc.getMessage(),exc);
        }
        return properties;
    }

    public static void main(String args[]) throws ConfigFileReaderException{
        ConfigFileReader configFileReader = new ConfigFileReader();
        Properties properties = new Properties();
        properties = configFileReader.configFile();
        System.out.println(properties.get("browser"));
    }

}
