package com.mrgao.demo.scope.refresh.utils;

import com.mrgao.demo.scope.refresh.RefreshScopeBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

/**
 * @author Mr.Gao
 * @date 2024/1/30 13:42
 * @apiNote:
 */
public class RefreshConfigUtil {

    /**
     * 模拟改变数据库中都配置信息
     */
    public static void updateDbConfig(AbstractApplicationContext context) {
        //更新context中的mailPropertySource配置信息
        refreshMailPropertySource(context);

        //清空BeanRefreshScope中所有bean的缓存
        RefreshScopeBean.getInstance().clean();
    }

    /**
     * 刷新属性
     *
     * @param context
     */
    public static void refreshMailPropertySource(AbstractApplicationContext context) {
        Map<String, Object> mailInfoFromDb = DbUtil.getMailInfoFromDb();
        //将其丢在MapPropertySource中（MapPropertySource类是spring提供的一个类，是PropertySource的子类）
        MapPropertySource mailPropertySource = new MapPropertySource("mail", mailInfoFromDb);
        context.getEnvironment().getPropertySources().addFirst(mailPropertySource);
    }
}
