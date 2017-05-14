package com.kwindo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by liza on 5/14/2017.
 */
public class Interface extends JFrame {

    private ImageIcon image;
    private JLabel imgLabel;

    Interface() {
        setLayout(new FlowLayout());

        image = new ImageIcon(getClass().getResource("resources/Kwindo_Logo.png"));

        imgLabel = new JLabel(image);
        add(imgLabel);
    }


    public static void main(String[] args){

        Interface gui = new Interface();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1280,720);
        gui.setVisible(true);
        gui.setTitle("Kwindo");
    }

}
