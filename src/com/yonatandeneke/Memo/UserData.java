package com.yonatandeneke.Memo;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class UserData {

    private String folderPath, configPath;
    private Path folder, memos;
    private Properties properties;

    public UserData(){
        folderPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Memo\\";
        configPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Memo\\config.properties";
        folder = Paths.get(folderPath);
        memos = Paths.get(folderPath + "\\memos\\");

        init();

        try {
            FileInputStream fileInputStream = new FileInputStream(configPath);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(){
        if (!Files.exists(folder)) {
            try {
                Files.createDirectory(folder);
                Files.createDirectory(memos);
                Files.write(Paths.get(folder + "\\config.properties"), Arrays.asList("name=You", "theme=light"), StandardCharsets.UTF_8);
                Files.write(Paths.get(memos + "\\Untitled.txt"), Collections.singleton(""), StandardCharsets.UTF_8);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public File[] getMemos(){
        File folder = new File(folderPath + "\\memos\\");
        return folder.listFiles();
    }


    public void updateName(String name){
        properties.setProperty("name", name);
    }

    public void updateTheme(String theme){
        properties.setProperty("theme", theme);
    }

    public String getName(){
        return properties.getProperty("name");
    }

    public String getTheme(){
        return properties.getProperty("theme");
    }

    public String getText(String fileName){
        for (File file : getMemos()){
            if (file.getName().equalsIgnoreCase(fileName)){
                try {
                    return Files.readString(Path.of(file.getPath()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "";
    }

    public void save(String fileName, JTextArea textBox){
        for (File file : getMemos()){
            if (file.getName().equalsIgnoreCase(fileName)){
                try {
                    Files.delete(Path.of(file.getPath()));
                    Files.writeString(Path.of(file.getPath()), textBox.getText(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
