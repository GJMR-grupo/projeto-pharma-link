package br.edu.pharmalink.legacy;
public class SupplierLegacyApi {
    public String buy(String sku,int amount){
        return sku+";"+amount+";"+(amount<100?"CONFIRMED":"MANUAL");
    }
}
