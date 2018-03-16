package Utils.FileParsers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class INIParser
{
    public Map<String, String> parseINIFile(String fileName)
    {
        Map<String, String> retrievedData = new HashMap<>();
        Properties properties = new Properties();

        try(InputStream inputStream = new FileInputStream(fileName))
        {
            properties.load(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (String key : properties.stringPropertyNames())
        {
            retrievedData.put(key, properties.getProperty(key));
        }
        return retrievedData;
    }
}
