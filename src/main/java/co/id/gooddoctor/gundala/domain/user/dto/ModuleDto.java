package co.id.gooddoctor.gundala.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ModuleDto {

    String name;
    String url;
    String desc;
    String status;


}
