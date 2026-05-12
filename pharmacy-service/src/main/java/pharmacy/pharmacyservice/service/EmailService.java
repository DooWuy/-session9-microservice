package pharmacy.pharmacyservice.service;

public class EmailService {

    private final JavaMailSender mailSender;

    public void sendInvoiceEmail(String orderId, String medicineId, Integer quantity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("khach-hang@example.com"); // Trong thực tế lấy từ thông tin khách hàng
        message.setSubject("Hóa đơn điện tử - Đơn hàng " + orderId);
        message.setText(String.format("""
            Chào bạn, 
            Đơn hàng %s của bạn đã thanh toán thành công.
            Chi tiết: %s x %d sản phẩm.
            Cảm ơn bạn đã tin tưởng hiệu thuốc!
            """, orderId, medicineId, quantity));

        try {
            mailSender.send(message);
            log.info(">>>> Hóa đơn cho đơn hàng [{}] đã được gửi tới khách hàng", orderId);
        } catch (Exception e) {
            log.error("Lỗi khi gửi email cho đơn hàng {}: {}", orderId, e.getMessage());
        }
    }
}
