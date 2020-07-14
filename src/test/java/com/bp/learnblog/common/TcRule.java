package com.bp.learnblog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TcRule {
    // 0 表示无限期， 1表示指定时间范围
    private int timeType;

    // timeType=1时，有效
    private String startDate;

    // timeType=1时，有效
    private String endDate;

    // timeType=1时，周日到周六用 0-6表示，存储格式：1|2
    private String week;

    // timeType=1时，有效
    private String startTime;

    // timeType=1时，有效
    private String endTime;

    // 0 表示所有员工，1表示指定的员工
    private int personType;

    // personType=1时，员工ID存储格式 1|2|3
    private String personList;

    /**
     * 规则判断去重
     *
     * @param tcRule
     * @param listTcRule
     * @return
     */
    public static List<TcRule> checkTcRule(TcRule tcRule, List<TcRule> listTcRule) {
        // TODO 有个疑问，会不会出现这样的情况：如果timeType 和 personType 都是1， 但那些子项都没有设置
        // 判断是否已有规则
        if (listTcRule.size() > 0) {
            // 定义冲突规则列表
            List<TcRule> conflictRules = new ArrayList<>();
            for (TcRule existsRule : listTcRule) {
                // 判断员工类型
                if (existsRule.personType == 0 || tcRule.personType == 0) {
                    // 员工类型为0的情况
                    // 判断时期类型及是否在同一有效期内
                    if (checkTcRuleIfInSameTime(existsRule, tcRule)) {
                        conflictRules.add(existsRule);
                    }

                } else if (existsRule.personType == 1) {
                    // 员工类型为1的情况
                    // 判断当前员工是否在列表中
                    if (tcRule.personList != null && existsRule.personList != null &&
                            compareString(tcRule.personList, existsRule.personList, "\\|")) {
                        // 判断时期类型及是否在同一有效期内
                        if (checkTcRuleIfInSameTime(existsRule, tcRule)) {
                            conflictRules.add(existsRule);
                        }
                    }
                }
            }

            if (conflictRules.size() > 0) {
                return conflictRules;
            }
        }

        return null;
    }

    /**
     * 判断两个规则的时刻是否在同一时间内
     *
     * @param existsRule 规则列表中的规则
     * @param tcRule 新创建的规则
     * @return
     */
    private static boolean checkTcRuleIfInSameTime(TcRule existsRule, TcRule tcRule) {
        // 判断有效期类型
        if (existsRule.timeType == 0 || tcRule.timeType == 0) {
            // 任意时间则返回true
            return true;
        } else if (existsRule.timeType == 1){
            // 首先判断日期
            // TODO 如果规则中的日期为空是否就是表示所有日期(以此类推星期还有时间段)
            // TODO 若是，那么在每个判断条件中加上 短路或 的条件
            if (!StringUtils.isEmpty(existsRule.endDate) && !StringUtils.isEmpty(tcRule.startDate) &&
                    !StringUtils.isEmpty(tcRule.endDate) && (existsRule.endDate.compareTo(tcRule.startDate) >= 0) &&
                    (existsRule.endDate.compareTo(tcRule.endDate) <= 0)) {
                // 判断星期
                if (!StringUtils.isEmpty(tcRule.week) && !StringUtils.isEmpty(existsRule.week) &&
                        compareString(tcRule.week, existsRule.week, "\\|")) {
                    // 判断时间段
                    if (!StringUtils.isEmpty(existsRule.endTime) && !StringUtils.isEmpty(tcRule.startTime) &&
                            !StringUtils.isEmpty(tcRule.endTime) &&
                            (existsRule.endTime.compareTo(tcRule.startTime) >= 0) &&
                            (existsRule.endTime.compareTo(tcRule.endTime) <= 0)) {
                        // 若都符合，即在同一时刻内则返回true
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 校验两个字符串的交集（如星期，人员列表等）
     *
     * @param strA 字符串A
     * @param strB 字符串B
     * @param separator 间隔符
     * @return
     */
    private static boolean compareString(String strA, String strB, String separator) {
        // 去除间隔符
        strA = strA.replaceAll(separator, "");
        strB = strB.replaceAll(separator, "");
        // 两个字符串分别放入set并求交集
        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();
        for (char charA : strA.toCharArray()) {
            setA.add(String.valueOf(charA));
        }
        for (char charB : strB.toCharArray()) {
            setB.add(String.valueOf(charB));
        }
        return setA.removeAll(setB);
    }

    public static void main(String[] args) {
        System.out.println(compareString("5|8", "0|1|6", "\\|"));
        System.out.println("23:00".compareTo("22:01"));

        TcRule tcRule01 = new TcRule();
        tcRule01.setTimeType(0);
        tcRule01.setPersonType(0);

        TcRule tcRule02 = new TcRule();
        tcRule02.setTimeType(0);
        tcRule02.setPersonType(1);
        tcRule02.setPersonList("1|2|3");

        TcRule tcRule03 = new TcRule();
        tcRule03.setTimeType(1);
        tcRule03.setPersonType(0);
        tcRule03.setPersonList("1|2|3");

        TcRule tcRule04 = new TcRule();
        tcRule04.setTimeType(1);
        tcRule04.setPersonType(1);
        tcRule04.setPersonList("1|2|3");
        List<TcRule> tcRuleList = new ArrayList<>();
//        tcRuleList.add(tcRule01);
        tcRuleList.add(tcRule02);
//        tcRuleList.add(tcRule03);
//        tcRuleList.add(tcRule04);

        TcRule tcRule = new TcRule();
        tcRule.setPersonType(1);
        tcRule.setTimeType(1);

        System.out.println(checkTcRule(tcRule, tcRuleList));
    }
}
