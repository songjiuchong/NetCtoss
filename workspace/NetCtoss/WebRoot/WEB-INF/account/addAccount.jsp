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
            
            //判断页面是否存在错误的内容;
            var realNameFlag;
            var idcardNoFlag;
        	var accountNameFlag;
        	var loginPasswordFlag;
        	var validateLoginPasswordFlag;
        	var telephoneFlag;
        	
        	//给姓名输入框添加焦点移出事件;
            $(function(){
            	$("#realName").blur(function(){
            		//取得输入框中的值;
            		var realName = $(this).val();
            		//若为空,则提示,并给出错误提示的样式;
            		if(realName == null||realName==""){
            		    $("#realNameMsg").text("姓名不能为空");
            			$("#realNameMsg").addClass("error_msg");
            			realNameFlag=false;
            			return;
            		}
            		$("#realNameMsg").text("20长度以内的汉字、字母和数字的组合");
            		$("#realNameMsg").removeClass("error_msg"); 
            		realNameFlag=true;
            	});
            });
            
            //给身份证输入框添加焦点移出事件;
            $(function(){
            	$("#idcardNo").blur(function(){
            		//取得输入框中的值;
            		var idcardNo = $(this).val();
            		//若为空,则提示,并给出错误提示的样式;
            		if(idcardNo == null||idcardNo==""){
            		    $("#idcardNoMsg").text("身份证号不能为空");
            			$("#idcardNoMsg").addClass("error_msg");
            			idcardNoFlag=false;
            			return;
            		}
            		$("#idcardNoMsg").text("正确的身份证号码格式号");
            		$("#idcardNoMsg").removeClass("error_msg"); 
            		idcardNoFlag=true;
            		//根据身份证号生成生日;
            		var year = idcardNo.substring(6,10);
            		year+="-";
            		var mon = idcardNo.substring(10,12);
            		mon+="-";
            		var day = idcardNo.substring(12,14);
            		var birthday = year+mon+day;
            		$("#birthDay").val(birthday);
            	});
            });
            
            //给电话输入框添加焦点移出事件;
            $(function(){
            	$("#telephone").blur(function(){
            		//取得输入框中的值;
            		var telephone = $(this).val();
            		//若为空,则提示,并给出错误提示的样式;
            		if(telephone == null||telephone==""){
            		    $("#telephoneMsg").text("电话号码不能为空");
            			$("#telephoneMsg").addClass("error_msg");
            			telephoneFlag=false;
            			return;
            		}
            		$("#telephoneMsg").text("正确的电话号码格式：手机或固话");
            		$("#telephoneMsg").removeClass("error_msg"); 
            		telephoneFlag=true;
            	});
            });
            	
            	       	
            //给资费输入框添加焦点移出事件;
            $(function(){
            	$("#accountName").blur(function(){
            		//取得输入框中的值;
            		var accountName = $(this).val();
            		//若为空,则提示,并给出错误提示的样式;
            		if(accountName == null||accountName==""){
            			$("#accountNameMsg").text("账务账号不能为空");
            			$("#accountNameMsg").addClass("error_msg");
            			accountNameFlag=false;
            			return;
            		}
            			//发送异步请求给AddAccountValidateAction,返回是否验证通过;
            			$.post("addAccountValidate.action",
            			{"accountName":accountName},
            			function(data){
            				if(data){
            					//返回值为true,则校验通过;
            					$("#accountNameMsg").text("30长度以内的字母、数字和下划线的组合");
            					$("#accountNameMsg").removeClass("error_msg");   
            					accountNameFlag=true;         					
            				}else{
            					//如果返回值为false,则校验不通过;
            					$("#accountNameMsg").text("账务账号名称已存在!");
            					$("#accountNameMsg").addClass("error_msg");    
            					accountNameFlag=false;        					
            				}
            			});
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
            function showResult(){
            	if(realNameFlag&idcardNoFlag&accountNameFlag&loginPasswordFlag&validateLoginPasswordFlag&telephoneFlag)
                {document.getElementById("addAccountForm").submit();
                }else{
					showResultDiv(true);
                }
            }
            
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }

            //显示选填的信息项
            function showOptionalInfo(imgObj) {
                var div = document.getElementById("optionalInfo");
                if (div.className == "hide") {
                    div.className = "show";
                    imgObj.src = "../images/hide.png";
                }
                else {
                    div.className = "hide";
                    imgObj.src = "../images/show.png";
                }
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
                <li><a href="../account/account_list.html" class="account_on"></a></li>
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
            <!--保存成功或者失败的提示消息-->     
            <div id="save_result_info" class="save_fail">页面存在错误信息,提交失败！</div>
            <form action="addAccount.action" method="post" id="addAccountForm" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input type="text" name="account.realName" id="realName" value="" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="realNameMsg">20长度以内的汉字、字母和数字的组合</div>
                </div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" name="account.idcardNo" id="idcardNo" value="" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="idcardNoMsg">正确的身份证号码格式号</div>
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <input type="text" id="accountName" name="account.loginName" value=""  />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="accountNameMsg" >30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input type="password" id="password" name="account.loginPassword"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="loginPasswordMsg">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input type="password" id="validatePassword" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="validatePasswordMsg">两次密码必须相同</div>
                </div>     
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input type="text" name="account.telephone" id="telephone" class="width200"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium" id="telephoneMsg">正确的电话号码格式：手机或固话</div>
                </div>                
                <!--可选项-->
                <div class="text_info clearfix"><span>可选项：</span></div>
                <div class="input_info">
                    <img src="../images/show.png" alt="展开" onclick="showOptionalInfo(this);" />
                </div>
                <div id="optionalInfo" class="hide">
                    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                    <div class="input_info">
                        <input type="text" name="account.recommenderId" />
                        <div class="validate_msg_long">正确的身份证号码格式</div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                        <input type="text" name="account.birthDate" value="" id="birthDay" readonly class="readonly" />
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" name="account.email" class="width350"/>
                        <div class="validate_msg_tiny">50长度以内，合法的 Email 格式</div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                    	<!--<s:select name="account.occupation" list="#{'a':'干部','b':'学生','c':'技术人员','d':'其他'}"/>-->
                        <select name="account.occupation" >
                            <option value="a">干部</option>
                            <option value="b">学生</option>
                            <option value="c">技术人员</option>
                            <option value="d">其他</option>
                        </select>                     
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type">
                        <input type="radio" name="account.gender" checked="checked" id="female" value="0"/>
                        <label for="female">女</label>
                        <input type="radio" name="account.gender" id="male" value="1"/>
                        <label for="male">男</label>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                        <input type="text" name="account.mailAddress" class="width350"/>
                        <div class="validate_msg_tiny">50长度以内</div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <input type="text" name="account.zipCode" />
                        <div class="validate_msg_long">6位数字</div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <input type="text" name="account.qq" />
                        <div class="validate_msg_long">5到13位数字</div>
                    </div>                
                </div>
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='findAccount.action';"/>
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
