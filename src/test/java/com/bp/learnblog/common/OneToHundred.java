package com.bp.learnblog.common;

import java.util.*;

public class OneToHundred {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        if (M < 1 || M > 100) {
            System.out.println("ERROR!");
        } else {
            List<Integer> personList = new ArrayList<>(100);
            for (int i = 1; i <= 100; i++) {
                personList.add(i);
            }

            int target = 0;
            StringBuilder sb = new StringBuilder();
            while (!personList.isEmpty()) {
                target = (target + M) % personList.size();
                if (target != 0) {
                    personList.remove(target - 1);
                    target--;
                } else {
                    sb.append(personList.get(personList.size() - 1)).append(",");
                    personList.remove(personList.size() - 1);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }
}
