package com.mrgao.demo.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Gao
 * @date 2024/3/20 11:31
 * @apiNote:
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList();
        List<Integer> integerList = list.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(integerList);

        Teacher teacher = new Teacher();
        teacher.setUserName("Mr.Gao");
        teacher.setAge(27);
        teacher.setHobby("Java编程");
        System.out.println(teacher.hashCode());
        System.out.println(JSONObject.toJSONString(teacher));

        // 克隆
        Teacher cloneTeacher = teacher.clone();
        cloneTeacher.setHobby("克隆后的Value");
        System.out.println(cloneTeacher.hashCode());
        System.out.println(JSONObject.toJSONString(cloneTeacher));
        System.out.println(JSONObject.toJSONString(teacher));


        String s = StringUtils.leftPad("999", 13, "0");
        System.out.println(s);

        String s1 = System.currentTimeMillis() + "";
        System.out.println(System.currentTimeMillis());
        System.out.println(s1.length());
    }


    @Data
    public static class Person {
        private String userName;
        private Integer age;
    }

    @Data
    public static class Teacher extends Person implements Cloneable {
        private String hobby;

        @Override
        public Teacher clone() {
            try {
                // TODO: copy mutable state here, so the clone can't change the internals of the original
                return (Teacher) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
