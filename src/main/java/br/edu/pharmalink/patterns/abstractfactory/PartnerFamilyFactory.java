package br.edu.pharmalink.patterns.abstractfactory;
import br.edu.pharmalink.legacy.*;
public class PartnerFamilyFactory {
    public Object createSupplier(String family){ return new SupplierLegacyApi(); }
    public Object createPayment(String family){ return new PaymentLegacyGateway(); }
    public Object createDelivery(String family){ return new DeliveryPartnerApi(); }
}
