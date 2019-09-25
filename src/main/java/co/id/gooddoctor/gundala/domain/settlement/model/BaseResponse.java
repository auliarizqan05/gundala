package co.id.gooddoctor.gundala.domain.settlement.model;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class BaseResponse {

    int code;
    int status;
    String message;
    Object data;

    public BaseResponse failedProcess(){
        this.code = 1;
        this.message = "Failed";
        this.status = 500;

        return this;
    }

    public BaseResponse failedProcess(Object data) {
        this.code = 1;
        this.message = "Failed";
        this.status = 500;
        this.data = data;

        return this;
    }

    public BaseResponse successProcess(Object data){
        this.code = 0;
        this.message = "Success";
        this.status = 200;
        this.data = data;

        return this;
    }

    public BaseResponse successProcess(){
        this.code = 0;
        this.message = "Success";
        this.status = 200;

        return this;
    }


}
