package com.guoxquiboloy.le3;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class RecentFileManager {
    private static String recentsPath = "recentFiles.csv";
    
    public static void addRecentFile(File file) throws IOException{
        FileWriter writer = new FileWriter(new File(recentsPath), true);
        FileReader reader = new FileReader(file);
        FileReader checker = new FileReader(recentsPath);
        List<String> paths = getPaths(file);
        
        if (checker.read() != -1) {
            writer.write(',');
        }

        writer.write(file.getAbsolutePath());;
        writer.close();
        checker.close();
        reader.close();
    }

    public static List<String> getPaths(File file) throws IOException{
        FileReader reader = new FileReader(file);
        String pathsBig = "";
        int c;
        while ((c = reader.read()) != -1) {
            pathsBig += (char)c;
        }
        List<String> paths = Arrays.asList(pathsBig.split(","));
        reader.close();
        return paths;
    }
}
