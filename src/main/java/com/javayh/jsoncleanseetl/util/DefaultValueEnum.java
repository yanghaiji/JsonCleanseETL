package com.javayh.jsoncleanseetl.util;

import java.util.Map;

/**
 * <p>
 * default value 当jsonpath获取不到值得时候使用，防止数据节点丢失或者其他得异常
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2024-03-04
 */
public enum  DefaultValueEnum {

    MAP("java.util.HashMap"),

    LIST("java.util.List"),

    STRING("java.lang.String"),

    NULL("null")



    ;

    private String value;

    DefaultValueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return super.toString();
    }

    public Object newInstance(DefaultValueEnum defaultValueEnum) throws Exception {
        String value = defaultValueEnum.getValue();
        return Class.forName(value).newInstance();
    }

}
