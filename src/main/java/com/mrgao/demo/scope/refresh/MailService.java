package com.mrgao.demo.scope.refresh;

import com.mrgao.demo.scope.MainConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Gao
 * @date 2024/1/30 14:02
 * @apiNote:
 */
@Service
public class MailService {

    @Autowired
    private MainConfig1 mainConfig;

    @Override
    public String toString() {
        return "MailService{" + "mainConfig=" + mainConfig + '}';
    }
}
