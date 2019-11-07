package com.ygt.pojo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LogCompanyinfo {
    //公司的id
    private Integer logcompanyid;
    //公司的名字
    private String logcompanyname;
    //公司的地址
    private String logcompanyaddres;
    //公司的负责人
    private String logcompanypeople;
    //公司负责人电话
    private String logcompanyphone;
}
