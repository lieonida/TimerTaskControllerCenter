package cn.com.voc.ControlCenter.TimerTask.util;

import java.util.UUID;

/**
 * @description: 字符串操作工具类
 * @author: hn
 * @date: 2019/4/4
 */
public class StringUtil {

    /**
     * @description 获取UUID
     * @author hn
     * @date 20:42 2019/4/4
     * @Param
     * @return String
     */
    public static  String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }

}
