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
    private String title;
    private String noRek;
    private String bank;
    private String periodTrf;
    private BigDecimal totalPendapatan;
    private BigDecimal totalLebihBayar;
    private BigDecimal totalPembayaranSaatIni;
    private List<SettlementItemModel> items;
}
