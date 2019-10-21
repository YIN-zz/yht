package com.ygt.pojo;

import lombok.*;




@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Driverinfo {
    //出入库id
    private Integer drid;

    //出入库材料类别
    private String dtype;

    //备案证号
    private String recordid;

    //易制毒名称
    private String dname;

    //易制毒数量
    private int dcount;

    //易制毒总重量

    private double dwerght;

    //出入库时间
    private String rtime;

    //货车司机名字
    private String rdriver;

    //手机号
    private String rphone;

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
    private Integer mid;

    //易制毒化学品设备id
    private Integer chid;



}
