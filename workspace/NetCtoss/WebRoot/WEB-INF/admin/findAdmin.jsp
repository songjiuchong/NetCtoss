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
        
            //分页提交;
            function toPage(page){
            	//把当前页page记录到hidden中;
            	document.getElementById("page").value=page;
            	//提交表单;
            	document.getElementById("findForm").submit();
            }
            
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            
            //重置密码
            function resetPwd() {
            	var checkObjs = document.getElementsByName("selectAdmin");
            	var checked = false;
                var ids = new Array();
                for(var i=0;i<checkObjs.length;i++){
                	if(!checkObjs[i].checked)
                		continue;
                	checked = true;
                	var trObj = checkObjs[i].parentNode.parentNode;
               		var tdObjs = trObj.getElementsByTagName("td");
               		ids.push(tdObjs[1].innerHTML);
                }
                if(!checked){
                	alert("请至少选择一条数据！");
                	return;
                }
                $.post("resetPassword.action",
                {"ids":ids.toString()},
                function(data){
                	var pass = data;
                	if(pass){
                		alert("重置密码成功！");
                	}else{
                		alert("重置密码失败！");
                	}
                });
            }
            
            //alternative;
            //重置密码;
            function resetPwd_jquery(){
            	var trObjs = $("input[name='selectAdmin']:checked").parent().parent();
            	var ids = new Array();
            	for(var i =0;i<trObjs.length;i++){
            		ids.push($(trObjs[i]).children()[1].innerHTML);
            	}
            }
            
            //删除
            function deleteAdmin(id) {
                var r = window.confirm("确定要删除此管理员吗？");
                if(!r){
                	return;
                }
				$.post("deleteAdmin.action",
            		{"id":id},
            		function(data){
            			var pass = data;
            			var msg="";
            			if(pass){
            				//删除成功;
            				msg="删除成功！";
            			}else{
            				//删除失败;
            				msg="删除失败！";
            			}
            			//设置提示信息;
            			$("#operMsg").text(msg);
            			//显示提示信息块;
            			$("#operate_result_info").css("display","block");
            			//延迟1秒后做页面刷新,为了让提示信息块停留更久;
            			setTimeout(function(){
            			var page = $("#page").val();
            			toPage(page);
            			},1000);
            		});               
            }
            
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
                }
            }
            
            //退出/注销登录;
            function logout(){
            	var r = window.confirm("是否退出？");
            	if(!r)
            		return;
            	location.href="../login/logout.action";
            	//关闭页面;
            	//window.open('','_self',''); //加入这条语句后在关闭页面时就不会弹窗确认了;
            	//window.close();
            }
            
        </script>       
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="javascript:logout();">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../login/toHomePage.action" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_on"></a></li>
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
            <form action="findAdmin.action" id="findForm" method="post">
            	<s:hidden id="page" name="page" />
                <!--查询-->
                <div class="search_add">
                    <div>
                    	模块：			
                        <s:select name="privilegeId" list="privileges" listKey="id" listValue="name" id="selModules" cssClass="select_search"></s:select>
                    </div>
                    <div>
                    	角色：<!-- <input type="text" value="" class="text_search width200" /> -->
                    	<s:select name="roleId" list="roles" listKey="id" listValue="name" cssClass="select_search"></s:select>
                    </div>
                    <div><input type="button" value="搜索" onclick="toPage(1);" class="btn_search"/></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddAdmin.action';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span id="operMsg"></span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>
                        <s:iterator value="admins">
	                        <tr>
	                            <td><input type="checkbox" name="selectAdmin" /></td>
	                            <td><s:property value="id"/></td>
	                            <td><s:property value="name"/></td>
	                            <td><s:property value="adminCode"/></td>
	                            <td><s:property value="telephone"/></td>
	                            <td><s:property value="email"/></td>
	                            <td><s:property value="enrollDate"/></td>
	                            <td>
	                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
	                                	<s:property value="roleNames"/>
	                                </a>
	                                <!--浮动的详细信息-->
	                                <div class="detail_info">
	                                	<s:property value="roleNames"/>
	                                </div>
	                            </td>
	                            <td class="td_modi">
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateAdmin.action?id=<s:property value="id"/>';" />
	                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(<s:property value="id"/>);" />
	                            </td>
	                        </tr>
                        </s:iterator>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                    <s:if test="page==1">
        	        	<a href="javascript:void(0);">上一页</a>
        	        </s:if>
        	        <s:else>
        	        	<a href="javascript:toPage(<s:property value="page-1"/>);">上一页</a>
        	        </s:else>
        	        
        	        <s:iterator begin="1" end="totalPages" var="p">
        	        	<s:if test="#p==page">
        	        		<a href="javascript:toPage(<s:property value="#p"/>);" class="current_page"><s:property value="#p"/></a>
        	        	</s:if>
        	        	<s:else>
        	        		<a href="javascript:toPage(<s:property value="#p"/>);"><s:property value="#p"/></a>
        	        	</s:else>
        	        </s:iterator>
                    
                    <s:if test="page==totalPages">
        	        	<a href="javascript:void(0);">下一页</a>
        	        </s:if>
        	        <s:else>
        	        	<a href="javascript:toPage(<s:property value="page+1"/>);">下一页</a>
        	        </s:else>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
