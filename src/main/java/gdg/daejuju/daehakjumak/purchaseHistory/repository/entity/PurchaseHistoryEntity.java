package gdg.daejuju.daehakjumak.purchaseHistory.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jumak_purchase_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PurchaseHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String menu;
    private int quantity;
    private int price;
    private Long userId;
}
