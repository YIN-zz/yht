package com.ygt.pojo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BeiAn {
    //备案证号
    private String recordid;
    //货物名称
    private String bname;
    //货物重量
    private double bcwerght;
    //货物数量
    private int bccount;
    //库内货物总id
    private int chid;

}
