package br.edu.pharmalink.repository;
import br.edu.pharmalink.model.StockLot;
import java.util.*;
public class InMemoryStockRepository {
    private final List<StockLot> lots=new ArrayList<>();
    public void add(StockLot l){lots.add(l);}
    public List<StockLot> findByMedicine(String code){
        List<StockLot> r=new ArrayList<>();
        for(StockLot l:lots) if(l.medicineCode.equals(code)) r.add(l);
        return r;
    }
}
