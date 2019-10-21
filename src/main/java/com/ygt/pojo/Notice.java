package com.ygt.pojo;

import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Notice {
    //警员发布信息id
    private Integer noid;

    //通知通告消息
    private String nmessage;

    //发布文字
    private String ntext;

    //发布视频
    private String nvideo;

    //发布图片
    private String npicture;

    //发布s时间
    private String ntime;

   //警员id
    private Integer pid;





}
