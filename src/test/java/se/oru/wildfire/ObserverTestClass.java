package se.oru.wildfire;

public class ObserverTestClass implements Observer{

    public boolean isNotified = false;
    @Override
    public void newUpdate(Notifier o) {
        isNotified = true;
    }
}
