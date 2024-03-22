package com.mrgao.demo;

import com.mrgao.demo.config.DbConfig;
import com.mrgao.demo.scope.MainConfig;
import com.mrgao.demo.scope.ThreadScope;
import com.mrgao.demo.scope.UserToCustomModel;
import com.mrgao.demo.scope.refresh.MailService;
import com.mrgao.demo.scope.refresh.MainConfig1;
import com.mrgao.demo.scope.refresh.RefreshScopeBean;
import com.mrgao.demo.scope.refresh.utils.RefreshConfigUtil;
import com.mrgao.demo.service.BeanScopeModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Gao
 * @date 2024/1/29 13:59
 * @apiNote:学习Bean的生命周期
 */
@SpringBootTest(classes = XbqxDemoApplication.class)
public class SpringScopeApplicationTest {

    private ClassPathXmlApplicationContext applicationContext;

    @PostConstruct
    public void before() {
        System.out.println("spring容器准备启动.....");
        this.applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("spring容器准备完毕!");
    }

    /**
     * 案例描述：单例Bean
     */
    @Test
    public void testSingleBean() {
        System.out.println("---------单例bean，每次获取的bean实例都一样---------");
        System.out.println(this.applicationContext.getBean("beanScopeSingleModel", BeanScopeModel.class));
        System.out.println(this.applicationContext.getBean("beanScopeSingleModel", BeanScopeModel.class));
        System.out.println(this.applicationContext.getBean("beanScopeSingleModel", BeanScopeModel.class));
    }

    /**
     * 案例描述：多例Bean
     * 注意: 多例bean每次获取的时候都会重新创建，如果这个bean比较复杂，创建时间比较长，会影响系统的性能，这个地方需要注意
     */
    @Test
    public void testPrototypeBean() {
        System.out.println("---------多例bean，每次获取的bean实例都不一样，每次获取对象是都是创建---------");
        System.out.println(this.applicationContext.getBean("beanScopePrototypeModel", BeanScopeModel.class));
        System.out.println(this.applicationContext.getBean("beanScopePrototypeModel", BeanScopeModel.class));
        System.out.println(this.applicationContext.getBean("beanScopePrototypeModel", BeanScopeModel.class));
    }

    /**
     * 案例描述：自定义作用域
     */
    @Test
    public void testCustomScope() throws Exception {
        for (int i = 0; i < 2; i++) { //@2
            new Thread(() -> {
                System.out.println(Thread.currentThread() + "," + applicationContext.getBean("beanScopeCustomModel"));
                System.out.println(Thread.currentThread() + "," + applicationContext.getBean("beanScopeCustomModel"));
            }).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Autowired
    private DbConfig dbConfig;

    /**
     * 案例描述：测试PropertySource注解
     */
    @Test
    public void testPropertySource() {
        System.out.println("-----数据库连接信息-----");
        System.out.println("链接地址:" + dbConfig.getUrl());
        System.out.println("链接姓名:" + dbConfig.getUsername());
        System.out.println("链接密码:" + dbConfig.getPassword());
    }


    /**
     * 案例描述：以注解的形式注入自定义Scope
     */
    @Test
    public void testCustomScopeAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注入作用域到容器中
        context.getBeanFactory().registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());

        context.register(MainConfig.class);

        context.refresh();
        System.out.println("从容器中获取User对象");
        UserToCustomModel user = context.getBean(UserToCustomModel.class); //@2
        System.out.println("user对象的class为：" + user.getClass()); //@3
        System.out.println("多次调用user的getUsername感受一下效果\n");
        for (int i = 1; i <= 3; i++) {
            System.out.println(String.format("********\n第%d次开始调用getUsername",
                    i));
            System.out.println(user.getUsername());
            System.out.println(String.format("第%d次调用getUsername结束\n********\n",
                    i));
        }
    }


    /**
     * 案例描述：自动刷新注解@RefreshScope
     */
    @Test
    public void testAutoRefreshScope() throws Exception {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext();
        context.getBeanFactory().registerScope(RefreshScopeBean.REFRESH_SCOPE,
                RefreshScopeBean.getInstance());
        context.register(MainConfig1.class);

        //刷新mail的配置到Environment
        RefreshConfigUtil.refreshMailPropertySource(context);
        context.refresh();
        MailService mailService = context.getBean(MailService.class);
        System.out.println("配置未更新的情况下,输出3次");
        for (int i = 0; i < 3; i++) { //@1
            System.out.println(mailService);
            TimeUnit.MILLISECONDS.sleep(200);
        }
        System.out.println("模拟3次更新配置效果");
        for (int i = 0; i < 3; i++) { //@2
            RefreshConfigUtil.updateDbConfig(context); //@3
            System.out.println(mailService);
            TimeUnit.MILLISECONDS.sleep(200);
        }

    }
}
