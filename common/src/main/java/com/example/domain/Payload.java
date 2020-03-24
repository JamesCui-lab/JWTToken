package com.example.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author john
 * @date 2020/1/12 - 9:15
 */
@Data
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}