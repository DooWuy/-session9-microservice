package pharmacy.pharmacyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

public class ControllerAdvice {


    @ExceptionHandler(WarehouseUnavailableException.class)
    public ResponseEntity<?> handleWarehouseUnavailable(WarehouseUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "error", "WAREHOUSE_UNAVAILABLE",
                        "message", ex.getMessage()
                ));
    }
}
