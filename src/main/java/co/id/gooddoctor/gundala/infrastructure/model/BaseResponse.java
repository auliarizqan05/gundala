package co.id.gooddoctor.gundala.infrastructure.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class BaseResponse implements Serializable {

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

    public BaseResponse failedProcess(int status, String message, Object data) {
        this.code = 1;
        this.message = message;
        this.status = status;
        this.data = data;

        return this;
    }

    public BaseResponse failedProcess(String message, Object data) {
        this.code = 1;
        this.message = message;
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
