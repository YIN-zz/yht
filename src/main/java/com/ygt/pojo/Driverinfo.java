package com.ygt.pojo;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Driverinfo {
    //出入库id
    private Integer driverrid;
    //公司名字
    private String drivercompany;
    //备案证号
    private String driverrecordid;
    //出入库材料类别
    private String drivertype;
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
    //货品用途
    private String driverruse;
    //货品出入库
    private String driverrinout;
    //查询List集合
    private List<Goodsinfo> goodsinfoList;
}
