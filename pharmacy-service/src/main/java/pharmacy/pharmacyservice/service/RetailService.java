package pharmacy.pharmacyservice.service;

import org.springframework.stereotype.Service;
import pharmacy.pharmacyservice.dto.StockResponse;
@Service
@RequiredArgsConstructor
public class RetailService {


    private final WarehouseClient warehouseClient;
    private final LocalStockRepository localStockRepository;

    public SellResponse sellMedicine(String medicineCode, Integer quantity) {
        WarehouseCheckResponse warehouseResult =
                warehouseClient.checkWarehouse(medicineCode, quantity);

        if (Boolean.TRUE.equals(warehouseResult.useLocalStock())) {
            Integer localStock = localStockRepository.getAvailableQuantity(medicineCode);

            if (localStock < quantity) {
                return new SellResponse(
                        false,
                        warehouseResult.message(),
                        "Tồn kho cục bộ không đủ để bán."
                );
            }

            // tiếp tục trừ tồn kho cục bộ + tạo hóa đơn
            return new SellResponse(
                    true,
                    warehouseResult.message(),
                    "Giao dịch tiếp tục dựa trên tồn kho cục bộ."
            );
        }

        // xử lý bình thường khi warehouse-service phản hồi
        return new SellResponse(
                true,
                "Đã kiểm tra tồn kho kho tổng thành công.",
                "Giao dịch tiếp tục."
        );
    }
}
