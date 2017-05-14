package com.kwindo;

import javax.swing.JFrame;

/**
 * Created by liza on 5/14/2017.
 */
public class Interface extends JFrame {

    public static void main(String[] args){

        Interface gui = new Interface();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1280,720);
        gui.setVisible(true);
        gui.setTitle("Kwindo");
    }

}
