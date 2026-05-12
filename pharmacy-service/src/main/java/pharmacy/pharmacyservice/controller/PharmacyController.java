package pharmacy.pharmacyservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pharmacy.pharmacyservice.dto.OrderEvent;
import pharmacy.pharmacyservice.service.PharmacyProducerService;

import java.time.LocalDateTime;
import java.util.UUID;

public class PharmacyController {

    private final PharmacyProducerService producerService;

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestBody OrderRequest request) {

        OrderEvent event = new OrderEvent(
                UUID.randomUUID().toString(),
                request.medicineId(),
                request.quantity(),
                LocalDateTime.now()
        );


        producerService.sendOrderEvent(event);

        return ResponseEntity.ok("Thanh toán thành công và đã gửi yêu cầu cập nhật kho");
    }
}
record OrderRequest(String medicineId, Integer quantity) {}

