package com.code.zcy;

import java.util.Arrays;

/**
 * @author lizhengqiang
 * @create 2024-09-27 22:03
 **/
public class Video_002_Experiment {
    public static void main(String[] args) {
        System.out.println("一个社会的基尼系数是一个在0-1之间的小数");
        System.out.println("基尼系数为0代表所有人的财富完全一样");
        System.out.println("基尼系数为1代表有一个人掌握了全世界的财富");
        System.out.println("基尼系数越小，代表社会财富分配越均衡；越大则代表财富分配越不均匀");
        int n = 100;
        int t = 1;
        System.out.println("人数：" + n);
        System.out.println("轮数：" + t);
        experiment(n, t);
    }

    public static void experiment(int n, int t) {
        //初始化财富
        double[] wealth = new double[n];
        Arrays.fill(wealth, 100);

        //初始化是否有钱
        boolean[] hasMoney = new boolean[n];

        //循环轮次
        for (int i = 0; i < t; i++) {
            //初始化是否有钱
            Arrays.fill(hasMoney, false);
            for (int j = 0; j < n; j++) {
                if (wealth[j] > 0) {
                    hasMoney[j] = true;
                }
            }

            //进行给钱
            for (int j = 0; j < n; j++) {
                if (hasMoney[j]) {
                    int other = j;
                    while (other == j) {
                        other = (int) (Math.random() * n);
                    }
                    wealth[other]++;
                    wealth[j]--;
                }
            }
        }

        //排序输出
        Arrays.sort(wealth);
        for (double v : wealth) {
            System.out.print(v + " ");
        }

    }
}
