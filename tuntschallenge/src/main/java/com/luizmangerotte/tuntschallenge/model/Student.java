package com.luizmangerotte.tuntschallenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {

    private static final int CLASS = 60;
    private static final int PERCENTAGE_MISS = 25;


    private Long id;

    private String name;

    private Double miss;

    private Double testScore1;

    private Double testScore2;

    private Double testScore3;
    private String situation;
    private Double noteApproval;

    public Student(Long id, String name, Double miss, Double testScore1, Double testScore2, Double testScore3) {
        this.id = id;
        this.name = name;
        this.miss = miss;
        this.testScore1 = testScore1;
        this.testScore2 = testScore2;
        this.testScore3 = testScore3;
    }

    public Double average() {
        return (testScore1 + testScore2 + testScore3) / 3;
    }

    public boolean failedForMiss() {
        return miss > (CLASS * PERCENTAGE_MISS / 100);
    }

}
