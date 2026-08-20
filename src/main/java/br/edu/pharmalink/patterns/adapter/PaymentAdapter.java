package br.edu.pharmalink.patterns.adapter;
import br.edu.pharmalink.legacy.PaymentLegacyGateway;
public class PaymentAdapter {
    private final PaymentLegacyGateway gateway=new PaymentLegacyGateway();
    public boolean pay(String customer,double value,String method){return gateway.charge(customer,value,method)==0;}
    public PaymentLegacyGateway legacy(){return gateway;}
}
