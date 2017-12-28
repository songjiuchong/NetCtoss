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
        	
        	function send(page){
            	document.getElementById("page").value=page;
            	document.getElementById("findRoleForm").submit();
            }
            
            function deleteRole(id) {
                var r = window.confirm("确定要删除此角色吗？");
                 if(!r){return;}
                 $.post("deleteRole.action",
            		{"id":id},
            		function(data){
            			var pass = data;
            			var msg="";
            			if(pass){
            				//删除成功;
            				msg="删除成功";
            			}else{
            				//删除失败;
            				msg="删除失败";
            			}
            			//设置提示信息;
            			$("#operMsg").text(msg);
            			//显示提示信息块;
            			$("#operate_result_info").css("display","block");
            			//延迟1秒后做页面刷新,为了让提示信息块停留更久;
            			setTimeout(function(){
            			var page = $("#page").val();
            			send(page);
            			},1000);
            		});               
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
            <form action="findRole.action" method="post" id="findRoleForm">
            	<s:hidden name="page" id="page" />
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddRole.action';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    	<div id="operMsg">删除成功！</div>
                 </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>                      
                        <s:iterator value="roles">
                        <tr>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td>
                            	
								<s:iterator value="privileges" status="stat">
									<!--
									<s:iterator value="top" id="inner">
										<s:property value="#inner.name"/>
									</s:iterator>
									-->
									<s:if test="#stat.count==1">
										<s:property value="name"/>
									</s:if>
									<s:else>
										、<s:property value="name"/>
									</s:else>
								</s:iterator>
							</td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateRole.action?id=<s:property value="id"/>';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteRole(<s:property value="id"/>);" />
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
        	        	<a href="findRole.action?page=<s:property value="page-1"/>">上一页</a>
        	        </s:else>
        	        
        	        <s:iterator begin="1" end="totalPages" var="p">
        	        	<s:if test="page==#p">
                    		<a href="findRole.action?page=<s:property value="#p"/>" class="current_page"><s:property value="#p"/></a>
                    	</s:if>
                    	<s:else>
                    		<a href="findRole.action?page=<s:property value="#p"/>"><s:property value="#p"/></a>
                    	</s:else>
                    </s:iterator>
                    
                    <s:if test="page==totalPages">
                    	<a href="javascript:void(0);">下一页</a>
                    </s:if>
                    <s:else>
        	        	<a href="findRole.action?page=<s:property value="page+1"/>">下一页</a>
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
