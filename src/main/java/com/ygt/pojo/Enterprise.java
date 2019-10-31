package com.ygt.pojo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Enterprise {
    //企业主页id
    private Integer eid;

    //企业介绍文字
    private String etext;


    //企业上传照片
    private String epicture;

    //企业上传视频
    private String evideo;

    //企业信息文化
    private String einfo;
    //修改的时间
    private String etime;
    //企业登陆人id
    private Integer userid;
}
