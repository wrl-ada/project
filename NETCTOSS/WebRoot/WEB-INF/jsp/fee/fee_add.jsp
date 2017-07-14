<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
        //保存结果的提示
        function showResult() {
            showResultDiv(true);
            window.setTimeout("showResultDiv(false);", 3000);
        }
        function showResultDiv(flag) {
            var divResult = document.getElementById("save_result_info");
            if (flag)
                divResult.style.display = "block";
            else
                divResult.style.display = "none";
        }

        //切换资费类型
        function feeTypeChange(type) {
            var inputArray = document.getElementById("main").getElementsByTagName("input");
            if (type == 1) {
                inputArray[4].readonly = true;
                inputArray[4].value = "";
                inputArray[4].className += " readonly";
                inputArray[5].readonly = false;
                inputArray[5].className = "width100";
                inputArray[6].readonly = true;
                inputArray[6].className += " readonly";
                inputArray[6].value = "";
            }
            else if (type == 2) {
                inputArray[4].readonly = false;
                inputArray[4].className = "width100";
                inputArray[5].readonly = false;
                inputArray[5].className = "width100";
                inputArray[6].readonly = false;
                inputArray[6].className = "width100";
            }
            else if (type == 3) {
                inputArray[4].readonly = true;
                inputArray[4].value = "";
                inputArray[4].className += " readonly";
                inputArray[5].readonly = true;
                inputArray[5].value = "";
                inputArray[5].className += " readonly";
                inputArray[6].readonly = false;
                inputArray[6].className = "width100";
            }
        }
        //检查资费名
        var name_flag = false;//代表name是否通过检测
        function checkName(){
        	name_flag = false;
        	//检查资费名是否为空
        	var v_name = $("#name").val();
        	if(v_name == ""){
        		$("#name_error").html("资费名不能为空");
        		$("#name_error").addClass("error_msg");
        		return false;//此处将false作为doSubmit函数返回值
        	}
        	//alert("--请求执行----")
        	//检查资费名是否重复
        	//ajax(/fee/checkName.from)-->CheckNameController-->CostMapperDao-->json(返回boolean值)
        	$.ajax({
        		async:false,//同步
        		type:"POST",
        		url:"checkName.from",
        		data:{"name":v_name},
        		success:function(ok){
        		//alert("---正在执行-----");
        			if(ok){
        				$("#name_error").html("资费名可用");
        				$("#name_error").removeClass("error_msg");
        				name_flag = true;//允许提交
        			}else{
        				$("#name_error").html("资费名被占用,换一个吧");
        				$("#name_error").addClass("error_msg");
        				name_flag = false;
        			}
        		}
        	});
        	//alert("--请求执行结束----");
        	//通过检查返回true,未通过返回false
        	//前面ajax用同步，目的是等前面ajax请求
        	//回调函数执行完毕后再执行此行代码
        	return name_flag;
        }
        //检测基本时长
        var baseduration_flag = false;
        function checkBaseDuration(){
            baseduration_flag = false;
        	//获取选中的资费类型
        	var v_type = $("input[name='cost_type']:checked").val();
        	//获取基本时长输入的内容
        	var	baseduration = $("#base_duration").val();
        	if(v_type == 2){//套餐类型
        		//基本时长不能为空
        		if(baseduration == ""){
        			$("#base_duration_error").html("不能为空");
        			$("#base_duration_error").addClass("error_msg");
        			baseduration_flag = false;
        		}else{//不为空，数值型，且数值在1~600之间
        			//判断输入的内容是否为数值
        			//isNaN检查一个字符串不是数值类型
        			//isString为false表示输入的是数值，否则为字符串
        			var isString = isNaN(baseduration);
        			if(!isString){//输入的是数值，判断范围是否正确
        				if(parseInt(baseduration)>=1 && parseInt(baseduration)<=600){
        					$("#base_duration_error").html("√");
        			        $("#base_duration_error").removeClass("error_msg");
        			        baseduration_flag = true;
        				}
        			}
        			if(!baseduration_flag){
        				$("#base_duration_error").html("必须在1~600之间");
        			    $("#base_duration_error").addClass("error_msg");
        			    baseduration_flag = false;
        			}
        		}
        	}else if(v_type==1 || v_type ==3){
        		if(baseduration==""){
        			baseduration_flag = true;
        		}else{
        			$("#base_duration_error").html("此项应为空");
        			$("#base_duration_error").addClass("error_msg");
        			baseduration_flag = false;
        		}
        	}
        	return baseduration_flag;
        }
        //检测基本资费
        var basecost_flag = false;//代表基本资费是否通过检测
        function checkBaseCost(){
            basecost_flag = false;
            //获取选中的资费类型
        	var v_type = $("input[name='cost_type']:checked").val();
        	var basecost = $("#base_cost").val();
        	//检查基本资费是否符合匹配规则
        	if(v_type==1 || v_type==2){//包月,套餐
        		if(basecost==""){//包月时基本资费不能为空
        			$("#basecost_error").html("不能为空");
        			$("#basecost_error").addClass("error_msg");
        			basecost_flag = false;
        		}else{//不为空
        			var isString = isNaN(basecost);
        			if(!isString){//是数值
        				if(parseInt(basecost)>0 && parseInt(basecost)<99999.9){//符合范围
        					$("#basecost_error").html("√");
        			        $("#basecost_error").removeClass("error_msg");
        			        basecost_flag = true;
        				}
        			}
        			if(!basecost_flag){
        				$("#basecost_error").html("请输入0-99999.99之间的数值");
        			    $("#basecost_error").addClass("error_msg");
        				basecost_flag = false;
        			}
        		}
        	}else if(v_type==3){
        		if(basecost==""){
        			basecost_flag = true;
        		}else{
        			$("#basecost_error").html("此项应为空");
        			$("#basecost_error").addClass("error_msg");
        		    basecost_flag = false;
        		}
        	}
        	return basecost_flag;
        }
        //检查单位资费
        var unitcost_flag = false;
        function checkUnitCost(){
        	unitcost_flag = false;
        	var v_type = $("input[name='cost_type']:checked").val();
        	var unit_cost = $("#unit_cost").val();
        	if(v_type==2 || v_type == 3){
        		if(unit_cost == ""){
        			$("#unitcost_error").html("不能为空");
        			$("#unitcost_error").addClass("error_msg");
        			unitcost_flag = false;
        		}else{
        			var isString = isNaN(unit_cost);
        			if(!isString){
        				if(parseInt(unit_cost)>0.0 && parseInt(unit_cost)<99999.9){
        					$("#unitcost_error").html("√");
        			        $("#unitcost_error").removeClass("error_msg");
        			        unitcost_flag = true;
        				}
        			}
        			if(!unitcost_flag){
        				$("#unitcost_error").html("请输入0-99999.99之间的数值");
        			    $("#unitcost_error").addClass("error_msg");
        			    unitcost_flag = false;
        			}
        		}
        	}else if(v_type==1){
        		if(unit_cost == ""){
        			unitcost_flag = true;
        		}else{
        			$("#unitcost_error").html("此项应为空");
        			$("#unitcost_error").addClass("error_msg");
        			unitcost_flag = false;
        		}	
        	}
        	return unitcost_flag;
        }
        //检查资费说明内容是否符合要求
        var descr_flag = false;
        function checkDescr(){
        	descr_flag = false;
        	//获取框中输入的内容
        	var descr = $("#descr").val();
        	if(descr == ""){
        		descr_flag = true;
        	}else{
        		var regx = /^[\w\u4e00-\u9fa5]+$/;
        	    if(regx.test(descr)){
				    descr_flag = true;      	
        	    }else{
	        		$("#descr_error").html("100长度的字母、数字、汉字和下划线的组合");
	        		$("#descr_error").addClass("error_msg");
	        		descr_flag = false;
        		}
        	}
        	return descr_flag;
        }
        //表单是否可以被提交
        var form_flag = false;//表单是否提交标记
        function doSumbit(){
        	name_flag = checkName();//检测资费名
        	baseduration_flag = checkBaseDuration();//检查基本时长
        	basecost_flag = checkBaseCost();//检测基本资费
        	unitcost_flag = checkUnitCost();//检查单位资费
        	descr_flag = checkDescr();//检查资费说明内容
        	form_flag = name_flag && baseduration_flag && basecost_flag && unitcost_flag && descr_flag;
        	return form_flag;
        }
    </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_on"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../service/service_list.html" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_fail">保存失败，资费名称重复！</div>
            <form action="add.from" method="post" class="main_form" onsubmit="return doSumbit();">
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" name="name" id="name"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="name_error">50长度的字母、数字、汉字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="cost_type" value="1" id="monthly" onclick="feeTypeChange(1);" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="cost_type" value="2" checked="checked" id="package" onclick="feeTypeChange(2);" />
                    <label for="package">套餐</label>
                    <input type="radio" name="cost_type" value="3" id="timeBased" onclick="feeTypeChange(3);" />
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" name="base_duration" class="width100" id="base_duration"/>
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="base_duration_error"></div>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text" name="base_cost" class="width100" id="base_cost"/>
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="basecost_error"></div>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text" name="unit_cost" class="width100" id="unit_cost"/>
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="unitcost_error"></div>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70" name="descr" id="descr"></textarea>
                    <div class="validate_msg_short" id="descr_error"></div>
                </div>                    
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
