package co.id.gooddoctor.gundala.domain.settlement.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Settlement {

    long id;
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
