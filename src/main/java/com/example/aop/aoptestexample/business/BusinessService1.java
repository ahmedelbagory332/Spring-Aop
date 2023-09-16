package com.example.aop.aoptestexample.business;


import java.util.Arrays;

import com.example.aop.aoptestexample.data.DataService1;
import org.springframework.stereotype.Service;


@Service
public class BusinessService1 {

    private DataService1 dataService1;

    public BusinessService1(DataService1 dataService1) {
        this.dataService1 = dataService1;
    }

    public int calculateMax(String name) {
        int[] data = dataService1.retrieveData();
        //throw new RuntimeException("Something Went Wrong!");
        return Arrays.stream(data).max().orElse(0);
    }
}