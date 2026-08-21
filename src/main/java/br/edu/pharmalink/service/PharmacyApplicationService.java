package br.edu.pharmalink.service;

import br.edu.pharmalink.model.Medicine;
import br.edu.pharmalink.model.Order;
import br.edu.pharmalink.model.StockLot;
import br.edu.pharmalink.patterns.observer.AuditObserver;
import br.edu.pharmalink.patterns.observer.CustomerNotificationObserver;
import br.edu.pharmalink.patterns.observer.OrderPublisher;
import br.edu.pharmalink.patterns.strategy.DiscountCalculator;
import br.edu.pharmalink.repository.InMemoryMedicineRepository;
import br.edu.pharmalink.repository.InMemoryStockRepository;

import java.util.HashMap;
import java.util.Map;

// Coordena o fluxo principal da aplicação.
// Delega responsabilidades específicas para os outros serviços,
// evitando concentrar toda a lógica em uma única classe.
public class PharmacyApplicationService {

    private final InMemoryMedicineRepository medicines;
    private final Map<String, Order> orders = new HashMap<>();

    private final OrderPublisher publisher = new OrderPublisher();
    private final DiscountCalculator discounts = new DiscountCalculator();

    private final PrescriptionService prescriptionService = new PrescriptionService();
    private final StockService stockService;
    private final PaymentService paymentService = new PaymentService();
    private final DeliveryService deliveryService = new DeliveryService();
    private final NotificationService notificationService = new NotificationService();

    public PharmacyApplicationService(InMemoryMedicineRepository medicines, InMemoryStockRepository stock) {
        this.medicines = medicines;
        this.stockService = new StockService(stock);

        publisher.subscribe(new CustomerNotificationObserver());
        publisher.subscribe(new AuditObserver());
    }

    public void addMedicine(Medicine medicine) {
        medicines.save(medicine);
    }

    public void addStock(StockLot lot) {
        stockService.addStock(lot);
    }

    public Order createOrder(String id, String customer, String channel) {
        Order order = new Order(id, customer, channel);
        orders.put(id, order);

        publisher.publish(id, "ORDER_CREATED");

        return order;
    }

    public void addItem(String orderId, String medicineCode, String prescriptionNumber) {
        Order order = orders.get(orderId);
        Medicine medicine = medicines.find(medicineCode);

        if (order == null || medicine == null) {
            return;
        }

        if (!prescriptionService.canSell(medicine, prescriptionNumber)) {
            System.out.println("MEDICAMENTO EXIGE RECEITA. ITEM NAO ADICIONADO: " + medicineCode);
            return;
        }

        StockLot chosenLot = stockService.reserveAvailableLot(medicineCode);

        if (chosenLot == null) {
            System.out.println("SEM LOTE DISPONIVEL PARA O MEDICAMENTO: " + medicineCode);
            return;
        }

        order.medicineCodes.add(medicineCode);
        order.total += medicine.price;

        System.out.println("ITEM ADICIONADO: " + medicineCode + " | LOTE: " + chosenLot.lot);
    }

    public void finishOrder(String orderId) {
        Order order = orders.get(orderId);

        if (order == null) {
            return;
        }

        double discount = discounts.calculate(order);
        double payable = order.total - discount;

        boolean paymentApproved = paymentService.pay(order.customer, payable);

        if (!paymentApproved) {
            order.status = "PAYMENT_ERROR";
            notificationService.notifyCustomer(order);
            publisher.publish(order.id, "PAYMENT_ERROR");
            return;
        }

        order.status = "PAID";

        String protocol = deliveryService.dispatch(order);
        System.out.println(protocol);

        notificationService.notifyCustomer(order);
        publisher.publish(order.id, "ORDER_FINISHED");
    }

    public Order findOrder(String id) {
        return orders.get(id);
    }
}
