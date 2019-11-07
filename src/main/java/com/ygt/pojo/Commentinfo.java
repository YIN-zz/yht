package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Commentinfo {

    //评论主键id
    private Integer commentid;
    //评论人的姓名
    private String commentname;
    //评论内容
    private String commentcontent;
    //评论时间
    private String commenttime;
    //评论人的照片(查看返回时用到）
    private String userphone;
    //发布人的id
    private Integer releaseid;
    //评论回复id
    private Integer replyid;
}
