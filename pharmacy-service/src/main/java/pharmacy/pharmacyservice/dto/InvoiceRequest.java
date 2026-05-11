package pharmacy.pharmacyservice.dto;

import java.math.BigDecimal;

public record InvoiceRequest (

        String invoiceCode,
        String medicineCode,
        Integer quantity,
        BigDecimal totalAmount,
        String cashierMachineId
) {



}
