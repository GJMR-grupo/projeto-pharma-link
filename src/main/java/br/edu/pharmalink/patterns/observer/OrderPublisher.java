package br.edu.pharmalink.patterns.observer;
public class OrderPublisher {
    private OrderObserver observer;
    public void subscribe(OrderObserver o){observer=o;}
    public void publish(String id,String e){if(observer!=null) observer.update(id,e);}
}
