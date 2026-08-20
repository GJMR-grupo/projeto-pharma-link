package br.edu.pharmalink.patterns.strategy;
import br.edu.pharmalink.model.Order;
public class DiscountCalculator {
    private DiscountStrategy strategy;
    public void setStrategy(DiscountStrategy strategy){this.strategy=strategy;}
    public double calculate(Order order){
        if("APP".equals(order.channel)) return order.total * 0.10;
        if(order.total > 500) return order.total * 0.15;
        return strategy==null ? 0 : strategy.calculate(order);
    }
}
