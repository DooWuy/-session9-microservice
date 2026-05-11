package pharmacy.pharmacyservice.dto;

public record StockResponse(
        String medicineCode,
        Integer availableQuantity,
        Boolean enoughStock
) {}
