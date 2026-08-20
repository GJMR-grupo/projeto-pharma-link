package br.edu.pharmalink.patterns.factory;
import br.edu.pharmalink.model.Order;
public class OrderFactory {
    public static Order create(String type,String id,String customer){
        if("DELIVERY".equals(type)) return new Order(id,customer,"APP");
        if("PICKUP".equals(type)) return new Order(id,customer,"STORE");
        return new Order(id,customer,type);
    }
}
