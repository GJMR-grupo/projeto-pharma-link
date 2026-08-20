package br.edu.pharmalink.model;
public class Prescription {
    public String number;
    public String patient;
    public String medicineCode;
    public String validUntil;
    public Prescription(String number,String patient,String medicineCode,String validUntil){
        this.number=number;this.patient=patient;this.medicineCode=medicineCode;this.validUntil=validUntil;
    }
}
