package br.edu.pharmalink.service;

import br.edu.pharmalink.legacy.DeliveryPartnerApi;
import br.edu.pharmalink.model.Order;

// Responsável pelas operações relacionadas à entrega.
// Faz a comunicação com o serviço externo responsável
// pelo envio do pedido ao cliente.
public class DeliveryService {

    private final DeliveryPartnerApi deliveryPartnerApi = new DeliveryPartnerApi();

    public String dispatch(Order order) {
        return deliveryPartnerApi.dispatch(order.id, "ADDRESS_NOT_MODELED");
    }
}