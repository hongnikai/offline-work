package com.lc.learn.io.trans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lc 2/7/21 11:07 AM
 */
@Data
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 123456L;

    private transient int age;

    private String name;




}
