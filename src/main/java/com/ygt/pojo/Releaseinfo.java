package com.ygt.pojo;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Releaseinfo {

    //发布主键id
    private Integer releaseid;
    //发布人姓名
    private String releasename;
    //发布内容
    private String releasecontent;
    //发布第一张图片
    private String  releasefirst;
    //发布第二张图片
    private String releasesecond;
    //发布第三张图片
    private String releasethird;
    //发布时间
    private String releasetime;
    //发布人的头像(查看时候返回需要的）
    private String userphoto;
    //带评论查看的list集合
    private List<Commentinfo> commentinfos;
}
