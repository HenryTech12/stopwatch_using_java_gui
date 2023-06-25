/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stopwatch;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author leonovo
 */
public class StopwatchInterface implements ActionListener {
    JFrame frame;
    
    JPanel panel;
    
    JLabel label;
    
    JButton startButton;
    
    JButton pauseButton;
    
    JButton resetButton;
    
    private static int milliseconds;
    private static int seconds;
    private static int mins;
    
    private static boolean isStarted = false;
    private static boolean isPaused = false;
    
    private static Timer timer;
    
    JLabel lastRecord;
    
    public StopwatchInterface() {
      Border border = BorderFactory.createLineBorder(Color.gray, 5);
      
      //start Button
      startButton = new JButton();
      startButton.setBounds(10, 125, 100, 40);
      startButton.setFocusable(false);
      startButton.setText("START");
      startButton.addActionListener(this);
      
      //pause Button
      pauseButton = new JButton();
      pauseButton.setBounds(140, 125, 100, 40);
      pauseButton.setFocusable(false);
      pauseButton.setText("PAUSE");
      pauseButton.addActionListener(this);
      
      //reset Button
      resetButton = new JButton();
      resetButton.setBounds(270, 125, 100, 40);
      resetButton.setFocusable(false);
      resetButton.setText("RESET");
      resetButton.addActionListener(this);
      
      //function label
      label = new JLabel();
      label.setBounds(100, 50, 150, 55);
      label.setOpaque(true);
      label.setVerticalAlignment(SwingConstants.CENTER);
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setBackground(Color.GREEN);
      label.setFont(new Font(Font.SANS_SERIF,5,15));
      label.setBorder(border);
      
      //label used to keep record of last time
      lastRecord = new JLabel();
      lastRecord.setBounds(0, 200, 250, 55);
      lastRecord.setOpaque(true);
      lastRecord.setVerticalAlignment(SwingConstants.CENTER);
      lastRecord.setHorizontalAlignment(SwingConstants.CENTER);
      lastRecord.setText(label.getText());
      lastRecord.setFont(new Font(Font.SANS_SERIF,5,15));
      lastRecord.setBackground(Color.DARK_GRAY);
      lastRecord.setForeground(Color.WHITE);
      
      
      
      //defining and instantiating JPanel
      panel  = new JPanel();
      panel.setSize(400, 400);
      panel.setLayout(null);
      panel.add(label);
      panel.add(startButton);
      panel.add(pauseButton);
      panel.add(resetButton);
      panel.add(lastRecord);
      panel.setBackground(Color.DARK_GRAY);
     
      
      timer = new Timer(10,this);
      timer.start();
      
      //defining and instatiating JFrame
      frame = new JFrame();
      frame.setSize(400, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      frame.setLayout(null);
      frame.add(panel);
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //calling required methods
       start(e);
       pause(e);
       reset(e);
        
    }
 public void start(ActionEvent e) {
     //chceking if start button is clicked
     if(e.getSource() == startButton) {
       isStarted = true;   
      }
     //incrementing millisecoonds per timer if startButton is clicked
      if(isStarted) {
            milliseconds++;
            
      //incrementing seconds if milliseconds reach some certain value
        if(milliseconds == 100) {
            seconds++;
           milliseconds = 0;
        }
        
        //incrementing seconds if mins reach some ceartain value also
      if(seconds == 60) {
      mins++;
      seconds = 0;
      milliseconds = 0;
      }
      
     //re- processing  stopwatch if minutes reaches max value
    if(mins == 60) {
     mins =0;
     seconds =0;
     milliseconds =0;
    }
    
    // function which starts back the stopwatch after been paused
     if(isPaused) {
       timer.restart();
       pauseButton.setEnabled(true);
     }
     //setting text to label
     label.setText("0"+mins+" : 0"+seconds+" : 0"+milliseconds);
      }
 }
public void pause(ActionEvent e) {
    if(e.getSource() == pauseButton) {
      label.setText("0"+mins+" : 0"+seconds+" : 0"+milliseconds);
      timer.stop();
      isPaused= true;
      pauseButton.setEnabled(false);
      }
}
public void reset(ActionEvent e) {
   if(e.getSource() == resetButton) {
    String record= label.getText();
       milliseconds =0;
       seconds = 0;
       mins = 0;
       start(e);  
     lastRecord.setText("Stopwatch Last-Record :"+record);
     }
  }
}


    

