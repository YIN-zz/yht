package com.ygt.pojo;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatisticsByAbnormal {
    //出入库id
    private Integer driverid;
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
    //异常主键id
    private Integer abnormalid;
    //发生异常情况
    private String abnormalstatus;
    //发生异常备注（详细情况）
    private String abnormalmessage;
    //发生异常时间
    private String abnormaltime;
    //发生异常地址
    private String abnormaladdress;
}
