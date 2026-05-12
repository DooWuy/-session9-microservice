package inventory.inventoryservice.service;

import inventory.inventoryservice.dto.OrderEvent;
import inventory.inventoryservice.repository.MedicineRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryConsumerService {

    private final MedicineRepository medicineRepository;
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "medicine-stock-events", groupId = "inventory-group")
    @Transactional
        log.info(" Nhận sự kiện đơn hàng: ID thuốc {}, Số lượng {}",
                event.medicineId(), event.quantity());

        try {

            OrderEntity newOrder = new OrderEntity(event.orderId(), event.medicineId(), event.quantity());
            orderRepository.save(newOrder);

            int updatedRows = medicineRepository.decreaseStock(event.medicineId(), event.quantity());

            if (updatedRows > 0) {
                log.info("Cập nhật kho thành công cho đơn hàng: {}", event.orderId());
            } else {
                log.warn("Cập nhật kho THẤT BẠI cho đơn hàng: {} (Có thể do hết hàng)", event.orderId());
            }
        } catch (Exception e) {
            log.error("Lỗi khi xử lý sự kiện Kafka: {}", e.getMessage());
            throw e;
        }
    }
}
