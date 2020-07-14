package com.bp.learnblog.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class KeybordInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.length() <= 0) {
            System.out.println();
        } else {
            // 英文模式下
            if (String.valueOf(input.charAt(0)).equals("#")) {
                //
                Map<String, List<String>> map = new HashMap<>();
            } else {
                // 数字模式
                input = input.replaceAll("\\#{0,}\\,{0,}\\.{0,}\\/{0,}\\ {0,}", "");
                System.out.println(input);
            }
        }
    }
}
