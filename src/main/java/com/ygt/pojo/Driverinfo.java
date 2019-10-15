package com.ygt.pojo;

import lombok.*;
import org.joda.time.DateTime;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Driverinfo {
    //出入库id
    private Integer drid;
    //备案证号
    private String recordid;
    //出入库时间
    private DateTime rtime;
    //货车司机名字
    private String rdriver;
    //手机号
    private Integer rphone;
    //车牌号
    private String rnumber;
    //备案号或者许可证照片
    private String recordphoto;
    //驾驶证或行驶证照片
    private String driverphoto;
    //司机站在货车前照片
    private String carphoto;
    //货品用途
    private String ruse;
    //货品出入库
    private String rinout;
    //仓库管理员id
    private Integer sid;
    //易制毒化学品id
    private Integer chid;
    //易制毒设备id
    private Integer fid;


}
