package se.scramble_studios.nehro.settlers_of_catan.java;

import se.scramble_studios.nehro.settlers_of_catan.java.gui_components.game_button.GameButton;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 07/01/2015.
 * <p>
 *     The game's main menu.
 *     Should display "Start"-, "Settings"-, and "Exit"-buttons
 * </p>
 */
public class MainMenu extends JFrame {
    public MainMenu() {
        // Sets title
        super("Settlers of Catan");

        // Creates JComponents
        JLayeredPane contentPane = new JLayeredPane();
        GameButton startButton = new GameButton("START", 100, 50);
        GameButton settingsButton = new GameButton("SETTINGS", 100, 50);
        GameButton exitButton = new GameButton("EXIT", 100, 50);

        /*
        * Adds listeners to the buttons
        * */
        exitButton.addButtonClickListener(new ExitButtonListener());

        /*
         * Modifies the JComponents
         */
        // Content pane
        contentPane.setBounds(0, 0, 500, 600);

        // Start button
        startButton.setBounds(50, 50, 100, 50);
        startButton.setOpaque(true);

        // Settings button
        settingsButton.setBounds(50, 150, 100, 50);
        settingsButton.setOpaque(true);

        // Exit button
        exitButton.setBounds(50, 250, 100, 50);
        exitButton.setOpaque(true);

        /*
         * Adds components to the content pane
         */
        contentPane.add(startButton, 1, 0);
        contentPane.add(settingsButton, 1, 0);
        contentPane.add(exitButton, 1, 0);

        // Modifies the JFrame
        this.setPreferredSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Adds JComponents to the frame
        this.add(contentPane, BorderLayout.CENTER);

        // Makes the frame show up correctly
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * <p>
     *     A Game Button Listener for the exit button which exits the program
     * </p>
     */
    class ExitButtonListener implements GameButton.GameButtonListener {
        @Override
        public void onButtonClick() {
            System.exit(0);
        }
    }
}
