package br.edu.pharmalink.service;

import br.edu.pharmalink.model.Medicine;

// Responsável pelas regras relacionadas à receita médica.
// Verifica se um medicamento pode ser vendido de acordo com
// a necessidade ou não de apresentação de receita.
public class PrescriptionService {

    public boolean canSell(Medicine medicine, String prescriptionNumber) {
        if (!medicine.prescriptionRequired) {
            return true;
        }

        return prescriptionNumber != null && !prescriptionNumber.isBlank();
    }
}