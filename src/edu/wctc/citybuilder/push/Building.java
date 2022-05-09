package edu.wctc.citybuilder.push;

import edu.wctc.iface.PushObserver;

import javax.swing.*;

public class Building extends JButton implements PushObserver {
    private String name;
    private int upgradeCost;
    private boolean canUpgrade = false;

    public Building(String name, int upgradeCost) {
        super(name + ": " + upgradeCost);
        this.name = name;
        this.upgradeCost = upgradeCost;
    }

    @Override
    public void update(int goldOnHand) {
        this.canUpgrade =
                goldOnHand >= this.upgradeCost;
        setEnabled(this.canUpgrade);
    }
}
