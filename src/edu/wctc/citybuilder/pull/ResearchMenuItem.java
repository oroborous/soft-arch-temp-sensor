package edu.wctc.citybuilder.pull;

import edu.wctc.iface.PullObserver;

import javax.swing.*;

public class ResearchMenuItem extends JButton implements PullObserver {
    private int researchCost;
    private boolean canResearch = false;
    private Treasury observable;

    public ResearchMenuItem(String name,
                            int researchCost) {
        super(name + ": " + researchCost);
        this.researchCost = researchCost;
    }

    public void register(Treasury treasury) {
        treasury.addObserver(this);
        this.observable = treasury;
    }

    @Override
    public void update() {
        this.canResearch =
                observable.getGoldOnHand() >= this.researchCost;
        setEnabled(this.canResearch);
    }

}
