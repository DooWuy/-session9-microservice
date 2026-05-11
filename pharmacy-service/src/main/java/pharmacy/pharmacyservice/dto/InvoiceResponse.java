package pharmacy.pharmacyservice.dto;

public record InvoiceResponse(


        Boolean success,
        String status,
        String message,
        String invoiceCode
) {
}
