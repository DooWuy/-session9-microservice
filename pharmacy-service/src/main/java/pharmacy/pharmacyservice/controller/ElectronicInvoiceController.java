package pharmacy.pharmacyservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pharmacy.pharmacyservice.dto.InvoiceRequest;
import pharmacy.pharmacyservice.dto.InvoiceResponse;
import pharmacy.pharmacyservice.service.ElectronicInvoiceService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/e-invoices")
public class ElectronicInvoiceController {

    private final ElectronicInvoiceService electronicInvoiceService;

    @PostMapping
    public InvoiceResponse createInvoice(@RequestBody InvoiceRequest request) {
        return electronicInvoiceService.createElectronicInvoice(request);
    }
}
