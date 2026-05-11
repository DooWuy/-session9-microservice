package pharmacy.pharmacyservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pharmacy.pharmacyservice.service.InsuranceVerificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/insurance")
public class InsuranceController {

    private final InsuranceVerificationService insuranceService;

    @PostMapping("/verify")
    public CompletableFuture<InsurancePriceResponse> verify(
            @RequestBody InsuranceVerifyRequest request
    ) {
        return insuranceService.verifyInsurance(request);
    }
}
