package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Policeinfo{
    //主键id
    private Integer policeid;
    //警员所在派出所
    private String policeaddress;
    //外键userid
    private Integer userid;
}
