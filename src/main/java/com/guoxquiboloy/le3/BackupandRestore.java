package com.guoxquiboloy.le3;
//Library of Functions
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.FileReader;
import java.io.FileWriter;

//Main Program
public class BackupandRestore 
{
    private static final String BACKUP_FILE = "backup.txt";
    //Creates a backup file
    public static void backupUserWork(String text)
    {
        try
        {
            Files.write(Paths.get(BACKUP_FILE), text.getBytes(), StandardOpenOption.CREATE);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //Restores backup
    public static String restoreUserWork()
    {
        try
        {
            return new String(Files.readAllBytes(Paths.get(BACKUP_FILE)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    //Checks for backup
    public static boolean backupChecker () throws IOException
    {
        File file = new File(BACKUP_FILE);
        FileReader reader = new FileReader(file);
        boolean backexists = reader.read() != -1;
        reader.close();
        return backexists;
    }

    public static void clearBackup() throws IOException{
        FileWriter clearer = new FileWriter(BACKUP_FILE, false);
        clearer.write("");
        clearer.close();
    }
}