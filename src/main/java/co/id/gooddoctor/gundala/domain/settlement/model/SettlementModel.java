package co.id.gooddoctor.gundala.domain.settlement.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class SettlementModel {
    private String vendorName;
    private long vendorId;
    private String title;
    private String noRek;
    private String bank;
    private String periodTrf;
    private String companyName;
    private BigDecimal totalOrderSubtotal;
    private BigDecimal totalCommission;
    private BigDecimal total;
    private List<SettlementItemModel> items;
}
