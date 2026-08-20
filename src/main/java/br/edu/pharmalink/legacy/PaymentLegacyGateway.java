package br.edu.pharmalink.legacy;
public class PaymentLegacyGateway {
    public int charge(String customer,double value,String method){
        return value>0 ? 0 : 99;
    }
}
