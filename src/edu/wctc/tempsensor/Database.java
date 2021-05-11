package edu.wctc.tempsensor;


import edu.wctc.tempsensor.iface.Observer;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * This class represents a simple database that keeps
 * a history of the temperature. It wants to be notified
 * when the temperature at the sensor changes so it can
 * write to its file.
 */
public class Database implements Observer {
    private TemperatureSensor temperatureSensor;

    // A simple "database" -- just writing lines to a file
    private PrintWriter output;

    public Database() {
        try {
            // Create the log file
            this.output = new PrintWriter("weatherHistory.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the application closes (when
     * the window closes)
     */
    public void close() {
        output.close();
    }

    /**
     * Gives the observable sensor to the database.
     * The database will then register itself as an observer of
     * the sensor.
     * @param temperatureSensor The sensor to observe
     */
    public void subscribe(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;

        // Tell the sensor, "I want to be notified when you change"
        this.temperatureSensor.addObserver(this);
    }

    @Override
    public void update() {
        // Write an entry to the log file
        output.print(LocalDateTime.now());
        output.print(" ");
        output.println(temperatureSensor.getTemperature());
        output.flush();
    }
}
