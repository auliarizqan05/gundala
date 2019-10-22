package co.id.gooddoctor.gundala.domain.user.dto;

import co.id.gooddoctor.gundala.domain.user.entity.Module;
import co.id.gooddoctor.gundala.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UserModuleDto {

    String description;
    String status;
    User users;
    Module modules;

}
