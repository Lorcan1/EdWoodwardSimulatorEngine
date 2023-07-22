package com.example.matchEngine.observerPattern;

import com.example.matchEngine.observerPattern.Observer;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
