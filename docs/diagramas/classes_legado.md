# Diagrama de classes legado

```mermaid
classDiagram
    class PharmacyApplicationService
    class Medicine
    class StockLot
    class Order
    class PaymentLegacyGateway
    class DeliveryPartnerApi
    PharmacyApplicationService --> Medicine
    PharmacyApplicationService --> StockLot
    PharmacyApplicationService --> Order
    PharmacyApplicationService --> PaymentLegacyGateway
    PharmacyApplicationService --> DeliveryPartnerApi
```

O diagrama representa somente uma visão parcial do legado.
