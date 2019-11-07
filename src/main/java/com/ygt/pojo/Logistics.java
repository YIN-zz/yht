package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Logistics {

    //物流id
    private Integer logisticsid;
    //物流公司名字
    private String logisticscompany;
    //物流公司地址
    private String logisticsaddress;
    //物流负责人
    private String logisticsname;
    //物流电话
    private String logisticsphone;
    //绑定用户id
    private Integer userid;
}
