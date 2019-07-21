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
            success:function (data) {
                if (data == '1'){
                    location.href = "../html/index.html";
                }else{
                    layer.msg('登录名或密码错误');
                }
            }
        })
        return false;
    })

});