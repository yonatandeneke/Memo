package com.yonatandeneke.Memo;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserData {

    private Path folder;

    public UserData(){
        folder = Paths.get(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Memo\\");
        init();
    }

    public void init(){
        if (!Files.exists(folder)) {
            try {
                Files.createDirectory(folder);
                Files.write(Paths.get(folder + "\\options.txt"), Arrays.asList("name: You", "theme: light"), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateName(String name){

    }

    public void updateTheme(String theme){

    }

}
