package com.yonatandeneke.Memo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class App {


    private JTextArea noteBox;
    private JPanel panel1;
    private JList<String> list1;
    private JScrollPane scrollPanel1;
    private UserData data;

    public App(){
        data = new UserData();

        DefaultListModel<String> model = new DefaultListModel<>();
        for (File file : data.getMemos()){
            model.addElement(file.getName().substring(0, file.getName().lastIndexOf(".")));
        }
        list1.setModel(model);
        scrollPanel1.setViewportView(list1);
        list1.setLayoutOrientation(JList.VERTICAL);
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    String selection = list1.getSelectedValue();
                    noteBox.setText(data.getText(selection + ".txt"));
                }
            }
        });

        noteBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                data.save(list1.getSelectedValue() + ".txt", noteBox);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                data.save(list1.getSelectedValue() + ".txt", noteBox);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                data.save(list1.getSelectedValue() + ".txt", noteBox);
            }
        });

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
