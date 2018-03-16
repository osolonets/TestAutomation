package Utils.FileParsers;

import java.io.*;
import java.util.Scanner;

public class TextFileParser
{

    public String getLastLineOfTheFile(String fileName)
    {
        String line="";
        File file = new File(fileName);
        try(FileInputStream fis=new FileInputStream(file))
        {
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNext())
            {
                line = scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return line;
    }

    public void appendLineToTheFile(String fileName, String line)
    {
        try(FileWriter writer = new FileWriter(fileName, true))
        {
            writer.append(line);
            writer.append("\n");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
