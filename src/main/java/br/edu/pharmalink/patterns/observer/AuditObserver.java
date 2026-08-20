package br.edu.pharmalink.patterns.observer;
public class AuditObserver implements OrderObserver {
    public void update(String id,String event){System.out.println("AUDIT "+id+" "+event);}
}
