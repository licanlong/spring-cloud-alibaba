package com.licanlong.bean;

import lombok.*;

/**
 * @author licl
 * @date 2019/10/15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String id;
    private String name;
    private String gender;
}
