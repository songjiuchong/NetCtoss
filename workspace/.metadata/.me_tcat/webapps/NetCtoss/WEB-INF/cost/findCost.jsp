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
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee() {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            
            //删除
            function deleteFee(id) {
                var r = window.confirm("确定要删除此资费吗");
                 if(!r){return;}
                 $.post("deleteCost.action",
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
            			document.getElementById("findCostForm").submit();
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
			<span>当前账号：<b>scott</b></span>
            <a href="javascript:logout();">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../login/toHomePage.action" class="index_off"></a></li>
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
            <form action="findCost.action" method="post" id="findCostForm" >
            	<s:hidden id="page" name="page"/>
                <!--排序-->
                <div class="search_add">
                    <div>
                        <input type="button" value="月租" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddCost.action';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <div id="operMsg"></div>
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>    
                        <s:iterator value="costList">              
	                        <tr>
	                            <td><s:property value="id"/></td>
	                            <td><a href="fee_detail.html"><s:property value="name"/></a></td>
	                            <td><s:property value="baseDuration"/></td>
	                            <td><s:property value="baseCost"/></td>
	                            <td><s:property value="unitCost"/></td>
	                            <td><s:property value="createTime"/></td>
	                            <td><s:property value="startTime"/></td>
	                            <td>
									<s:if test="status==0">开通</s:if>
									<s:else>暂停</s:else>
								</td>
	                            <td>                                
	                                <input type="button" value="启用" class="btn_start" onclick="startFee();" />
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateCost.action?id=${id}';"/>
	                                <input type="button" value="删除" class="btn_delete" onclick='deleteFee(<s:property value="id"/>);'/>
	                            </td>
	                        </tr>
                        </s:iterator>
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                	<s:if test="page==1">
                		<a href="#">上一页</a>
                	</s:if>
                	<s:else>
                		<a href="findCost.action?page=<s:property value="page-1"/>">上一页</a>
                	</s:else>
        	        <s:iterator begin="1" end="totalPage" var="p">
        	        	<s:if test="#p==page">
        	        		<a href="findCost.action?page=<s:property/>" class="current_page"><s:property/></a>
        	        	</s:if>
        	        	<s:else>
        	        		<a href="findCost.action?page=<s:property value="#p"/>"><s:property value="#p"/></a>
        	        	</s:else>
                    </s:iterator>
                	<s:if test="page==totalPage">
                		<a href="#">下一页</a>
                	</s:if>
                	<s:else>
                		<a href="findCost.action?page=<s:property value="page+1"/>">下一页</a>
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
