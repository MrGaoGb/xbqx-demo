package com.mrgao.demo.scope.refresh.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Mr.Gao
 * @date 2024/1/30 13:45
 * @apiNote:
 */
public class DbUtil {
    /**
     * 模拟从db中获取邮件配置信息
     *
     * @return
     */
    public static Map<String, Object> getMailInfoFromDb() {
        Map<String, Object> result = new HashMap<>();
        result.put("mail.username", UUID.randomUUID().toString());
        return result;
    }

}
