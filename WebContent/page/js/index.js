function webSocketSession() {
    var websocket;
    var Onlineset = new Set(); //维护在线人数的容器
    var mydate = new Date(); //时间对象，用于显示时间
    var chatInfo = [] //聊天记录

    websocket = new WebSocket("ws://localhost:8080/WebSockt/WebSocketTest");
    websocket.onopen = function (evnt) {
//    	1.发送json字符串，后端主要通过name和对应的session记录在线人数
//    	例如：{"content":"join","date":"2020-06-15 14:09:17","name":"远生哥"}
        var name = $("#username").text();
        websocket.send("a" + name);
    };

    websocket.onmessage = function (evnt) {
//    	服务器转发的信息格式：普通信息：{"content":"你好你好","date":"2020-06-15 14:09:17","name":"远生哥"}
//    	每当有人加入或者退出，服务器发送的信息：{"name":"servicer","onlineNumber":["user1","user2","user3"]}
        var data = evnt.data;
        console.log(data)
        // console.log(Onlineset)
        //维护在线用户模块
        if (data[0] == "a") { //用户维护在线用户队列
            var name = data.slice(1); //获得名字
            if (data[1] == "1") { //用户下线，维护队列
                Onlineset.delete(name);
            } else { //用户上线，维护队列
                Onlineset.add(name);
            }
            // 维护用户列表
            var listnameset = "<div class='list-group-item active'>" +
                "<h4 class='list-group-item-heading'>聊天室成员</h4></div>";
            $("#userlist").html(listnameset);
            Onlineset.forEach(function (item) {
                if (item.toString() != "") {
                    var content = "<div class='list-group-item '>" +
                        "<h4 class='list-group-item-heading'>" +
                        "<span class='glyphicon glyphicon-user'></span >" + item.toString() + "</h4></div>";

                    $("#userlist").append(content);
                    //添加在线用户到列表中
                }
            });
            //维护在线用户结束
            return;
        }
        //维护消息发送模块
        //解析data，获得名字与内容
        var namelenth = parseInt(data[0]); //获得名字的长度
        var name = data.slice(1, 1 + namelenth) || "匿名"; //获得名字
        var content = data.slice(1 + namelenth); //获得消息内容
        // console.log(content)
        chatInfo.unshift({
            name: name,
            content: content
        })
        // console.log(chatInfo)
        var isSelf = "other"; //其他人消息样式
        var image = "other";
        // if (name == $("#username").text()) {
        //     //消息来自自己，改变样式
        //     style = "alert-info";
        //     image = "myself";
        // }
        // var message = "<li class='meida alert " + style + " ' role='alert'><div class='media-left'><img src='./image/" + image + ".jpg' width='30px'" +
        //     "style='margin-top: 40%' alt='' class='media-object img-circle '>" +
        //     "</div><div class='media-body '><h6 class='meida-heading text-warning '>" + name +
        //     "&nbsp;&nbsp;" + mydate.toLocaleString() + "</h6>" + "<p ><xmp>" + content + "</xmp></p></div></li>";
        if (name == $("#username").text()) {
            //消息来自自己，改变样式
            isSelf = "my";
            image = "myself";
        }

        //     <div class="message my">
        //     <div class="avator">
        //         <img src="./image/myself.jpg" alt="" srcset="">
        //     </div>
        //     <div class="content">
        //         <div class="name" id="username">
        //             北京东例子
        //         </div>
        //         <div class="msg">
        //             <pre> 版权所有 (C) Microsoft Corporation。保留所有权利。 </pre>
        //         </div>

        //     </div>
        // </div>
        var message = `<div class="message ${isSelf}">
                            <div class="avator">
                                <img src="./image/${image}.jpg" alt="" srcset="">
                            </div>
                            <div class="content">
                                <div class="name" id="username"> ${name}</div>
                                <div class="msg"><pre > ${content}</pre></div>
                            </div>
                        </div>`

        $("#messageList").append(message);
        // console.log(chatInfo)
        // console.log(document.getElementById('messageList').scrollHeight - document.getElementById('messageList').scrollTop)
        document.getElementById('messageList').scrollTop = document.getElementById('messageList').scrollHeight


    };


    websocket.onerror = function (evnt) { };
    websocket.onclose = function (evnt) { };

    //初始化事件
    $("#sendMessage").on("click", function (e) {
        var name = $("#username").text();
        var msg = $("#chat-input-area").val();
        if (msg.length === 0) {
            return
        }
//        ps：这里我测试，注释一下
//        var a = name.length + name + msg;
        	
        websocket.send(msg);
        $("#chat-input-area").val(""); //发送消息后清空输入框
        $("#navbar-collapse").collapse('hide'); //手机响应式下拉菜单隐藏

    });



    $('#chat-input-area').bind('keypress', function (event) {
        if (event.keyCode == "13") //回车提交
        {
            var name = $("#username").text();
            var a = name.length + name + $("#chat-input-area").val();
            $("#chat-input-area").val(""); //发送消息后清空输入框
            $("#navbar-collapse").collapse('hide'); //手机响应式下拉菜单隐藏
//            这里测试改了一下 a，你改回来，
            websocket.send(msg);
            return false; //阻止事件广播
        }
    });
    $('#chat-input-area').keypress(function (e) {
        if (e.ctrlKey && e.which == 13 || e.which == 10) {
            var msg = $("#chat-input-area").val() + "\n"
            $("#chat-input-area").val(msg)
        } else if (e.shiftKey && e.which == 13 || e.which == 10) {
            var msg = $("#chat-input-area").val() + "\n"
            $("#chat-input-area").val(msg)

        }
    })
}


