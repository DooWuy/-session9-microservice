package pharmacy.pharmacyservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import pharmacy.pharmacyservice.dto.OrderEvent;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacyProducerService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private static final String TOPIC = "medicine-stock-events";

    public void sendOrderEvent(OrderEvent event) {

        String key = event.medicineId();

        CompletableFuture<SendResult<String, OrderEvent>> future =
                kafkaTemplate.send(TOPIC, key, event);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Gửi sự kiện thành công: {} | Partition: {} | Offset: {}",
                        event.orderId(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Gửi sự kiện thất bại cho Order: {} - Lỗi: {}",
                        event.orderId(), ex.getMessage());
            }
        });
    }
}
