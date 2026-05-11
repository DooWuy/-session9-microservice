package pharmacy.pharmacyservice.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ElectronicInvoiceService {


    private final RestTemplate restTemplate;

    @Retry(name = "invoiceRetry", fallbackMethod = "createInvoiceFallback")
    @RateLimiter(name = "invoiceRateLimiter", fallbackMethod = "createInvoiceFallback")
    public InvoiceResponse createElectronicInvoice(InvoiceRequest request) {
        String url = "http://invoice-service/api/v1/e-invoices";

        return restTemplate.postForObject(
                url,
                request,
                InvoiceResponse.class
        );
    }

    public InvoiceResponse createInvoiceFallback(
            InvoiceRequest request,
            Exception e
    ) {
        return new InvoiceResponse(
                false,
                "PENDING",
                "Không thể xuất hóa đơn điện tử ngay lúc này. Hệ thống đã ghi nhận yêu cầu và sẽ xử lý lại sau.",
                request.invoiceCode()
        );
    }
}
