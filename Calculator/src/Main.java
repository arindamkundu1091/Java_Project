import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main implements ActionListener, KeyListener {

    static JTextField textField1, textField;
    static double result = 0;
    static String input = "";
    final private static Font mainFont = new Font("Seoge UI", Font.BOLD, 16);
    final private static Font mainFont1 = new Font("Seoge UI", Font.BOLD, 12);

    public static double calculation() {
        String s = textField.getText();
        return new Object() {
            int pos = -1 , ch ;
            void nextChar() {
                ch = (++pos <s.length()) ? s.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while(ch == ' ')nextChar();
                if(ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() {
                nextChar();
                double x = parseExpression();
                if(pos < s.length()) throw new RuntimeException("Unexpected: "+ (char)ch);
                return x;
            }
            double parseExpression() {
                double x = parseTerm();
                for(; ;) {
                    if (eat('+')) x+= parseTerm();
                    else if(eat('-')) x-= parseTerm();
                    else return x;
                }
            }
            double parseTerm() {
                double x = parseFactor();
                for( ;  ;) {
                    if(eat('*')) x*= parseFactor();
                    else if(eat('/')) x/= parseFactor();
                    else return x;
                }
            }
            double parseFactor() {
                if(eat('+')) return +parseFactor();
                if(eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if(eat('(')) {
                    x = parseExpression();
                    if(!eat(')')) throw new RuntimeException("Missing ')'");

                }
                else if((ch >= '0' && ch <= '9')|| ch == '.') {
                    while((ch >='0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(s.substring(startPos, this.pos));
                }
                else if((ch >= 'a' && ch <= 'z')) {
                    while((ch >= 'a' && ch <= 'z')) nextChar();
                    String func = s.substring(startPos, this.pos);
                    if(eat('(')) {
                        x = parseExpression();
                        if(!eat(')')) throw new RuntimeException("Missing ')' after argument to "+func);
                    }
                    else {
                        x = parseFactor();
                    }
                    if(func.equals("log")) x = Math.log(x);
                    else if(func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if(func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if(func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else textField1.setText("Syntax Error");
                }
                else {
                    textField1.setText("Syntax Error");
                    throw new RuntimeException("Unexpected: "+(char)ch);
                }
                if(eat('^')) x = Math.pow(x, parseFactor());

                return x;
            } 
        }.parse();
    }

    public static String Pressed(String text) {
        text = textField.getText() + text;
        textField.setText(text);
        return text;
    }

    public static void main(String[] args) {
        
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(295, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        panel.setBackground(Color.cyan);
        frame.add(panel);
        panel.setLayout(null);
        
        
        textField1 = new JTextField();
        textField1.setBounds(10,50,260,30);
        textField1.setBackground(Color.getHSBColor(255,25,255));;
        textField1.setFont(mainFont);
        panel.add(textField1);
        
        textField = new JTextField();
        textField.setBounds(10,10,260,35);
        textField.setBackground(Color.getHSBColor(255,25,255));;
        textField.setFont(mainFont);
        panel.add(textField);


        JButton btn_1 = new JButton("←");
        btn_1.setBounds(10,85,50,40);
        btn_1.setFont(mainFont);
        btn_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = Pressed("");
                String nText = text.substring(0,(text.length()-1));
                textField.setText(nText);
            }
        });
        panel.add(btn_1);
        
        JButton btn_2 = new JButton("C");
        btn_2.setBounds(61,85,50,40);
        btn_2.setFont(mainFont);
        btn_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(null);
                textField1.setText(null);
            }
        });
        panel.add(btn_2);

        JButton btn_3 = new JButton("=");
        btn_3.setBounds(112,85,50,40);
        btn_3.setFont(mainFont);
        btn_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = "";
                result =""+ calculation();
                textField1.setText(result);
            }
        });
        panel.add(btn_3);

        JButton btn_4 = new JButton("+");
        btn_4.setBounds(163,85,50,40);
        btn_4.setFont(mainFont);
        btn_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("+");
            }
        });
        panel.add(btn_4);

        JButton btn_21= new JButton("(");
        btn_21.setBounds(214,85,55,40);
        btn_21.setFont(mainFont);
        btn_21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("(");
            }
        });
        panel.add(btn_21);

        JButton btn_5 = new JButton("1");
        btn_5.setBounds(10,130,50,40);
        btn_5.setFont(mainFont);
        btn_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("1");
            }
        });
        panel.add(btn_5);
        
        JButton btn_6 = new JButton("2");
        btn_6.setBounds(61,130,50,40);
        btn_6.setFont(mainFont);
        btn_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("2");
            }
        });
        panel.add(btn_6);
        
        JButton btn_7 = new JButton("3");
        btn_7.setBounds(112,130,50,40);
        btn_7.setFont(mainFont);
        btn_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("3");
            }
        });
        panel.add(btn_7);
        
        JButton btn_8 = new JButton("-");
        btn_8.setBounds(163,130,50,40);
        btn_8.setFont(mainFont);
        btn_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("-");
            }
        });
        panel.add(btn_8);

        JButton btn_22= new JButton(")");
        btn_22.setBounds(214,130,55,40);
        btn_22.setFont(mainFont);
        btn_22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed(")");
            }
        });
        panel.add(btn_22);
        
        JButton btn_9 = new JButton("4");
        btn_9.setBounds(10,175,55,40);
        btn_9.setFont(mainFont);
        btn_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("4");
            }
        });
        panel.add(btn_9);
        
        JButton btn_10 = new JButton("5");
        btn_10.setBounds(61,175,50,40);
        btn_10.setFont(mainFont);
        btn_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("5");
            }
        });
        panel.add(btn_10);
        
        JButton btn_11 = new JButton("6");
        btn_11.setBounds(112,175,50,40);
        btn_11.setFont(mainFont);
        btn_11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("6");
            }
        });
        panel.add(btn_11);
        
        JButton btn_12 = new JButton("*");
        btn_12.setBounds(163,175,50,40);
        btn_12.setFont(mainFont);
        btn_12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("*");
            }
        });
        panel.add(btn_12);

        JButton btn_23= new JButton("sin");
        btn_23.setBounds(214,175,55,40);
        btn_23.setFont(mainFont1);
        btn_23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("sin");
            }
        });
        panel.add(btn_23);

        JButton btn_13 = new JButton("7");
        btn_13.setBounds(10,220,50,40);
        btn_13.setFont(mainFont);
        btn_13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("7");
            }
        });
        panel.add(btn_13);
        
        JButton btn_14 = new JButton("8");
        btn_14.setBounds(61,220,50,40);
        btn_14.setFont(mainFont);
        btn_14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("8");
            }
        });
        panel.add(btn_14);
        
        JButton btn_15 = new JButton("9");
        btn_15.setBounds(112,220,50,40);
        btn_15.setFont(mainFont);
        btn_15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("9");
            }
        });
        panel.add(btn_15);
        
        JButton btn_16 = new JButton("/");
        btn_16.setBounds(163,220,50,40);
        btn_16.setFont(mainFont);
        btn_16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("/");
            }
        });
        panel.add(btn_16);

        JButton btn_24= new JButton("cos");
        btn_24.setBounds(214,220,55,40);
        btn_24.setFont(mainFont1);
        btn_24.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("cos");
            }
        });
        panel.add(btn_24);
        
        JButton btn_17 = new JButton("0");
        btn_17.setBounds(10,265,45,40);
        btn_17.setFont(mainFont);
        btn_17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("0");
            }
        });
        panel.add(btn_17);
        
        JButton btn_18 = new JButton("00");
        btn_18.setBounds(56,265,50,40);
        btn_18.setFont(mainFont1);
        btn_18.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("00");
            }
        });
        panel.add(btn_18);

        JButton btn_19 = new JButton("log");
        btn_19.setBounds(102,265,60,40);
        btn_19.setFont(mainFont1);
        btn_19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("log");
            }
        });
        panel.add(btn_19);

        JButton btn_20 = new JButton("^");
        btn_20.setBounds(163,265,50,40);
        btn_20.setFont(mainFont);
        btn_20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("^");
            }
        });
        panel.add(btn_20);

        JButton btn_25= new JButton("tan");
        btn_25.setBounds(214,265,55,40);
        btn_25.setFont(mainFont1);
        btn_25.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pressed("tan");
            }
        });
        panel.add(btn_25);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {}
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {}
    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {}
}
