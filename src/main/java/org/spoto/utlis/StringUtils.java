package org.spoto.utlis;

public class StringUtils {
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String  str){
        return !isEmpty(str);
    }

    public static boolean isAllEmpty(String...args){
        for (String str : args) {
            if(isEmpty(str)){
                return false;
            }
        }
        return true;
    }

}
