package br.edu.pharmalink.model;
public class StockLot {
    public String lot;
    public String medicineCode;
    public int quantity;
    public String expirationDate;
    public StockLot(String lot,String medicineCode,int quantity,String expirationDate){
        this.lot=lot; this.medicineCode=medicineCode; this.quantity=quantity; this.expirationDate=expirationDate;
    }
}
