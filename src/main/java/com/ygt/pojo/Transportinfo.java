package com.ygt.pojo;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transportinfo{
    //主键id
    private Integer transportid;
    //运输预计到达时间
    private Date transporttime;
    //开始运输
    private String transportstart;
    //运输到达
    private String transportend;
    //司机的id
    private Integer userid;
    //运输id
    private Integer driverid;

}
