package br.edu.pharmalink.patterns.strategy;
import br.edu.pharmalink.model.Order;
public interface DiscountStrategy { double calculate(Order order); }
