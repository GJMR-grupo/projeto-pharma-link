package br.edu.pharmalink.legacy;
public class DeliveryPartnerApi {
    public String dispatch(String orderId,String address){
        return "PROTO-"+orderId+"|DISPATCHED|"+address;
    }
}
