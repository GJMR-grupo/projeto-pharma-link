package br.edu.pharmalink;
import br.edu.pharmalink.model.*;
import br.edu.pharmalink.repository.*;
import br.edu.pharmalink.service.*;
import br.edu.pharmalink.patterns.adapter.*;
import br.edu.pharmalink.patterns.facade.*;

public class Main {
    public static void main(String[] args){
        InMemoryMedicineRepository meds=new InMemoryMedicineRepository();
        InMemoryStockRepository stock=new InMemoryStockRepository();
        PharmacyApplicationService service=new PharmacyApplicationService(meds,stock);

        service.addMedicine(new Medicine("MED001","Amoxicilina 500mg","ANTIBIOTIC",true,42.90));
        service.addMedicine(new Medicine("MED002","Vitamina C","SUPPLEMENT",false,18.50));
        service.addStock(new StockLot("L001","MED001",5,"2025-01-01"));
        service.addStock(new StockLot("L002","MED002",20,"2027-12-31"));

        service.createOrder("PED001","Cliente Demo","APP");
        service.addItem("PED001","MED001","");
        service.finishOrder("PED001");

        PharmaLinkFacade facade=new PharmaLinkFacade(service,new SupplierAdapter(),new PaymentAdapter());
        System.out.println("FINAL="+facade.getService().findOrder("PED001").status);
    }
}
