import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class Calculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private String inputText = "";

    public Calculator() {
        setTitle("Scientific Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "sin", "cos", "sqrt"
        };

        for (String buttonLabel : buttons) {
            JButton button = new JButton(buttonLabel);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            try {
                double result = evaluateExpression(inputText);
                inputText = formatResult(result);
            } catch (Exception ex) {
                inputText = "Error";
            }
        } else if (command.equals("C")) {
            inputText = "";
        } else if (command.equals("sin")) {
            inputText = Double.toString(Math.sin(Double.parseDouble(inputText)));
        } else if (command.equals("cos")) {
            inputText = Double.toString(Math.cos(Double.parseDouble(inputText)));
        } else if (command.equals("sqrt")) {
            inputText = Double.toString(Math.sqrt(Double.parseDouble(inputText)));
        } else {
            inputText += command;
        }

        inputField.setText(inputText);
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%s", result);
        }
    }

    private double evaluateExpression(String expression) {
        return CalculatorEngine.evaluate(expression);
    }
}

class CalculatorEngine {
    public static double evaluate(String expression) {
        return CalculatorParser.evaluate(expression);
    }
}

class CalculatorParser {
    private static final ScriptEngineManager manager = new ScriptEngineManager();
    private static final ScriptEngine engine = manager.getEngineByName("js");

    public static double evaluate(String expression) {
        try {
            Object result = engine.eval(expression);
            if (result instanceof Number) {
                return ((Number) result).doubleValue();
            } else {
                throw new IllegalArgumentException("Invalid expression");
            }
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error evaluating expression");
        }
    }
}

public class scientificCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
