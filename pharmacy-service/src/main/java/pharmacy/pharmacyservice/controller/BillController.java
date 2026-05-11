package pharmacy.pharmacyservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bill")

public class BillController {

    @Value("${pharmacy.vat-rate}")
    private BigDecimal vatRate;

    @PostMapping
    public BillResponse calculateBill(@RequestBody BillRequest request) {
        BigDecimal medicineTotal = request.getMedicineTotal();

        BigDecimal vatAmount = medicineTotal
                .multiply(vatRate)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        BigDecimal totalAmount = medicineTotal.add(vatAmount);

        return new BillResponse(medicineTotal, vatRate, vatAmount, totalAmount);
    }

    @Data
    public static class BillRequest {
        private BigDecimal medicineTotal;
    }

    public record BillResponse(
            BigDecimal medicineTotal,
            BigDecimal vatRate,
            BigDecimal vatAmount,
            BigDecimal totalAmount
    ) {}
}
