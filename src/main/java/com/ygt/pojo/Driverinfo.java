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
    private Integer driver_rid;
    //公司名字
    private String driver_company;
    //备案证号
    private String driver_recordid;
    //出入库材料类别
    private String driver_type;
    //出入库时间
    private String driver_time;
    //目的地
    private String driver_bourn;
    //货车司机名字
    private String driver_driver;
    //手机号
    private String driver_phone;
    //车牌号
    private String driver_number;
    //备案号或者许可证照片
    private String driver_recordphoto;
    //驾驶证或行驶证照片
    private String driver_photo;
    //司机站在货车前照片
    private String driver_carphoto;
    //货品用途
    private String driver_ruse;
    //货品出入库
    private String driver_rinout;
    //仓库管理员id
    private Integer mid;

    //查询List集合
    private List<Goodsinfo> goodsinfoList;
}
