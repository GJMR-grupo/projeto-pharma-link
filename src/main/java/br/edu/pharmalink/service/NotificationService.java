package br.edu.pharmalink.service;

import br.edu.pharmalink.legacy.WhatsappLegacyApi;
import br.edu.pharmalink.model.Order;

// Responsável pelo envio de notificações ao cliente.
// Centraliza a comunicação utilizada para informar
// atualizações sobre o pedido.
public class NotificationService {

    private final WhatsappLegacyApi whatsapp = new WhatsappLegacyApi();

    public void notifyCustomer(Order order) {
        whatsapp.send("000000000", "Pedido " + order.id + " atualizado: " + order.status);
    }
}