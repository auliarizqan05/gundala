package co.id.gooddoctor.gundala.domain.settlement.constant;

import org.apache.commons.lang3.StringUtils;

public class ColumnNameConstant {

    public static String VENDOR_ID = "Vendor ID";

    public static String ORDER_ID = "Order ID";

    public static String VENDOR_NAME = "Vendor name";

    public static String STORE_ID = "Store ID";

    public static String STORE_NAME = "Store name";

    public static String USER_ID = "User ID";

    public static String PAYMENT_STATUS = "Payment status";

    public static String ORDER_CREATED_DATE = "Order created dt";

    public static String ORDER_DELIVERED_DATE = "Order delivered dt";

    public static String PAYMENT_DATE = "Payment dt";

    public static String ORDER_SUBTOTAL = "Order subtotal";

    public static boolean COLUMN_NAME_INDEX(String columnName) {
        return StringUtils.equalsAnyIgnoreCase(columnName, new CharSequence[]{ORDER_ID, VENDOR_ID, VENDOR_NAME, STORE_ID, STORE_NAME, USER_ID, PAYMENT_STATUS, ORDER_CREATED_DATE, ORDER_DELIVERED_DATE, PAYMENT_DATE, ORDER_SUBTOTAL});
    }
}
