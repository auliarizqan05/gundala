package co.id.gooddoctor.gundala.domain.settlement.model;

import lombok.Data;

@Data
public class GenerateSettlementItemDto {
    private String orderId;
    private String storeId;
    private String userId;
    private String vendorId;
    private String orderCreated;
    private String payDate;
    private String payStatus;
    private String storeName;
    private String vendorName;
    private String orderSubtotal;
}
