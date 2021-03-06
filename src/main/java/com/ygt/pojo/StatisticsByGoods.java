package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatisticsByGoods {
    //出入库id
    private Integer driverid;
    //公司名字
    private String drivercompany;
    //备案证号
    private String driverrecordid;
    //出入库时间
    private String drivertime;
    //货车司机名字
    private String driverdriver;
    //车牌号
    private String drivernumber;
    //身份证号
    private String driveridentity;
    //手机号
    private String driverphone;
    //目的地
    private String driverbourn;
    //备案号或者许可证照片
    private String driverrecordphoto;
    //驾驶证或行驶证照片
    private String driverphoto;
    //司机站在货车前照片
    private String drivercarphoto;
    //货品出入库
    private String driverrinout;
    //货物主键id
    private Integer goodid;
    //货物类型
    private String goodtype;
    //货物的用途
    private String goodruse;
    //货物名称
    private String goodname;
    //货物数量
    private Integer goodcount;
    //货物重量
    private Double goodeweight;
    //库内货物总id
    private Integer chid;
}
