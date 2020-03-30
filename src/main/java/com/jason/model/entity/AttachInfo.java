package com.jason.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachInfo {
    private String url;
    private String key;
    private String putTime ;
    private double fileSize ;
}