function clean() { //清空屏幕
    $("#messageList").html("");
    $("#chat").val("");
    $("#navbar-collapse").collapse('hide');
}

function savename() { // 保存名字
    if (/[.*<|>.*]|.{9}/.test($("#newname").val()) || $("#newname").val() == "") {
        $("[data-toggle='popover']").popover('show');
        return;
    }
    $("#username").text($("#newname").val());
    $('#myModal').modal('hide');
    $("[data-toggle='popover']").popover('hide');
    localStorage.setItem('username', $("#newname").val());

    webSocketSession(); //进入核心模块
}

// 名字框绑定提示信息事件
$("#newname").focus(function (event) {
    $("[data-toggle='popover']").popover('show');
}).blur(function (event) {
    $("[data-toggle='popover']").popover('hide');
}).hover(function () {
    $("[data-toggle='popover']").popover('show');
}, function () {
    $("[data-toggle='popover']").popover('hide');
});

window.onload = function () {
    this.caltWidthAndHeight()
    this.init()
}

function caltWidthAndHeight() {
    var height = window.innerHeight - 210
    var width = window.innerWidth - 420

    $(".chat-main").css("width", width + "px")
    $("#chat-input-area").css("width", width + "px")
    $(".chat-list").css("height", height + "px")
}

$(window).resize(function () {
    this.caltWidthAndHeight()
});

function init() {
    let name = localStorage.getItem('username');
    if (name) {
        $("#reload-text").css("display", "block")
        var html = `<div style="margin-bottom:10px;font-size: 28px;">以原身份进入聊天室：</div> <span>您上次登录的昵称是:${name} 点击重新进入按钮以该昵称进入</span>`
        $("#reload-text").append(html)
        $("#myModal").modal('show');
    } else {
        $("#reload-text").css("display", "none")
        $(function () {
            // 先输入名字
            $("#myModal").modal('show');
            $("#newname").focus();

        });
    }
    $("#reload-btn").on("click", function () {
        let name = localStorage.getItem('username');
        $("#username").text(name);
        console.log(name)
        $('#myModal').modal('hide');
        $("[data-toggle='popover']").popover('hide');
        webSocketSession();
    });

}
