package pharmacy.pharmacyservice.dto;

public class BillResponse {

    private double medicineTotal;
    private double vatRate;
    private double vatAmount;
    private double finalTotal;

    public BillResponse(double medicineTotal, double vatRate, double vatAmount, double finalTotal) {
        this.medicineTotal = medicineTotal;
        this.vatRate = vatRate;
        this.vatAmount = vatAmount;
        this.finalTotal = finalTotal;
    }

    public double getMedicineTotal() {
        return medicineTotal;
    }

    public double getVatRate() {
        return vatRate;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public double getFinalTotal() {
        return finalTotal;
    }
}
