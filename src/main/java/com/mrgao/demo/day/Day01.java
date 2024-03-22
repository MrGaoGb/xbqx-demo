package com.mrgao.demo.day;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.Gao
 * @date 2024/1/9 10:28
 * @apiNote:
 * 输入描述：
 * 第一行输入整数n。
 * 第二行输入n个整数。
 *
 * 输出描述：
 * 输出排序后的n个整数。
 *
 * 输入样例：
 * 4
 * 2 3 1 23
 *
 * 输出样例：
 * 3 1 23 2
 */
public class Day01 {
    public static void main(String[] args) {
        System.out.println("请输入个数...");
        Scanner sc = new Scanner(System.in);
        // 获取到输入的数组的个数
        int inputNum = sc.nextInt();
        // 偶数
        List<Integer> evenList = new ArrayList<>();
        // 奇数
        List<Integer> oddList = new ArrayList<>();
        for (int i = 0; i < inputNum; i++) {
            System.out.println("添加第" + (i + 1) + "个数: ");
            sc = new Scanner(System.in);
            int inputNumVal = sc.nextInt();
            if (inputNumVal % 2 == 0) {
                // 偶数
                evenList.add(inputNumVal);
            } else {
                // 奇数
                oddList.add(inputNumVal);
            }
        }
        // 数组合并输出
        List<Integer> mergeList = Stream.of(oddList, evenList).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(mergeList);
    }
}
