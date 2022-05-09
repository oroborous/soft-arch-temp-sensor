package edu.wctc.iface;

public interface PushObservable {
    void addObserver(PushObserver observer);

    void notifyObservers();

    void removeObserver(PushObserver observer);
}
