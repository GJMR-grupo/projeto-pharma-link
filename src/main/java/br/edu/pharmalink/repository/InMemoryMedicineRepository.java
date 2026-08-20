package br.edu.pharmalink.repository;
import br.edu.pharmalink.model.Medicine;
import java.util.*;
public class InMemoryMedicineRepository {
    private final Map<String,Medicine> data=new HashMap<>();
    public void save(Medicine m){data.put(m.code,m);}
    public Medicine find(String code){return data.get(code);}
    public Collection<Medicine> all(){return data.values();}
}
