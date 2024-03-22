package com.mrgao.demo.scope;

import com.mrgao.demo.scope.refresh.RefreshScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Mr.Gao
 * @date 2024/1/30 12:00
 * @apiNote:
 */
@Getter
@Setter
@Component
@MyScope
public class UserToCustomModel {

    private String username;

    public UserToCustomModel() {
        System.out.println("---------创建User对象" + this); //@2
        this.username = UUID.randomUUID().toString(); //@3
    }

}
