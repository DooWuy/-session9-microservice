package pharmacy.pharmacyservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@RefreshScope
@Service
@RequiredArgsConstructor
public class InsuranceVerificationService {

    private final WebClient insuranceWebClient;

    @TimeLimiter(name = "insuranceTL", fallbackMethod = "insuranceFallback")
    @Retry(name = "insuranceRetry", fallbackMethod = "insuranceFallback")
    @CircuitBreaker(name = "insuranceCB", fallbackMethod = "insuranceFallback")
    public CompletableFuture<InsurancePriceResponse> verifyInsurance(
            InsuranceVerifyRequest request
    ) {
        return insuranceWebClient.post()
                .uri("/api/v1/insurance/verify")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(InsurancePriceResponse.class)
                .toFuture();
    }

    public CompletableFuture<InsurancePriceResponse> insuranceFallback(
            InsuranceVerifyRequest request,
            Throwable e
    ) {
        InsurancePriceResponse response = new InsurancePriceResponse(
                false,
                request.originalPrice(),
                request.originalPrice(),
                "Xác thực bảo hiểm sau"
        );

        return CompletableFuture.completedFuture(response);
}
