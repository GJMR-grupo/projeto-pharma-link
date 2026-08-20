package br.edu.pharmalink.model;
public class Medicine {
    public String code;
    public String name;
    public String category;
    public boolean prescriptionRequired;
    public double price;
    public Medicine(String code,String name,String category,boolean prescriptionRequired,double price){
        this.code=code; this.name=name; this.category=category;
        this.prescriptionRequired=prescriptionRequired; this.price=price;
    }
}
