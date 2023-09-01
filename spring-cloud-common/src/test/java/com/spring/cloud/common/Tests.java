package com.spring.cloud.common;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Tests {

    @Test
    public void test() {
        LinkedList<Map<String, Integer>> mapLinkedList = new LinkedList<>();

        Map<String, Integer> map = new HashMap<>();
        map.put("age", 11);
        mapLinkedList.add(map);

        map = new HashMap<>();
        map.put("age", 15);
        mapLinkedList.add(map);

        map = new HashMap<>();
        map.put("age", 5);
        mapLinkedList.add(map);

        map = new HashMap<>();
        map.put("age", 7);
        mapLinkedList.add(map);

        map = new HashMap<>();
        map.put("age", 9);
        mapLinkedList.add(map);

        map = new HashMap<>();
        map.put("age", 20);
        mapLinkedList.add(map);

        mapLinkedList.sort(Comparator.comparing(o -> o.get("age")));

        System.out.println(mapLinkedList);
    }


    @Test
    public void test1() {

    }

    /**
     * 给出一个数组，要求返回不在数组中出现过的最小正整数
     *
     * @param A
     * @return
     */
    public int solution(int[] A) {
        int temp;

        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 0; j < A.length - i - 1; j++) {
                if (A[j + 1] < A[j]) {
                    temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }

        int res = 1;

        if (A[0] > 1 || A[A.length - 1] < 1) {
            res = 1;
        } else {
            for (int k = 0; k < A.length - 1; k++) {
                if (A[k] < A[k + 1] - 1) {
                    res = A[k] + 1;
                    break;
                } else {
                    res = A[A.length - 1] + 1;
                }
            }

        }

        if (res < 1) {
            res = 1;
        }

        return res;
    }

    public int solution(String S, int[] C) {
        // write your code in Java SE 8
        int j = 0;
        int acc = 0;
        int length = S.length();

        for (int i = 0; i < C.length; ++i) {
            while ((i + j + 1 < length) && (S.indexOf(i) == S.indexOf(i + j + 1))) {
                j = j + 1;
            }

            if (j > 0) {
                acc += cost(C, i, i + j);
                i = i + j;
            } else {
                i = i + 1;
            }

            j = 0;
        }

        return acc;
    }

    public int cost(int[] C, int i, int j) {

        int biggest = C[i];
        int acc = 0;

        for (int m = i; m <= j; m++) {
            acc += C[m];
            if (C[m] > biggest) {
                biggest = C[m];
            }
        }

        return acc - biggest;
    }

}
