package pharmacy.pharmacyservice.dto;

import java.math.BigDecimal;

public record  InsuranceVerifyRequest(


        String insuranceCardNo,
        String medicineCode,
        BigDecimal originalPrice
) {
}
