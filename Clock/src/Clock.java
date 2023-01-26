import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Clock {

    // Create a method which return date
    static String getDate()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
         String Date = dtf.format(now);
         return Date;
    }

    // create a method which return time
    static String getTime() 
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String Time = dtf.format(now);
        return Time;
    }
    
    // declare a static Font variable which contain the font layout 
    final private static Font mainFont = new Font("Seoge UI", Font.BOLD, 20);

    public static void initializeClock() {

        /***********************Set main Frame*****************************/
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(240,200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setAlwaysOnTop(true);
        mainFrame.setResizable(false);
        
        /*************************set clock Panel*************************/
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(null);
        clockPanel.setBounds(10,10,220,180);
        clockPanel.setBackground(Color.cyan);
        mainFrame.add(clockPanel);

        /******************set jlabel for time***********************/
        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(10,50,200,50);
        timeLabel.setFont(mainFont);
        clockPanel.add(timeLabel);

        /***********************set jlabel for date************************/
        JLabel dateLabel = new JLabel();
        dateLabel.setBounds(10,0,200,40);
        dateLabel.setFont(mainFont);
        clockPanel.add(dateLabel);

        JButton alarm = new JButton("Alarm");
        alarm.setBounds(70,110,80,40);
        alarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlarmClock alarm = new AlarmClock();
                alarm.initializeAlarmClock();
            }
            
        });
        clockPanel.add(alarm);

        mainFrame.setVisible(true);

        /***********************set time and date in jlabel********************/
        String realDate = getDate();
        String realTime = getTime();
        try{
            for(;;){
                realTime = getTime();
                timeLabel.setText("Time:  "+realTime);
                dateLabel.setText("Date:  "+realDate);
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        // AlarmClock alarm = new AlarmClock();
        // alarm.initializeAlarmClock();
        initializeClock();
    }
}

