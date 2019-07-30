layui.use(['form','layer','jquery'], function () {

    // 操作对象
    var form = layui.form;
    var $ = layui.jquery;
    form.on('submit(login)',function (data) {
        console.log(data.field);
        $.ajax({
            url:'/login/form',
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            data:data.field,
            success:function (res) {
                console.log(res);
                if (res.status == '0'){
                    location.href = "../html/index.html";
                }else{
                    layer.msg(res.msg);
                }
            }
        })
        return false;
    })
    $("#imgCode").on('click',function(){
        $("#imgCode").attr("src", "/getVerifyCode?" + Math.random());
    })


});