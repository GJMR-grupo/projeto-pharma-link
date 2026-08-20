package br.edu.pharmalink.patterns.adapter;
import br.edu.pharmalink.legacy.SupplierLegacyApi;
public class SupplierAdapter extends SupplierLegacyApi {
    public boolean order(String medicineCode,int quantity){
        return buy(medicineCode,quantity).endsWith("CONFIRMED");
    }
    public String rawOrder(String medicineCode,int quantity){return buy(medicineCode,quantity);}
}
