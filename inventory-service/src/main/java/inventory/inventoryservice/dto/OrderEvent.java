package inventory.inventoryservice.dto;

import java.time.LocalDateTime;

public record OrderEvent(

        String orderId,
        String medicineId,
        Integer quantity,
        LocalDateTime timestamp
) {
}
