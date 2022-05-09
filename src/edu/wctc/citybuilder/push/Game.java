package edu.wctc.citybuilder.push;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        new Game();

    }

    private Treasury treasury = new Treasury();
    private List<Building> buildings = new ArrayList<>();
    private List<ConstructionMenuItem> constMenuItems = new ArrayList<>();
    private List<ResearchMenuItem> researchMenuItems = new ArrayList<>();
    private JFrame gameWindow;

    public Game() {
        createBuildings();
        createConstMenuItems();
        createResearchMenuItems();
        createGui();
        new Thread(treasury).start();
    }

    public void createGui() {
        gameWindow = new JFrame("City Builder (Push Model)");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pnlMain = new JPanel(new GridLayout(7, 1, 10, 10));
        pnlMain.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        pnlMain.add(treasury);
        pnlMain.add(new JLabel("Upgrade buildings"));
        pnlMain.add(new ButtonPanel(buildings));
        pnlMain.add(new JLabel("Build structures"));
        pnlMain.add(new ButtonPanel(constMenuItems));
        pnlMain.add(new JLabel("Research tech"));
        pnlMain.add(new ButtonPanel(researchMenuItems));
        gameWindow.getContentPane().add(pnlMain);
        gameWindow.pack();
        gameWindow.setVisible(true);
    }

    class ButtonPanel extends JPanel {
        ButtonPanel(List<? extends JButton> buttons) {
            setLayout(new GridLayout(1, buttons.size(), 10, 10));
            setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
            buttons.forEach(this::add);
        }
    }

    public void createBuildings() {
        buildings.add(new Building("granary", 250));
        buildings.add(new Building("forester", 475));
        buildings.add(new Building("blacksmith", 500));
        buildings.add(new Building("hospital", 750));
        for(Building b : buildings)
            treasury.addObserver(b);
    }

    public void createConstMenuItems() {
        constMenuItems.add(new ConstructionMenuItem("house", 100));
        constMenuItems.add(new ConstructionMenuItem("tailor", 300));
        constMenuItems.add(new ConstructionMenuItem("baker", 400));
        constMenuItems.add(new ConstructionMenuItem("dock", 700));
        for (ConstructionMenuItem cmi : constMenuItems)
            treasury.addObserver(cmi);

    }

    public void createResearchMenuItems() {
        researchMenuItems.add(new ResearchMenuItem("weaving", 10, 100));
        researchMenuItems.add(new ResearchMenuItem("trade", 45, 500));
        researchMenuItems.add(new ResearchMenuItem("steel", 30, 750));
        researchMenuItems.add(new ResearchMenuItem("banking", 30, 1000));
        for (ResearchMenuItem rmi : researchMenuItems)
            treasury.addObserver(rmi);
    }
}
