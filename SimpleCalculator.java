import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {

    private final JTextField display;
    private double num1 = 0, result = 0;
    private String operator = "";

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(320, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        setResizable(false);

    
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 26));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(300, 60));
        add(display, BorderLayout.NORTH);

    
        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "⌫", "±", "%"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9": case ".":
                display.setText(display.getText() + command);
                break;

            case "+": case "-": case "*": case "/":
                num1 = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
                break;

            case "=":
                double num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            display.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(formatResult(result));
                break;

            case "C":
                display.setText("");
                num1 = 0;
                result = 0;
                operator = "";
                break;

            case "⌫":
                String text = display.getText();
                if (!text.isEmpty()) {
                    display.setText(text.substring(0, text.length() - 1));
                }
                break;

            case "±":
                if (!display.getText().isEmpty()) {
                    double val = Double.parseDouble(display.getText());
                    display.setText(formatResult(-val));
                }
                break;

            case "%":
                if (!display.getText().isEmpty()) {
                    double val = Double.parseDouble(display.getText());
                    display.setText(formatResult(val / 100));
                }
                break;
        }
    }

    private String formatResult(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }
} 