package edu.wctc;

import edu.wctc.tempsensor.Database;
import edu.wctc.tempsensor.DisplayDevice;
import edu.wctc.tempsensor.TemperatureSensor;
import edu.wctc.tempsensor.actions.ColdSnapAction;
import edu.wctc.tempsensor.actions.HeatWaveAction;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        // Create a new sensor
        TemperatureSensor temperatureSensor = new TemperatureSensor();

        // Create observers to observe the sensor
        DisplayDevice phone = new DisplayDevice("My Phone");
        DisplayDevice thermostat = new DisplayDevice("My Smart Watch");
        Database simpleDatabase = new Database();

        // Subscribe them to the observable sensor
        phone.subscribe(temperatureSensor);
        thermostat.subscribe(temperatureSensor);
        simpleDatabase.subscribe(temperatureSensor);

        // Set the windows' locations and show them
        phone.showDisplay(650, 100);
        thermostat.showDisplay(650, 350);

        // Add action listeners to the sensor's buttons.
        // The sensor only knows that these are ActionListeners.
        // It is not coupled to ColdSnapAction or HeatWaveAction.
        temperatureSensor.setColdSnapListener(new ColdSnapAction(temperatureSensor));
        temperatureSensor.setHeatWaveListener(new HeatWaveAction(temperatureSensor));

        // When the GUI windows close, close the
        // database's file writer
        temperatureSensor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                simpleDatabase.close();
            }
        });
    }
}
