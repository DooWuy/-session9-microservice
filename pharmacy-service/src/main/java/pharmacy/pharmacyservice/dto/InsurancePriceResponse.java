package pharmacy.pharmacyservice.dto;

import java.math.BigDecimal;

public record InsurancePriceResponse(



        Boolean verified,
        BigDecimal originalPrice,
        BigDecimal finalPrice,
        String note
){
}
