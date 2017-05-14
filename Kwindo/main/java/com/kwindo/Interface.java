package com.kwindo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by liza on 5/14/2017.
 */
public class Interface extends JFrame {

    private ImageIcon image;
    private JLabel imgLogo;

    private ImageIcon image2;
    private JLabel img2;

    private JLabel totalProfLabel;
    private JLabel maxProfit;
    private JLabel minProfit;
    private JLabel dailyMin;
    private JLabel dailyMax;
    private JLabel timeTaken;

    private JButton restart;


    Interface() {
        setLayout(null);

        image = new ImageIcon(getClass().getResource("resources/Kwindo_Logo.png"));
        image2 = new ImageIcon(getClass().getResource("resources/textFrame.png"));

        imgLogo = new JLabel(image);
        imgLogo.setBounds(0, 0, 500, 185);
        add(imgLogo);

        totalProfLabel = new JLabel("Total profit:      ...Loading");
        totalProfLabel.setForeground(Color.WHITE);
        totalProfLabel.setBounds(150, 240, 300, 50);
        add(totalProfLabel);

        maxProfit = new JLabel("Max profit:       ...Loading");
        maxProfit.setForeground(Color.WHITE);
        maxProfit.setBounds(150, 260, 300, 50);
        add(maxProfit);

        minProfit = new JLabel("Min profit:        ...Loading");
        minProfit.setForeground(Color.WHITE);
        minProfit.setBounds(150, 280, 300, 50);
        add(minProfit);

        dailyMax = new JLabel("Daily Max Profit:    ...Loading");
        dailyMax.setForeground(Color.WHITE);
        dailyMax.setBounds(150, 340, 300, 50);
        add(dailyMax);

        dailyMin = new JLabel("Daily Min Profit:     ...Loading");
        dailyMin.setForeground(Color.WHITE);
        dailyMin.setBounds(150, 360, 300, 50);
        add(dailyMin);

        timeTaken = new JLabel("Trading took ... seconds.");
        timeTaken.setForeground(Color.WHITE);
        timeTaken.setBounds(150, 400, 300, 50);
        add(timeTaken);

        restart = new JButton("Restart");
        restart.setBounds(150, 460, 80, 20);
        restart.addActionListener(new Click());
        add(restart);

        img2 = new JLabel(image2);
        img2.setBounds(100, 185, 350, 350);
        add(img2);

        Container c = this.getContentPane();
        c.setBackground(Color.darkGray);
    }

    public void updateProfit(float totalProf, float minProf, float maxProf, float dailyMa, float dailyMi, long deltaTime) {
         
        totalProfLabel.setText("Total profit:      " + totalProf);
        maxProfit.setText("Max profit:       " + maxProf);
        minProfit.setText("Min profit:        " + minProf);
        dailyMax.setText("Daily Max Profit:    " + dailyMa);
        dailyMin.setText("Daily Min Profit:     " + dailyMi);
        timeTaken.setText("Trading took " + deltaTime + " seconds");
    }

    public void resetGUI() {
        totalProfLabel.setText("Total profit:      ...Loading");
        totalProfLabel.paintImmediately(totalProfLabel.getVisibleRect());

        maxProfit.setText("Max profit:       ...Loading");
        maxProfit.paintImmediately(maxProfit.getVisibleRect());

        minProfit.setText("Min profit:        ...Loading");
        minProfit.paintImmediately(minProfit.getVisibleRect());

        dailyMax.setText("Daily Max Profit:    ...Loading");
        dailyMax.paintImmediately(dailyMax.getVisibleRect());

        dailyMin.setText("Daily Min Profit:     ...Loading");
        dailyMin.paintImmediately(dailyMin.getVisibleRect());

        timeTaken = new JLabel("Trading took ... seconds.");
        timeTaken.paintImmediately(timeTaken.getVisibleRect());
    }

    class Click implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            resetGUI();
            String[] args = {"C:\\Users\\liza__000\\Desktop\\Hackathons\\HackDelft\\dataset"};
            KwindoAlgorithmRunner.run(new File(args[0]));
        }
    }
}

