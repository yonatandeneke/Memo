package com.yonatandeneke.Memo;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class UserData {

    private String folder;

    public UserData(){
        folder = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    }

    public void init(){

    }
}
