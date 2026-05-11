package pharmacy.pharmacyservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pharmacy.pharmacyservice.dto.StockResponse;

@Service
@RequiredArgsConstructor
public class WarehouseClient {


    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "warehouseCB", fallbackMethod = "checkWarehouseFallback")
    public WarehouseCheckResponse checkWarehouse(String medicineCode, Integer quantity) {
        String url = "http://warehouse-service/api/v1/stock/check"
                + "?medicineCode=" + medicineCode
                + "&quantity=" + quantity;

        return restTemplate.getForObject(url, WarehouseCheckResponse.class);
    }

    public WarehouseCheckResponse checkWarehouseFallback(
            String medicineCode,
            Integer quantity,
            Exception e
    ) {
        return new WarehouseCheckResponse(
                medicineCode,
                quantity,
                null,
                true,
                "Không thể kết nối kho tổng. Hệ thống sẽ sử dụng dữ liệu tồn kho cục bộ để tiếp tục giao dịch"
        );
    }
}
