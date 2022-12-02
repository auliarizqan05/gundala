package co.id.gundala.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ModuleDto {

    long id;
    String name;
    String url;
    String description;
    boolean status;


}
