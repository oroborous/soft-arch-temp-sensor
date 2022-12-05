package edu.wctc.citybuilder.push;

import edu.wctc.iface.PushObserver;

import javax.swing.*;

public class ConstructionMenuItem extends JButton implements PushObserver {
    private int goldCost;
    private boolean canBuild = false;

    public ConstructionMenuItem(String name, int goldCost) {
        super(name + ": " + goldCost);
        this.goldCost = goldCost;
    }

    @Override
    public void update(int goldOnHand) {
        this.canBuild =
                goldOnHand >= this.goldCost;
        setEnabled(this.canBuild);
    }
}
