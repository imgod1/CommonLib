

package com.imgod1.commlib.encrypt.base;

public class TextUtils {
    public static boolean isEmpty(Object str){
        if (str instanceof String){
            return ((String)str).isEmpty();
        }else {
            return str==null;
        }
    }
}
