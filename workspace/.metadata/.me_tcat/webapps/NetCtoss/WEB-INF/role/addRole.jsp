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
        	var nameFlag;
        	
        	//检查修改后用户名的合法性;nameCheck
        	$(function(){
        		$("#roleName").blur(function(){
        			var roleName=$(this).val();
        			//检查角色名是否为空;
        			if(roleName==null||roleName==""){
        			$("#roleNameMsg").text("角色名称不能为空");
        			$("#roleNameMsg").addClass("error_msg");
        			nameFlag=false;
        			return;
        			}
        			//用正则表达式检查角色名的合法性;
        			var re=/^([\u4E00-\u9FA5]|[a-zA-Z]|[0-9]){3,20}$/;
        			if(!re.test(roleName)){
        				$("#roleNameMsg").text("大于2且不超过20长度的字母、数字和汉字的组合！");
        				$("#roleNameMsg").addClass("error_msg");
        				nameFlag=false;
        				return;
        			}
        			//异步查找是否存在相同的角色名;
        			$.post("updateRoleValidate.action",
        				{"roleId":-1,"roleName":roleName},
        				function(data){
            				if(data){
            					//返回值为true,则校验通过;
            					$("#roleNameMsg").text("不能为空、大于2且不超过20长度的字母、数字和汉字的组合！");
            					$("#roleNameMsg").removeClass("error_msg");   
            					nameFlag=true;         					
            				}else{
            					//如果返回值为false,则校验不通过;
            					$("#roleNameMsg").text("角色名称已存在,重新输入！");
            					$("#roleNameMsg").addClass("error_msg");    
            					nameFlag=false;        					
            				}
            		});
        		});
        	});
        
            //保存成功的提示消息
            function showResult() {
            	if(!nameFlag)
            	return;
            	document.getElementById("addRoleForm").submit();
                showResultDiv(true);
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
                <li><a href="../role/role_list.html" class="role_on"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
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
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，角色名称重复！-->
            <form action="addRole.action" method="post" id="addRoleForm" class="main_form">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <s:textfield name="role.name" id="roleName" cssClass="width200"></s:textfield>
                    <span class="required">*</span>
                    <div id="roleNameMsg" class="validate_msg_medium">不能为空、大于2且不超过20长度的字母、数字和汉字的组合</div>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                    	<s:checkboxlist name="role.privilegeIds" list="privileges" listKey="id" listValue="name"></s:checkboxlist>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny">至少选择一个权限</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='findRole.action';"/>
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
