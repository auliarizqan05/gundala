package co.id.gooddoctor.gundala.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties
public class UserDto {

    long id;
    String fullName;
    String uname;
    String email;

    Set<RoleDto> roles;


}
