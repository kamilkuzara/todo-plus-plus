package utils;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.lang.RuntimeException;

public class SessionFileIO{
    private static SessionFileIO instance;

    private File sessionFile;

    public static SessionFileIO instance()
    {
        if(instance == null)
          instance = new SessionFileIO();

        return instance;
    }

    private SessionFileIO()
    {
        sessionFile = new File("program_files/session.json");
        //sessionFile = new File("/home/msi/Documents/session.json");
          System.out.println("Session file created in: " + sessionFile.getAbsolutePath());
        try
        {
            sessionFile.createNewFile();  // will create a new file if one doesn't already exist
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public String readSessionFile()
    {
        try(Scanner scanner = new Scanner(sessionFile))
        {
            scanner.useDelimiter("\n");

            String fileContent = "";
            while(scanner.hasNext())
                fileContent += scanner.next();

            // scanner.close();   <- not needed, because try-with-resource used

            return fileContent;
        }
        catch(FileNotFoundException exception)  // could be handled by catching IOException
        {
            System.out.println("The specified file was not found");
            exception.printStackTrace();
            return null;
        }
        catch(IllegalStateException exception)  // could be handled by catching RuntimeException
        {
            System.out.println("The scanner is closed");
            exception.printStackTrace();
            return null;
        }
        catch(NoSuchElementException exception)  // could be handled by catching RuntimeException
        {
            System.out.println("No more tokens are available");
            exception.printStackTrace();
            return null;
        }
        /*    // could be used as an alternative to the above
        catch(IOException exception)
        {
            exception.printStackTrace();
            return null;
        }
        /*catch(RuntimeException exception)
        {
            exception.printStackTrace();
            return null;
        }
        */
    }

    public void writeSessionFile(String fileContent)
    {
        try(BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter(
                                    new FileOutputStream(sessionFile), "utf-8")))
        {
            writer.write(fileContent);

            // writer.close();   <- not needed, because try-with-resource used
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
