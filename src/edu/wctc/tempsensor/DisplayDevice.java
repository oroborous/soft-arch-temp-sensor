package edu.wctc.tempsensor;

import edu.wctc.iface.PullObserver;

import javax.swing.*;
import java.awt.*;


/**
 * This class can represent any device that displays the current
 * weather: your phone, a home assistant like Google Home, a wearable
 * device like an Apple Watch or FitBit.
 *
 * These displays want to be notified when the temperature
 * being registered by the weather sensor changes, so they
 * can update their screens.
 */
public class DisplayDevice extends JFrame implements PullObserver {

    private TemperatureSensor temperatureSensor;
    private String deviceName;
    private JLabel lblTemperature = new JLabel();

    public DisplayDevice(String deviceName) {
        // Invoke superclass constructor in JFrame that takes
        // the window's title
        super(deviceName);

        // Store this device's name
        this.deviceName = deviceName;

        // Set up the GUI
        initComponents();
    }

    /**
     * This method sets up the GUI components. It's just Java Swing
     * code and doesn't have anything to do with demonstrating the
     * observer pattern, so don't worry too much about what it's
     * doing.
     */
    private void initComponents() {
        lblTemperature.setFont(lblTemperature.getFont().deriveFont(Font.BOLD, 36));
        lblTemperature.setHorizontalAlignment(JLabel.CENTER);
        lblTemperature.setForeground(Color.WHITE);

        JLabel lblTop = new JLabel(deviceName);
        lblTop.setFont(lblTemperature.getFont());
        lblTop.setHorizontalAlignment(JLabel.CENTER);
        lblTop.setForeground(Color.WHITE);

        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlMain.setBackground(Color.BLACK);

        pnlMain.add(lblTop, BorderLayout.NORTH);
        pnlMain.add(lblTemperature, BorderLayout.SOUTH);

        getContentPane().add(pnlMain);

        setPreferredSize(new Dimension(500, 250));
    }

    /**
     * This method positions the window, lays out its
     * components, and shows it.
     * @param xPos Horizontally, pixels from top left screen corner
     * @param yPos Vertically, pixels from top left screen corner
     */
    public void showDisplay(int xPos, int yPos) {
        setLocation(xPos, yPos);
        pack();
        setVisible(true);
    }

    /**
     * Gives the observable weather sensor to the display device.
     * The device will then register itself as an observer of
     * the sensor.
     * @param temperatureSensor The weather sensor to observe
     */
    public void subscribe(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;

        // Tell the sensor, "I want to be notified when you change"
        this.temperatureSensor.addObserver(this);
    }

    @Override
    public void update() {
        double newTemp = temperatureSensor.getTemperature();
        lblTemperature.setText(Double.toString(newTemp));
    }

}
