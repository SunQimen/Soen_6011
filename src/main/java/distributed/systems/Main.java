package distributed.systems;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
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

        // Button to trigger the calculation
        JButton calculateButton = new JButton("Calculate"); // Create a button for calculation
        calculateButton.setBounds(10, 110, 150, 25); // Set the position and size of the button
        panel.add(calculateButton); // Add the button to the panel

        // Label for the result
        JLabel resultLabel = new JLabel("Result:"); // Create a label for the result
        resultLabel.setBounds(10, 140, 80, 25); // Set the position and size of the label
        panel.add(resultLabel); // Add the label to the panel

        // Text area to display the result
        JTextArea result = new JTextArea(""); // Create a text area for displaying the result
        result.setBounds(100, 140, 250, 50); // Set the position and size of the text area
        result.setLineWrap(true); // Enable line wrapping
        result.setWrapStyleWord(true); // Wrap lines at word boundaries
        result.setEditable(false); // Make the text area non-editable
        panel.add(result); // Add the text area to the panel

        // Button to exit the application
        JButton exitButton = new JButton("Exit"); // Create a button to exit the application
        exitButton.setBounds(10, 200, 150, 25); // Set the position and size of the button
        panel.add(exitButton); // Add the button to the panel

        // Add action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
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

        // Add action listener for the exit button
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });
    }

    // Method to calculate x^y
    static double calculatePower(double x, double y) {
        if (x < -1e6 || x > 1e6 || y < -1e6 || y > 1e6) { // Validate input range
            throw new IllegalArgumentException("Input values must be within the range of -1e6 to 1e6."); // Throw exception for invalid input range
        }

        // Handle special cases
        if (x == 0 && y > 0) {
            return 0; // Handle 0^positive cases
        } else if (y == 0) {
            return 1; // Handle any number to the power of 0
        } else if (x == 0 && y == 0) {
            throw new IllegalArgumentException("0^0 is undefined."); // Handle 0^0 case
        } else if (x < 0 && y != Math.floor(y)) {
            throw new IllegalArgumentException("Negative base with non-integer exponent results in a complex number."); // Handle negative base with non-integer exponent
        }

        double result = 1.0; // Initialize result
        boolean negativeExponent = y < 0; // Check if exponent is negative
        y = Math.abs(y); // Use absolute value of exponent

        // Using Exponentiation by Squaring
        while (y > 0) {
            if ((int) y % 2 == 1) { // If y is odd
                result *= x; // Multiply result by x
            }
            x *= x; // Square x
            y = Math.floor(y / 2); // Divide y by 2
        }

        return negativeExponent ? 1 / result : result; // Return the result, considering if the exponent was negative
    }
}