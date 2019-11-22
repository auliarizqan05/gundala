package co.id.gooddoctor.gundala.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties
public class RoleDto {

    long id;
    String name;
    String description;
    boolean status;

    Set<ModuleDto> modules;

}
