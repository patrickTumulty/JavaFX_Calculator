package com.pt.observer;

import javafx.scene.control.Button;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String string);
}
