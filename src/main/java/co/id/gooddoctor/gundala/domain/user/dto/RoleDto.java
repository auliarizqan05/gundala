package co.id.gooddoctor.gundala.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class RoleDto {

    String name;
    String desc;
    String status;


}
