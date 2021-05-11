package edu.wctc.tempsensor.actions;


import edu.wctc.tempsensor.TemperatureSensor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener is an interface from Java Swing.
 * GUI components can have one or more action listeners
 * add to them.
 *
 * When a GUI component's "action" is performed (a button
 * is clicked, a checkbox is checked, etc.), that
 * component will notify all of its listeners. It will
 * call the actionPerformed method implemented below.
 *
 * It's then up to the listener implementation (like
 * HeatWaveAction or ColdWaveAction) to do whatever it's
 * job is, like increase or decrease the temperature.
 */
public class HeatWaveAction implements ActionListener {

    private TemperatureSensor temperatureSensor;

    public HeatWaveAction(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action listeners don't know anything about the
        // GUI component that triggered them. This listener
        // is attached to a button, but it could just as
        // easily be a dropdown box or checkbox.
        temperatureSensor.adjustTemperature(25);
    }
}
