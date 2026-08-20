package br.edu.pharmalink.model;
import java.util.*;
public class Order {
    public String id;
    public String customer;
    public String channel;
    public String status="CREATED";
    public double total;
    public List<String> medicineCodes = new ArrayList<>();
    public Order(String id,String customer,String channel){this.id=id;this.customer=customer;this.channel=channel;}
}
