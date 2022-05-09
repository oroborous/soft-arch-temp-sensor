package edu.wctc.citybuilder.pull;

import edu.wctc.iface.PullObserver;

import javax.swing.*;
import java.awt.*;

public class ResearchMenuItem extends JButton implements PullObserver {
    private String name;
    private int daysToComplete;
    private int researchCost;
    private boolean canResearch = false;
    private Treasury observable;

    public ResearchMenuItem(String name, int daysToComplete,
                            int researchCost) {
        super(name + ": " + researchCost);
        this.name = name;
        this.daysToComplete = daysToComplete;
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
