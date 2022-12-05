package edu.wctc.citybuilder.push;

import edu.wctc.iface.PushObserver;

import javax.swing.*;

public class ResearchMenuItem extends JButton implements PushObserver {
    private int researchCost;
    private boolean canResearch = false;

    public ResearchMenuItem(String name, int researchCost) {
        super(name + ": " + researchCost);
        this.researchCost = researchCost;
    }

    @Override
    public void update(int goldOnHand) {
        this.canResearch = goldOnHand >= researchCost;
        setEnabled(this.canResearch);
    }
}
