package pharmacy.pharmacyservice.service;

import org.springframework.stereotype.Service;
import pharmacy.pharmacyservice.config.WarehouseClient;
import pharmacy.pharmacyservice.dto.StockResponse;
@Service
@RequiredArgsConstructor
public class RetailService {


    private final WarehouseClient warehouseClient;

    public void sellMedicine(String medicineCode, Integer quantity) {
        StockResponse stock = warehouseClient.checkStock(medicineCode, quantity);

        if (!Boolean.TRUE.equals(stock.enoughStock())) {
            throw new RuntimeException("Không đủ hàng tồn kho.");
        }


    }
}
