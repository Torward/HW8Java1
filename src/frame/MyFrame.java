package frame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private final JTextField myTextField;
    private char operation;
    private double temporaryDigit;
    private double total;
    private boolean isFirstDigit;
    private JPanel myPanel = new JPanel();

    public MyFrame() throws HeadlessException {
        setTitle("Калькулятор");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 200, 300);
        setResizable(false);

        setAlwaysOnTop(true);
        isFirstDigit = true;

        temporaryDigit = 0;


        myPanel.setBackground(Color.white);
        JButton[] buttonArr = new JButton[9];
        myTextField = new JTextField(17);
        myTextField.setEditable(false);
        myPanel.add(myTextField);
        for (int i = 0; i < buttonArr.length; i++) {
            buttonArr[i] = new JButton(String.valueOf(i + 1));
            buttonArr[i].setPreferredSize(new Dimension(50, 30));
            buttonArr[i].setBackground(Color.lightGray);
            buttonArr[i].setBorderPainted(false);
            buttonArr[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    action(e);
                }
            });
            myPanel.add(buttonArr[i]);
        }
        JButton buttonZero = new JButton("0");
        buttonZero.setPreferredSize(new Dimension(50, 30));
        buttonZero.setBackground(Color.lightGray);
        buttonZero.setBorderPainted(false);
        JButton buttonPlus = new JButton("+");
        buttonPlus.setPreferredSize(new Dimension(50, 30));
        buttonPlus.setBackground(Color.gray);
        JButton buttonMinus = new JButton("-");
        buttonMinus.setPreferredSize(new Dimension(50, 30));
        buttonMinus.setBackground(Color.gray);
        JButton buttonEquals = new JButton("=");
        buttonEquals.setPreferredSize(new Dimension(50, 30));
        buttonEquals.setBackground(Color.getHSBColor(0.58f, 0.37f, 0.95f));
        buttonEquals.setBorderPainted(false);
        JButton buttonMultiply = new JButton("*");
        buttonMultiply.setPreferredSize(new Dimension(50, 30));
        buttonMultiply.setBackground(Color.gray);
        JButton buttonDivide = new JButton("/");
        buttonDivide.setPreferredSize(new Dimension(50, 30));
        buttonDivide.setBackground(Color.gray);
        JButton buttonNullification = new JButton("C");
        buttonNullification.setPreferredSize(new Dimension(50, 30));
        buttonNullification.setBackground(Color.getHSBColor(0, 49, 88));
        buttonNullification.setBorderPainted(false);
        JButton buttonPoint = new JButton(".");
        buttonPoint.setPreferredSize(new Dimension(50, 30));
        buttonPoint.setBackground(Color.gray);
        buttonPoint.setBorderPainted(false);
        buttonZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        buttonEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculating(e);
            }
        });
        buttonMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        buttonDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        buttonNullification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erase(e);
            }
        });
        buttonPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });


        myPanel.add(buttonZero);
        myPanel.add(buttonPlus);
        myPanel.add(buttonMinus);
        myPanel.add(buttonEquals);
        myPanel.add(buttonMultiply);
        myPanel.add(buttonDivide);
        myPanel.add(buttonNullification);
        myPanel.add(buttonPoint);


        add(myPanel);
        setVisible(true);

    }

    private void erase(ActionEvent a) {
        myTextField.setText("");
    }

    private void calculating(ActionEvent a) throws NumberFormatException {

        String text = myTextField.getText();
        String digit = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '-' || c == '+' || c == '*' || c == '/') {
                try {

                    double currentValue = Double.parseDouble(digit);
                    if (isFirstDigit) {
                        temporaryDigit += currentValue;
                        isFirstDigit = false;
                    } else {


                        if (operation == '-') {
                            temporaryDigit -= currentValue;
                        }
                        if (operation == '*') {
                            temporaryDigit *= currentValue;
                        }
                        if (operation == '/') {
                            temporaryDigit /= currentValue;
                        } else if (operation == '+') {
                            temporaryDigit += currentValue;
                        }
                    }
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Вы совершили действие приведшие к сбою в работе системы! Десять лет расстрела!");
                    myTextField.setText("");
                }
                digit = "";
                operation = c;
                continue;
            }
            digit += c;
        }


        double currentValue = Double.parseDouble(digit);

        if (operation == '-') {
            temporaryDigit -= currentValue;
        }
        if (operation == '*') {
            temporaryDigit *= currentValue;
        }
        if (operation == '/') {
            temporaryDigit /= currentValue;
        } else if (operation == '+') {
            temporaryDigit += currentValue;
        }

        isFirstDigit = true;
        myTextField.setText(String.valueOf(temporaryDigit));
        temporaryDigit = 0;
        total = 0;

    }

    public void action(ActionEvent event) {
        myTextField.setText(myTextField.getText() + event.getActionCommand());
    }
}
