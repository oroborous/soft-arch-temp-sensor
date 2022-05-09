package edu.wctc.citybuilder.pull;

import edu.wctc.iface.PullObservable;
import edu.wctc.iface.PullObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Treasury extends JLabel implements PullObservable, Runnable {
    private int goldOnHand;

    public int getGoldOnHand() {
        return goldOnHand;
    }

    public Treasury() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void run() {
        while(true) {
            try {
                int randomGold = (int)Math.floor(Math.random() * 500) - 150;
                earn(randomGold);
                setText("Gold: " + goldOnHand);
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void earn(int gold) {
        goldOnHand += gold;
        notifyObservers();
    }

    private List<PullObserver> observerList = new ArrayList<>();

    @Override
    public void addObserver(PullObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(PullObserver::update);
    }

    @Override
    public void removeObserver(PullObserver observer) {
        observerList.remove(observer);
    }
}
