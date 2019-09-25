package co.id.gooddoctor.gundala.domain.settlement.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class SettlementItemModel {

    BigDecimal orderId;
    BigDecimal storeId;
    BigDecimal userId;
    BigDecimal vendorId;
    Date orderCreatedDate;
    Date paymentDate;
    String paymentStatus;
    String storeName;
    String vendorName;
    BigDecimal orderSubtotal;
}
