package br.edu.pharmalink.service;

import br.edu.pharmalink.model.StockLot;
import br.edu.pharmalink.repository.InMemoryStockRepository;
import java.time.LocalDate;
import java.util.List;

// Responsável pelas operações relacionadas ao estoque.
// Procura lotes disponíveis, verifica validade e quantidade
// e realiza a reserva do medicamento.
public class StockService {

    private final InMemoryStockRepository stockRepository;

    public StockService(InMemoryStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void addStock(StockLot lot) {
        stockRepository.add(lot);
    }

    public StockLot reserveAvailableLot(String medicineCode) {
        List<StockLot> lots = stockRepository.findByMedicine(medicineCode);

        for (StockLot lot : lots) {
            if (isAvailable(lot)) {
                lot.quantity--;
                return lot;
            }
        }

        return null;
    }

    private boolean isAvailable(StockLot lot) {
        return lot.quantity > 0 && !isExpired(lot);
    }

    private boolean isExpired(StockLot lot) {
        LocalDate expirationDate = LocalDate.parse(lot.expirationDate);
        LocalDate today = LocalDate.now();

        return expirationDate.isBefore(today);
    }
}