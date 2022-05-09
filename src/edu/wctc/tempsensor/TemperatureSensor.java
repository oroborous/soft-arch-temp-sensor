package edu.wctc.tempsensor;



import edu.wctc.iface.PullObservable;
import edu.wctc.iface.PullObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemperatureSensor extends JFrame implements PullObservable {
    // Create a randomizer to increase/reduce
    // the temperature by a small amount each tick
    Random randomChangeGenerator = new Random();

    // Set initial temperature to a pleasant 76F
    private double temperature = 76;

    // All observers who should be notified when the
    // temperature changes
    private List<PullObserver> observerList = new ArrayList<>();

    // Buttons that cause drastic temperature changes when clicked
    private JButton btnHeatWave = new JButton("Heat Wave!");
    private JButton btnColdSnap = new JButton("Cold Snap!");

    // A label to show the last +/- change in the temperature
    private JLabel lblTempChange = new JLabel();

    /**
     * Constructor
     */
    public TemperatureSensor() {
        // Invoke superclass constructor in JFrame that takes
        // the window's title
        super("Temperature Sensor");

        // Set up the GUI
        initComponents();

        // Start the random change generator
        runWeather();
    }

    /**
     * When an Observer implementation is interested in
     * being notified when the sensor changes,
     * it is passed to this method to be added to the
     * list.
     * @param observer Any Observer implementation
     */
    @Override
    public void addObserver(PullObserver observer) {
        observerList.add(observer);
    }

    /**
     * Change the  sensor's temperature by some amount.
     * @param adjustment
     */
    public void adjustTemperature(double adjustment) {
        // Update the field
        temperature += adjustment;
        // Update the screen display
        updateLabel(adjustment);
        // Notify any registered observers that this observable just changed
        notifyObservers();
    }

    /**
     * Returns the current temperature of this sensor.
     * @return
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * This method sets up the GUI components. It's just Java Swing
     * code and doesn't have anything to do with demonstrating the
     * observer pattern, so don't worry too much about what it's
     * doing.
     */
    private void initComponents() {
        JLabel lblTop = new JLabel("Temperature Sensor");
        lblTop.setFont(lblTop.getFont().deriveFont(Font.BOLD, 36));
        lblTop.setHorizontalAlignment(JLabel.CENTER);
        lblTop.setForeground(Color.BLACK);

        lblTempChange.setFont(lblTop.getFont());
        lblTempChange.setHorizontalTextPosition(JLabel.RIGHT);

        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlMain.setBackground(Color.WHITE);

        JPanel pnlButtons = new JPanel(new GridLayout(1, 2, 10, 10));
        pnlButtons.add(btnHeatWave);
        pnlButtons.add(btnColdSnap);

        pnlMain.add(lblTop, BorderLayout.NORTH);
        pnlMain.add(lblTempChange, BorderLayout.CENTER);
        pnlMain.add(pnlButtons, BorderLayout.SOUTH);

        getContentPane().add(pnlMain);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        setLocation(100, 100);

        pack();
        setVisible(true);
    }

    /**
     * Implements method from Observable interface.
     * Iterates the list of observers and tells them
     * that whatever they were observing (in this case,
     * the sensor) has been updated.
     */
    @Override
    public void notifyObservers() {
        for (PullObserver observer : observerList) {
            observer.update();
        }
    }

    /**
     * Unregister an observer that is no longer interested
     * in being notified when the sensor changes.
     * @param observer
     */
    @Override
    public void removeObserver(PullObserver observer) {
        observerList.remove(observer);
    }

    /**
     * Updates the display label on the sensor
     * @param adjustment The amount of the temperature change
     */
    private void updateLabel(double adjustment) {
        if (adjustment > 0) {
            lblTempChange.setForeground(Color.RED);
            lblTempChange.setText("+" + adjustment);
            lblTempChange.setIcon(new ImageIcon(getClass().getResource("hot.jpg")));
        } else {
            lblTempChange.setForeground(new Color(7, 90, 130));
            lblTempChange.setText(Double.toString(adjustment));
            lblTempChange.setIcon(new ImageIcon(getClass().getResource("cold.jpg")));
        }
    }

    /**
     * Starts a new thread to update the temperature once every 1.5 seconds.
     * This isn't part of the observer pattern; it just gives us a steady
     * progression of changes to observe.
     */
    private void runWeather() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    double adjustment = randomChangeGenerator.nextDouble() * 2 - 1;
                    adjustTemperature(adjustment);

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();

    }

    /**
     * Adds an action listener (implemented by ColdSnapAction)
     * to the Cold Snap button. When the button is clicked,
     * it will call the actionPerformed() method of all of
     * its registered listeners.
     * @param listener Any action listener implementation
     */
    public void setColdSnapListener(ActionListener listener) {
        btnColdSnap.addActionListener(listener);
    }

    /**
     * Adds an action listener (implemented by HeatWaveAction)
     * to the Heat Wave button. When the button is clicked,
     * it will call the actionPerformed() method of all of
     * its registered listeners.
     * @param listener Any action listener implementation
     */
    public void setHeatWaveListener(ActionListener listener) {
        btnHeatWave.addActionListener(listener);
    }
}
