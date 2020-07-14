package com.bp.learnblog.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LowestCommonMutiple {
    public static int getResult(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (m < n) {
            int temp = m;
            m = n;
            n= temp;
        }

        // 求最大公约数
        int z = 0;
        while (n != 0) {
            z = m % n;
            m = n;
            n = z;
        }
        return m;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入两个数字：");
//        while (scanner.hasNext()) {
//            int m = scanner.nextInt();
//            int n = scanner.nextInt();
//            System.out.println(m * n / getResult(m, n));
//            break;
//        }
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String strA = scanner.nextLine();
            String strB = scanner.nextLine();
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (strA.length() > 1024 || strB.length() > 1024) {
                System.out.println(0);
            } else {
                strA = strA.replaceAll("\\s*\\t*", "");
                strB = strB.replaceAll("\\s*\\t*", "");
                System.out.println("strA: " + strA);
                if (strA.length() < strB.length()) {
                    System.out.println(0);
                } else {
                    int equalsNum = 0;
                    int strALength = strA.length();
                    int strBLength = strB.length();
                    for (int i = 0; i < strALength - 1; i++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(strA.charAt(i));
                        for (int j = 1; j < strBLength && (i + j) < strALength; j ++) {
                            sb.append(strA.charAt(i + j));
                        }
                        if (sb.toString().equals(strB)) {

                            equalsNum++;
                        }
                    }
                    System.out.println(equalsNum);
                }
            }

            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}
