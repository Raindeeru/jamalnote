package com.guoxquiboloy.le3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecentFileManager {
    private static String recentsPath = "recentFiles.csv";
    
    public static void addRecentFile(File file) throws IOException{
        FileWriter writer = new FileWriter(new File(recentsPath));
        writer.write(file.getAbsolutePath());
        writer.close();
    }
}
