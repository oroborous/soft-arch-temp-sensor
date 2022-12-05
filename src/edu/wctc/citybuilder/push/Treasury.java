package edu.wctc.citybuilder.push;

import edu.wctc.iface.PushObservable;
import edu.wctc.iface.PushObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Treasury extends JLabel implements PushObservable, Runnable {
    private int goldOnHand;

    public int getGoldOnHand() {
        return goldOnHand;
    }

    public Treasury() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    public void earn(int gold) {
        goldOnHand += gold;
        notifyObservers();
    }

    @Override
    public void run() {
        while(true) {
            try {
                int randomGold = (int)Math.floor(Math.random() * 500) - 200;
                earn(randomGold);
                setText("Gold: " + goldOnHand);
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private List<PushObserver> observerList = new ArrayList<>();

    @Override
    public void addObserver(PushObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(pushObserver -> pushObserver.update(goldOnHand));
    }

    @Override
    public void removeObserver(PushObserver observer) {
        observerList.remove(observer);
    }
}
