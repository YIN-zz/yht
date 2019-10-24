package com.ygt.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    //评论主键id
    private Integer commentid;
    //评论人的姓名
    private String commentname;
    //评论时间
    private String commenttime;
    //评论内容
    private String commenttext;
    //评论回复
    private String commentanswer;
    //外键发布人的id
    private Integer cmomentsid;
}
