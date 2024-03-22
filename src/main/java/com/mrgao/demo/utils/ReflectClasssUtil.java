package com.mrgao.demo.utils;

import com.mrgao.demo.service.TeacherServcieImpl;
import com.mrgao.demo.service.UserServiceImpl;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author Mr.Gao
 * @date 2023/12/29 10:52
 * @apiNote:
 */
public class ReflectClasssUtil {

    //public static void genClassFile() throws Exception {
    //    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    //
    //    byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", TeacherServcieImpl.class.getInterfaces());
    //
    //    FileOutputStream out = new FileOutputStream("D:\\workspace\\gaogba\\xbqx-demo\\" + "$Proxy0.class");
    //
    //    out.write(classFile);
    //}
    //
    //
    //public static void main(String[] args) throws Exception {
    //    genClassFile();
    //}

}
