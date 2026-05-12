package inventory.inventoryservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {


    @Modifying
    @Transactional
    @Query("UPDATE Medicine m SET m.stock = m.stock - :quantity WHERE m.id = :medicineId AND m.stock >= :quantity")
    int decreaseStock(@Param("medicineId") String medicineId, @Param("quantity") Integer quantity);
}
