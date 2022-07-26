package com.rdi.menu.enums;

public enum Type {

    PRODUCT(1),
    CHOICE(2),
    VALUE_MEAL(3);

    private int code;

    private Type(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Type valueOf(int code){
        for(Type value : Type.values()){
            if (code == value.getCode()){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid type code");
    }
}
