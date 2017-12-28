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
        
            //分页
            function toPage(page){
            	//把当前页page记录到hidden中;
            	document.getElementById("page").value=page;
            	//提交表单;
            	document.getElementById("findForm").submit();
            }
            
            //开通状态;
            function startService(serviceId,accountId){
            	var r = window.confirm("确定要开通此业务账号吗？");
            	if(!r) return;
            	$.post(
            		"startService.action",
            		{"serviceId":serviceId,"accountId":accountId},
            		function(data){
            			var msg = data;
            			$("#operMsg").text(msg);

            			//显示提示信息块;
            			$("#operate_result_info").css("display","block");
            			//延迟1.5秒后做页面刷新,为了让提示信息块停留更久;
            			setTimeout(function(){
            			var page = $("#page").val();
            			toPage(page);
            			},1000);
            		}
            	);
            }
            
            //删除
            function deleteService(serviceId){
                var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
                if(!r){return;}
             	$.post("deleteService.action",
            		{"serviceId":serviceId},
            		function(data){
            			var pass = data;
            			var msg="";
            			if(pass){
            				//删除成功;
            				msg="删除成功！"
            			}else{
            				//删除失败;
            				msg="删除失败！"
            			}
            			//设置提示信息;
            			$("#operMsg").text(msg);
            			//显示提示信息块;
            			$("#operate_result_info").css("display","block");
            			//延迟1.5秒后做页面刷新,为了让提示信息块停留更久;
            			setTimeout(function(){
            			var page = $("#page").val();
            			toPage(page);
            			},1000);
            		});               
            }
            
            //暂停状态;
            function pauseService(serviceId){
            	var r = window.confirm("确定要暂停此账务账号吗？");
            	if(!r) return;
            	$.post(
            		"pauseService.action",
            		{"serviceId":serviceId},
            		function(data){
            			var pass = data;
            			var msg="";
            			
            			if(pass){
            				//暂停成功;
            				msg="暂停成功！"
            			}else{
            				//暂停失败;
            				msg="暂停失败！"
            			}
            			//设置提示信息;
            			$("#operMsg").text(msg);
            			//显示提示信息块;
            			$("#operate_result_info").show();
            			//延迟1.5秒后做页面刷新,为了让提示信息块停留更久;
            			setTimeout(function(){
            			var page = $("#page").val();
            			toPage(page);
            			},1000);
            		});     
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
            <form action="findService.action" method="post" id="findForm">
             <s:hidden name="page" id="page"/>
                <!--查询-->
                <div class="search_add">                        
                    <div>
                    	OS 账号：<s:textfield name="osUserName" cssClass="width100 text_search" />
                    </div>                            
                    <div>
                    	服务器 IP：<s:textfield name="unixHost" cssClass="width100 text_search" />
                    </div>
                    <div>
                    	身份证：<s:textfield name="idcardNo" cssClass="text_search" />
                    </div>
                    <div>
                   		状态：<s:select name="status" list="#{'-1':'全部','0':'开通','1':'暂停','2':'删除'}" cssClass="select_search"></s:select>
                    </div>
                    <div><input type="button" value="搜索" class="btn_search" onclick="toPage(1)"/></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddService.action';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span id="operMsg"></span>
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <s:iterator value="serviceVos">
	                    <tr>
	                        <td><a href="toServiceDetail.action?serviceId=<s:property value="serviceId"/>" title="查看明细"><s:property value="serviceId"/></a></td>
	                        <td><s:property value="accountId"/></td>
	                        <td><s:property value="idcardNo"/></td>
	                        <td><s:property value="realName"/></td>
	                        <td><s:property value="osUsername"/></td>
	                        <td>
								<s:if test="status==0">开通</s:if>
								<s:if test="status==1">暂停</s:if>
								<s:if test="status==2">删除</s:if>
							</td>
	                        <td><s:property value="unixHost"/></td>
	                        <td>
	                            <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);"><s:property value="costName"/></a>
	                            <!--浮动的详细信息-->
	                            <div class="detail_info">
	                                <s:property value="descr"/>
	                            </div>
	                        </td>                            
	                        <td class="td_modi">
		                        <!--暂停态显示的按钮-->
		                        <s:if test="status==0">
			                       	<input type="button" value="暂停" class="btn_pause" onclick="pauseService(<s:property value="serviceId"/>);" />
			                        <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateService.action?id=<s:property value="serviceId"/>';" />
			                        <input type="button" value="删除" class="btn_delete" onclick="deleteService(<s:property value="serviceId"/>);" />
		                        </s:if>
		                        <!--开通态显示的按钮-->
		                        <s:if test="status==1">
		                          	<input type="button" value="开通" class="btn_start" onclick="startService(<s:property value="serviceId"/>,<s:property value="accountId"/>);"/>
			                        <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateService.action?id=<s:property value="serviceId"/>';" />
			                        <input type="button" value="删除" class="btn_delete" onclick="deleteService(<s:property value="serviceId"/>);" />
		                        </s:if>
		                        <!--删除态显示的按钮-->
		                        <s:if test="status==2">
		                        </s:if>
	                        </td>
	                    </tr>
                   </s:iterator>
                </table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
                <!--分页-->
                <div id="pages">
                    <a href="javascript:toPage(1);">首页</a>
                    
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
        	        
                    <a href="javascript:toPage(<s:property value="totalPages"/>);">末页</a>
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
