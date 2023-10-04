package com.hfd.blackjack;

public class more {
    public int score(int[] arr){
        int sum = 0;
        for(int i=0; i<arr.length;i++){
           sum = sum + arr[i];
        }
        return sum;
    }
}
