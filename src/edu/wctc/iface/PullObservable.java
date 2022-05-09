package edu.wctc.iface;

public interface PullObservable {
    void addObserver(PullObserver observer);

    void notifyObservers();

    void removeObserver(PullObserver observer);
}
