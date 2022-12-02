package co.id.gundala.domain.settlement.model;

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
    Date lastStatusDate;
    String paymentStatus;
    String storeName;
    String vendorName;
    BigDecimal orderSubtotal;
    double commission;
    BigDecimal settlementToMerchant;
}
