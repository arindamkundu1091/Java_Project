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
import javax.swing.JTextField;

public class AlarmClock {

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

    static String alarmClock(String alarm)
    {
        BeepSound beep = new BeepSound();
        String Alarm = "", setAlarm, clock;
        setAlarm = alarm;
        clock = getTime();
        try{
            outer:for(; ;) {
                clock = getTime();
                if(clock.equals(setAlarm)){
                    Alarm = "Wake up! It is "+clock;
                    for(int i = 0; i<=10; i++) {
                        beep.beepSound();
                    }
                    System.exit(0);
                    break outer;
                }
                Thread.sleep(1000);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return Alarm;
    }
    
    // declare a static Font variable which contain the font layout 
    final private static Font mainFont = new Font("Seoge UI", Font.BOLD, 20);

    public void initializeAlarmClock() {

        /***********************Set main Frame*****************************/
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(240,360);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setAlwaysOnTop(true);
        mainFrame.setResizable(false);
        
        /*************************set clock Panel*************************/
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(null);
        clockPanel.setBounds(10,10,220,340);
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

        JLabel alarmLabel = new JLabel();
        alarmLabel.setBounds(60,110,150,40);
        alarmLabel.setText("Set Alarm");
        alarmLabel.setFont(mainFont);
        clockPanel.add(alarmLabel);

        JTextField setAlarm = new JTextField(getTime());
        setAlarm.setBounds(60,170,90,40);
        setAlarm.setBackground(Color.cyan);
        setAlarm.setFont(mainFont);
        clockPanel.add(setAlarm);

        JLabel warnLabel = new JLabel();
        warnLabel.setBounds(10,280,200,40);
        clockPanel.add(warnLabel);

        JButton alarmBtn = new JButton("Set Alarm");
        alarmBtn.setBounds(50,230,100,40);
        alarmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarmClock(setAlarm.getText());
                String msg = alarmClock(setAlarm.getText());
                warnLabel.setText(msg);
            }
            
        });
        clockPanel.add(alarmBtn);

        mainFrame.setVisible(true);

        /***********************set time and date in jlabel********************/
        String realDate = getDate();
        String realTime = getTime();
                timeLabel.setText("Time:  "+realTime);
                dateLabel.setText("Date:  "+realDate);

    }
}
