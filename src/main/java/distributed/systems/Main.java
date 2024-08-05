package distributed.systems;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // Private constructor to prevent instantiation
    private Main() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Transcendental Function Calculator"); // Create a new JFrame with the title
        frame.setSize(400, 400); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation

        // Create the panel
        JPanel panel = new JPanel(); // Create a new JPanel
        frame.add(panel); // Add the panel to the frame
        placeComponents(panel); // Call the method to place components on the panel

        // Set the frame visibility to true
        frame.setVisible(true); // Make the frame visible
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null); // Set the layout to null to use absolute positioning

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Transcendental Function Calculator"); // Create a welcome label
        welcomeLabel.setBounds(10, 10, 380, 25); // Set the position and size of the label
        panel.add(welcomeLabel); // Add the label to the panel

        // Label for x input
        JLabel userLabelX = new JLabel("Enter x:"); // Create a label for the x input
        userLabelX.setBounds(10, 50, 80, 25); // Set the position and size of the label
        panel.add(userLabelX); // Add the label to the panel

        // Text field for x input
        JTextField userTextX = new JTextField(20); // Create a text field for the x input
        userTextX.setBounds(100, 50, 165, 25); // Set the position and size of the text field
        panel.add(userTextX); // Add the text field to the panel

        // Label for y input
        JLabel userLabelY = new JLabel("Enter y:"); // Create a label for the y input
        userLabelY.setBounds(10, 80, 80, 25); // Set the position and size of the label
        panel.add(userLabelY); // Add the label to the panel

        // Text field for y input
        JTextField userTextY = new JTextField(20); // Create a text field for the y input
        userTextY.setBounds(100, 80, 165, 25); // Set the position and size of the text field
        panel.add(userTextY); // Add the text field to the panel

        // Label for the result
        JLabel resultLabel = new JLabel("Result:"); // Create a label for the result
        resultLabel.setBounds(10, 110, 80, 25); // Set the position and size of the label
        panel.add(resultLabel); // Add the label to the panel

        JLabel result = new JLabel(""); // Create a label for displaying the result
        result.setBounds(100, 110, 165, 25); // Set the position and size of the label
        panel.add(result); // Add the label to the panel

        JButton calculateButton = new JButton("Calculate"); // Create a button for calculation
        calculateButton.setBounds(10, 140, 150, 25); // Set the position and size of the button
        panel.add(calculateButton); // Add the button to the panel

        JButton exitButton = new JButton("Exit"); // Create a button for exiting the application
        exitButton.setBounds(170, 140, 150, 25); // Set the position and size of the button
        panel.add(exitButton); // Add the button to the panel

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(userTextX.getText()); // Parse the x input as a double
                    double y = Double.parseDouble(userTextY.getText()); // Parse the y input as a double
                    double resultValue = calculatePower(x, y); // Calculate x^y
                    result.setText(String.valueOf(resultValue)); // Display the result
                } catch (NumberFormatException ex) {
                    result.setText("Invalid input"); // Handle invalid input
                } catch (Exception ex) {
                    result.setText("Error: " + ex.getMessage()); // Handle other exceptions
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });
    }

    // Method to calculate x^y
    public static double calculatePower(double x, double y) {
        if (x < -1e6 || x > 1e6 || y < -1e6 || y > 1e6) {
            throw new IllegalArgumentException("Input values must be within the range of -1e6 to 1e6.");
        }

        if (x == 0 && y == 0) {
            throw new IllegalArgumentException("0^0 is undefined.");
        } else if (x == 0 && y > 0) {
            return 0;
        } else if (y == 0) {
            return 1;
        } else if (x < 0 && y != Math.floor(y)) {
            throw new IllegalArgumentException("Negative base with non-integer exponent results in a complex number.");
        }

        double result = 1.0;
        boolean negativeExponent = y < 0;
        y = Math.abs(y);

        while (y > 0) {
            if ((int) y % 2 == 1) {
                result *= x;
            }
            x *= x;
            y = Math.floor(y / 2);
        }

        return negativeExponent ? 1 / result : result;
    }
}
