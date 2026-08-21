package br.edu.pharmalink.service;

import br.edu.pharmalink.legacy.PaymentLegacyGateway;

// Responsável pelo processamento do pagamento.
// Isola a comunicação com o sistema legado de pagamento,
// evitando que essa responsabilidade fique na classe principal.
public class PaymentService {

    private final PaymentLegacyGateway paymentGateway = new PaymentLegacyGateway();

    public boolean pay(String customer, double value) {
        int result = paymentGateway.charge(customer, value, "CARD");

        return result == 0;
    }
}
