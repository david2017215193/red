<%--
  Created by IntelliJ IDEA.
  com.zhihu.domain.User: 小胖狗wy
  Date: 2018/6/3
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%%>
<html>
<head>
    <base href="<%=basePath%>">
    <script>
        function show()
        {
            var a1=document.getElementById("textId");
            var show=document.getElementById("show");
            show.innerHTML="你：   "+a1.value;
        }
    </script>
    <title>talk</title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h3 class="text-center">
                与机器人对话
            </h3>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <form action="/talkServlet"  method="post">
                <fieldset>
                    <legend>说出你想说的话</legend><br />
                    ${msg2}
                    <input type="text" name = "Notification" id = "talk"/>
                    <span class="help-block">........</span>

                    <button class="btn"  type="submit">
                        提交
                    </button>
                </fieldset>
            </form>
        </div>
        <div class="span6">
            <div class="hero-unit">
                <input type="button" name="展示聊天记录" value="展示聊天记录" onclick="show();" />
                <div id="show"></div>
                ${msg}
            </div>
        </div>
    </div>
</div>
</body>
</html>
