package co.id.gooddoctor.gundala.domain.user.message;

import org.apache.commons.lang3.StringUtils;

public class LoginErrorMessage {

    public static String BAD_CREDENTIALS = "Bad credentials";
    public static String MISSING_CREDENTIALS = "Missing credentials";
    public static String EMPTY_PASSWORD = "Empty Password";
    public static String EMPTY_USERNAME = "Empty Username";

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String equalsMessage(String messageFromOut) {

        if (StringUtils.equalsAnyIgnoreCase(BAD_CREDENTIALS, messageFromOut)) {
            return "Password incorrect !!";
        } else if (StringUtils.equalsAnyIgnoreCase(MISSING_CREDENTIALS, messageFromOut)) {
            return "User not exist";
        } else if (StringUtils.equalsAnyIgnoreCase(EMPTY_PASSWORD, messageFromOut)) {
            return "Password is blank !!";
        } else if (StringUtils.equalsAnyIgnoreCase(EMPTY_USERNAME, messageFromOut)) {
            return "Username is blank !!";
        } else {
            return messageFromOut;
        }
    }

}
