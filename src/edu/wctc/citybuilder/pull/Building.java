package edu.wctc.citybuilder.pull;

import edu.wctc.iface.PullObserver;

import javax.swing.*;

public class Building extends JButton implements PullObserver {
    private int upgradeCost;
    private boolean canUpgrade = false;
    private Treasury observable;

    public Building(String name, int upgradeCost) {
        super(name + ": " + upgradeCost);
        this.upgradeCost = upgradeCost;
    }

    public void register(Treasury treasury) {
        treasury.addObserver(this);
        this.observable = treasury;
    }

    @Override
    public void update() {
        this.canUpgrade =
                observable.getGoldOnHand() >= this.upgradeCost;
        setEnabled(this.canUpgrade);
    }

}
