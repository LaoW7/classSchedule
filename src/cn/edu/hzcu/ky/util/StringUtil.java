package cn.edu.hzcu.ky.util;
/*
 * 字符串工具类
 */
public class StringUtil {
    public static boolean isEmpty(String str){//判断字符串是否为空
        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

}
