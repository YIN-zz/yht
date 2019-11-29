package com.ygt.pojo;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mapsinfo {

    //地图主键
    private Integer mapsid;
    //地图经度
    private String mapslongitude;
    //地图纬度
    private String mapslatitude;
    //外键用户id
    private Integer driverid;
}
