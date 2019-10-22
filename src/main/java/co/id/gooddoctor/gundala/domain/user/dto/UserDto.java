package co.id.gooddoctor.gundala.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UserDto {

    String fullName;
    String uname;
    String pwd;
    String email;


}
