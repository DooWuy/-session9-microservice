package pharmacy.pharmacyservice.config;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WarehouseClient {


    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "warehouseCB", fallbackMethod = "checkStockFallback")
    public StockResponse checkStock(String medicineCode, Integer quantity) {
        String url = "http://warehouse-service/api/v1/stock/check"
                + "?medicineCode=" + medicineCode
                + "&quantity=" + quantity;

        return restTemplate.getForObject(url, StockResponse.class);
    }

    public StockResponse checkStockFallback(
            String medicineCode,
            Integer quantity,
            Throwable throwable
    ) {
        throw new WarehouseUnavailableException(
                "hien tai kho khong phai hoi , vui long thu lai sai "
        );
    }
}
