package co.id.gooddoctor.gundala.model;

import lombok.Data;

import java.util.List;

@Data
public class GenSettlementDto {
    private String vendorName;
    private String title;
    private String noRek;
    private String bank;
    private String periodTrf;
    private String totalIncome;
    private String totalLebihBayar;
    private String totalPay;
    private List<GenSettlementItemDto> items;
}
