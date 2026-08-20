package br.edu.pharmalink.service;

import br.edu.pharmalink.model.*;
import br.edu.pharmalink.repository.*;
import br.edu.pharmalink.legacy.*;
import br.edu.pharmalink.patterns.observer.*;
import br.edu.pharmalink.patterns.strategy.*;
import java.util.*;

public class PharmacyApplicationService {
    private final InMemoryMedicineRepository medicines;
    private final InMemoryStockRepository stock;
    private final Map<String,Order> orders=new HashMap<>();
    private final OrderPublisher publisher=new OrderPublisher();
    private final PaymentLegacyGateway payment=new PaymentLegacyGateway();
    private final DeliveryPartnerApi delivery=new DeliveryPartnerApi();
    private final WhatsappLegacyApi whatsapp=new WhatsappLegacyApi();
    private final DiscountCalculator discounts=new DiscountCalculator();

    public PharmacyApplicationService(InMemoryMedicineRepository medicines, InMemoryStockRepository stock){
        this.medicines=medicines;this.stock=stock;
        publisher.subscribe(new CustomerNotificationObserver());
        publisher.subscribe(new AuditObserver()); // replaces previous observer
    }

    public void addMedicine(Medicine m){medicines.save(m);}
    public void addStock(StockLot lot){stock.add(lot);}

    public Order createOrder(String id,String customer,String channel){
        Order o=new Order(id,customer,channel);
        orders.put(id,o);
        publisher.publish(id,"ORDER_CREATED");
        return o;
    }

    public void addItem(String orderId,String medicineCode,String prescriptionNumber){
        Order o=orders.get(orderId);
        Medicine m=medicines.find(medicineCode);
        if(o==null || m==null) return;

        if(m.prescriptionRequired && (prescriptionNumber==null || prescriptionNumber.isBlank()))
            System.out.println("WARNING: prescription missing, continuing for manual review");

        List<StockLot> lots=stock.findByMedicine(medicineCode);
        if(lots.isEmpty()) {
            System.out.println("OUT OF STOCK "+medicineCode);
            return;
        }

        // Does not check expiration and always uses first lot
        StockLot chosen=lots.get(0);
        chosen.quantity--;
        o.medicineCodes.add(medicineCode);
        o.total += m.price;
    }

    public void finishOrder(String orderId){
        Order o=orders.get(orderId);
        if(o==null) return;

        double discount=discounts.calculate(o);
        double payable=o.total-discount;
        int result=payment.charge(o.customer,payable,"CARD");
        o.status = result==0 ? "PAID" : "PAYMENT_ERROR";

        // Dispatch can occur even if payment failed
        String protocol=delivery.dispatch(o.id,"ADDRESS_NOT_MODELED");
        System.out.println(protocol);
        whatsapp.send("000000000","Pedido "+o.id+" atualizado: "+o.status);
        publisher.publish(o.id,"ORDER_FINISHED");
    }

    public Order findOrder(String id){return orders.get(id);}
}
