package com.ygt.pojo;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Moments {

    //朋友圈主键id
    private Integer momentsid;
    //发布人姓名
    private String momentsname;
    //发布人手机号
    private String momentsphone;
    //发布时间
    private String  momentstime;
    //发布内容
    private String momentstext;
    //发布第一张图片
    private String monepicture;
    //发布第二张图片
    private String mtwopicture;
    //发布第三张图片
    private String mthreepicture;

    //查询评论回复的list集合
    private List<Comment> comments;
}
