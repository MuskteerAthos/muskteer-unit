/**
 * FileName:   CompareBySequence.java
 * @Description comparator
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.unit;

import java.util.Comparator;

public class CompareBySequence implements Comparator<Pair> {

    @Override
    public int compare(Pair o1, Pair o2) {
        long seq1 = (long) o1.getV();
        long seq2 = (long) o2.getV();
        if(seq1 > seq2){
            return 1;
        }
        return 0;
    }
}
