<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
    	<script type="text/javascript">
    		function doSubmit(){
    			//表单数据js检查
    			//提交表单
    			document.getElementById("loginForm").submit();
    		}
    	</script>
    </head>
    <body class="index">
    <form id="loginForm" action="login.from" method="post">
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input name="admin_code" type="text" class="width150" /></td>
                    <td class="login_error_info"><span class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input name="password" type="password" class="width150" /></td>
                    <td><span class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="code" type="text" class="width70" /></td>
                    <td>
                        <a href="javascript:;" onclick="document.getElementById('num').src = 'getCode.from?'+(new Date()).getTime()">
                        <img id = "num" src="getCode.from" alt="验证码" title="点击更换" />
                        </a>
                    </td>  
                    <td><span class="required">${err}</span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="#" onclick="doSubmit();"><img src="../images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required">${error}</span></td>                
                </tr>
            </table>
        </div>
        </form>
    </body>
</html>
