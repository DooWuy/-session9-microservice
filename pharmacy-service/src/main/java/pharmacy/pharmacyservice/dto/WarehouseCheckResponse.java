package pharmacy.pharmacyservice.dto;

public record WarehouseCheckResponse(
        String medicineCode,
        Integer requestedQuantity,
        Integer warehouseAvailableQuantity,
        Boolean useLocalStock,
        String message


) {





}
