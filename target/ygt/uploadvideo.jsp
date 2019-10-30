<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/10/22
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<form id="formsub" method="post" enctype="multipart/form-data"
      role="form">
    <div class="form-group">
        <label for="name">名称</label> <input name="text" type="text"
                                            class="form-control" id="name" placeholder="请输入名称">
    </div>
    <div class="form-group">
        <label for="inputfile">图片路径</label> <input type="file" name="file"
                                                   id="inputfile"> <br> <label for="inputfile">视频路径</label>
        <video style="display:none;" controls="controls" id="aa" oncanplaythrough="myFunction(this)">
        </video>
        <input type="file" id="inputfile1" οnchange="changeFile(this)" name="videofile">
        <input  style="display: none;" type="text" name="vtime" value=""  id="getDuration"/>
        <p class="help-block">请确认上传的格式正确。</p>
    </div>
    <div class="checkbox">
        <label> <input type="checkbox">请打勾
        </label>
    </div>
    <input id="time" type="hidden" value="">
    <button type="button" name="time" id="submitimg" class="btn btn-default">提交</button>
</form>
</body>

<script>
    $('#submitimg').click(function() {//通过单击按钮实现form表单提交。
        var form = new FormData(document.getElementById("formsub"));
        alert(document.getElementById('name').value);
        $.ajax({
            url : "/addImg.do",
            type : "post",
            data : form,
            processData : false,
            contentType : false,
            success : function(data) {
                if (data = 'Yes') {
                    alert("success");
                }
            },
            error : function(e) {
                alert("错误！！");
            }
        });
    });
    //获取视频时长 格式为 00:00  暂时只支持获取MP4格式 ，其他格式会为空
    function myFunction(ele) {
        var hour = parseInt((ele.duration)/3600);
        var minute = parseInt((ele.duration%3600)/60);
        var second = Math.ceil(ele.duration%60);
        if(minute<10 && second>9){
            document.getElementById("getDuration").value="0"+minute+":"+second;
        }
        if(minute<10 && second<10){
            document.getElementById("getDuration").value="0"+minute+":"+"0"+second;
        }
        if(minute>9 && second<10){
            document.getElementById("getDuration").value=minute+":"+"0"+second;
        }
        if(minute>9 && second>10){
            document.getElementById("getDuration").value=minute+":"+second;
        }
        //console.log(Math.floor(ele.duration));
        //document.write("这段视频的时长为："+hour+"小时，"+minute+"分，"+second+"秒");
        // document.getElementById("getDuration").value=minute+":"+second;
    }
    function changeFile(ele){
        var video = ele.files[0];
        var url = URL.createObjectURL(video);
        console.log(url);
        document.getElementById("aa").src = url;
    }
</script>
</html>
