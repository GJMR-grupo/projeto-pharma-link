package br.edu.pharmalink.patterns.facade;
import br.edu.pharmalink.service.PharmacyApplicationService;
import br.edu.pharmalink.patterns.adapter.*;
public class PharmaLinkFacade {
    public final PharmacyApplicationService service;
    public final SupplierAdapter supplier;
    public final PaymentAdapter payment;
    public PharmaLinkFacade(PharmacyApplicationService s,SupplierAdapter sup,PaymentAdapter pay){
        this.service=s;this.supplier=sup;this.payment=pay;
    }
    public void processOrder(String id){service.finishOrder(id);}
    public PharmacyApplicationService getService(){return service;}
}
