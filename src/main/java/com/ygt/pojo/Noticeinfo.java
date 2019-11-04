package com.ygt.pojo;

import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Noticeinfo {
    //警员发布信息id
    private Integer noticeid;

    //通知通告标题
    private String noticemessage;

    //发布文字
    private String noticetext;

    //发布图片1
    private String noticepicture1;

    //发布图片2
    private String noticepicture2;

    //发布图片3
    private String noticepicture3;

    //发布s时间
    private String ntime;

   //警员id
    private Integer userid;





}
