package pharmacy.pharmacyservice.dto;

public record SellResponse(

        Boolean success,
        String warningMessage,
        String transactionMessage
) {
}
