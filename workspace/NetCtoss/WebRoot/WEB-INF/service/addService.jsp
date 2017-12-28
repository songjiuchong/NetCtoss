<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script language="javascript" type="text/javascript" src="../js/jquery-1.4.3.js"></script>
        <script language="javascript" type="text/javascript">
        
        	//检验页面是否存在错误;
        	var accountIdFlag;
        	var unixHostFlag;
        	var osUsernameFlag;
        	var loginPasswordFlag;
        	var validateLoginPasswordFlag;
        	
        	//根据身份证号查找账务账号ID;
        	function searchAccount(){
        		//取得身份证号;
        		var idcardNo = $("#idcardNo").val();
        		if(idcardNo == null || idcardNo == ""){
        			$("#idcardNoMsg").addClass("error_msg");
        			$("#idcardNoMsg").text("请输入身份证号。");
        			return;
        		}
        		//异步请求查询账务账号;
        		$.post(
        			"searchAccount.action",
        			{"idcardNo":idcardNo},
        			function(data){
        				var account = data;
        				if(account==null||account==""||account==undefined){
        					accountIdFlag=false;
        					$("#idcardNoMsg").addClass("error_msg");
        					$("#idcardNoMsg").text("账务账号中没有匹配的身份证号,请重写输入");
        					$("#accountId").val("");
        					$("#loginName").val("");        					
        				}else{
        					accountIdFlag=true;
        					$("#accountId").val(account.id);
        					$("#loginName").val(account.loginName);
        					$("#idcardNoMsg").removeClass("error_msg");
        					$("#idcardNoMsg").text("查询成功,重新输入身份证后请重新查询。");
        				}
        		});
        	}
        	
        	//服务器IP输入框添加焦点移出事件;
            $(function(){
            	$("#unixHost").blur(function(){
            		//取得输入框中的值;
            		var unixHost = $(this).val();
            		//若为空,则提示,并给出错误提示的样式;
            		if(unixHost == null||unixHost==""){
            		    $("#unixHostMsg").text("服务器IP不能为空");
            			$("#unixHostMsg").addClass("error_msg");
            			unixHostFlag=false;
            			return;
            		}
            		$("#unixHostMsg").text("15 长度，符合IP的地址规范");
            		$("#unixHostMsg").removeClass("error_msg"); 
            		unixHostFlag=true;
            	});
            });
            
            //OS帐号输入框添加焦点移出事件;
            $(function(){
            	$("#osUserName").blur(function(){
            		//取得输入框中的值;
            		var osUserName = $(this).val();
            		//若为空,则提示,并给出错误提示的样式;
            		if(osUserName == null||osUserName==""){
            		    $("#osUserNameMsg").text("OS帐号不能为空");
            			$("#osUserNameMsg").addClass("error_msg");
            			osUsernameFlag=false;
            			return;
            		}
            		$("#osUserNameMsg").text("8长度以内的字母、数字和下划线的组合");
            		$("#osUserNameMsg").removeClass("error_msg"); 
            		osUsernameFlag=true;
            	});
            });
            
            //给密码输入框添加焦点移出事件;
             $(function(){
            	$("#password").blur(function(){
            		//取得输入框中的值;
            		var password = $(this).val();
            		//若为空,则提示,并给出错误提示的样式;
            		if(password == null||password==""){
            			$("#loginPasswordMsg").text("密码不能为空");
            			$("#loginPasswordMsg").addClass("error_msg");
            			loginPasswordFlag=false;
            			return;
            		}
            		$("#loginPasswordMsg").text("30长度以内的字母、数字和下划线的组合");
            		$("#loginPasswordMsg").removeClass("error_msg");
            		loginPasswordFlag=true;
            		var validatePassword = $("#validatePassword").val();
            	 	if(password!=validatePassword){
            			$("#validatePasswordMsg").addClass("error_msg");
            			validateLoginPasswordFlag=false;
            		}else{
            			$("#validatePasswordMsg").removeClass("error_msg");
            			validateLoginPasswordFlag=true;
            		}
            	 });
              });
            	
            //给密码验证输入框添加焦点移出事件;
             $(function(){
            	$("#validatePassword").blur(function(){  
            	var validatePassword = $(this).val();          	
            	var password = $("#password").val();
            	if(password!=validatePassword){
            		$("#validatePasswordMsg").addClass("error_msg");
            		validateLoginPasswordFlag=false;
            	}else{
            		$("#validatePasswordMsg").removeClass("error_msg");
            		validateLoginPasswordFlag=true;
            	}
            	});
             });
        	
            //保存成功的提示信息
            function showResult() {
            	if(accountIdFlag&&unixHostFlag&&osUsernameFlag&&loginPasswordFlag&&validateLoginPasswordFlag){
            		document.getElementById("addServiceForm").submit();
            	}else{
                	showResultDiv(true);;
                }
            }
            
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
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
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../service/service_list.html" class="service_on"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">保存失败！</div>
            <form action="addService.action" method="post" id="addServiceForm" class="main_form">
                <!--内容项-->
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" id="idcardNo" value="" class="width180" />
                    <input type="button" value="查询账务账号" class="btn_search_large" onclick="searchAccount();" />
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="idcardNoMsg">请输入身份证号。</div>
                </div>
                <div class="text_info clearfix"><span>账务账号：</span></div>
                <div class="input_info">
                	<input type="hidden" name="service.accountId" id="accountId"/>
                    <input type="text" readonly id="loginName" value="" onkeyup="searchAccounts(this);" />
                    <span class="required">*</span>
                    <div class="validate_msg_long"></div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
					<s:select name="service.costId" list="costList" listKey="id" listValue="name"></s:select>
                </div> 
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <input type="text" id="unixHost" name="service.unixHost" value="192.168.0.23" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="unixHostMsg">15 长度，符合IP的地址规范</div>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info">
                    <input type="text" id="osUserName" name="service.osUsername" value=""  />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="osUserNameMsg">8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input type="password" id="password" name="service.loginPasswd" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="loginPasswordMsg">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input type="password" id="validatePassword"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="validatePasswordMsg">两次密码必须相同</div>
                </div>     
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='findService.action';"/>
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
