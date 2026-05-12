package pharmacy.pharmacyservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pharmacy.pharmacyservice.dto.OrderEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "medicine-stock-events", groupId = "notification-group")
    public void handleInvoiceEvent(OrderEvent event) {
        log.info("Nhận sự kiện xuất hóa đơn cho đơn hàng: {}", event.orderId());

        // Gọi dịch vụ gửi email chuyên biệt
        emailService.sendInvoiceEmail(
                event.orderId(),
                event.medicineId(),
                event.quantity()
        );
    }
}
