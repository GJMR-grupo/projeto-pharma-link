package br.edu.pharmalink.patterns.observer;
public class CustomerNotificationObserver implements OrderObserver {
    public void update(String id,String event){System.out.println("CUSTOMER "+id+" "+event);}
}
