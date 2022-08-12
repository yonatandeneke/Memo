package com.yonatandeneke.Memo;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class App {


    private JTextArea noteBox;
    private JPanel panel1;
    private JList list1;

    public App(){
        System.out.println(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Memo");
        frame.setPreferredSize(new Dimension(600, 500));
        frame.pack();
        frame.setVisible(true);

    }
}
