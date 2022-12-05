package edu.wctc.citybuilder.pull;

import edu.wctc.iface.PullObserver;

import javax.swing.*;

public class ConstructionMenuItem extends JButton implements PullObserver {
    private int goldCost;
    private boolean canBuild = false;
    private Treasury observable;

    public ConstructionMenuItem(String name, int goldCost) {
        super(name + ": " + goldCost);
        this.goldCost = goldCost;
    }

    public void register(Treasury treasury) {
        treasury.addObserver(this);
        this.observable = treasury;
    }

    @Override
    public void update() {
        this.canBuild =
                observable.getGoldOnHand() >= this.goldCost;
        setEnabled(this.canBuild);
    }

}
