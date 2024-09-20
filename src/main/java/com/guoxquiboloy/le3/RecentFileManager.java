package com.guoxquiboloy.le3;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class RecentFileManager {
    private static String recentsPath = "recentFiles.csv";
    
    public static void addRecentFile(File file) throws IOException{
        FileWriter writer = new FileWriter(new File(recentsPath));
        FileReader reader = new FileReader(file);
        if (reader.read() != -1) {
            writer.write(',');
        }
        writer.write(file.getAbsolutePath());
        writer.close();
        reader.close();
    }
}
