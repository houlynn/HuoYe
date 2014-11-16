<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/font-awesome.css">
<script type="text/javascript" src="<%=basePath%>extjs/ext-all-dev.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/module.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>extjs/resources/ext-theme-neptune/ext-theme-neptune-all-debug.css">


<script type="text/javascript" src="/extjs/ext-lang-zh_CN.js"></script>
<link rel="shortcut icon" href="/platform/login/images/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/extjs/resources/css/example.css" />
<link rel="stylesheet" type="text/css"
	href="/extjs/resources/css/TabScrollerMenu.css" />
<link rel="stylesheet" type="text/css"
	href="/extjs/resources/css/CheckHeader.css" />
<link rel="stylesheet" type="text/css" href="/MyDesktop/css/desktop.css" />
<link rel="stylesheet" type="text/css" href="/core/css/comm.css" />
<link rel="stylesheet" type="text/css" href="/core/css/icon.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/plugin/swfupload/UploadPanel.css" />
<link type="text/css" rel="stylesheet" href="styles/index.css">
<link type="text/css" rel="stylesheet" href="styles/module.css">
<link type="text/css" rel="stylesheet" href="styles/clearbutton.css">
<link type="text/css" rel="stylesheet" href="styles/toggleslide.css">
<link type="text/css" rel="stylesheet" href="styles/boxSelect.css">
<link type="text/css" rel="stylesheet" href="styles/data-view.css">
<link type="text/css" rel="stylesheet"
	href="extjs/ux/grid/css/GridFilters.css">
<link type="text/css" rel="stylesheet"
	href="extjs/ux/grid/css/RangeMenu.css">

<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugin/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/extjs/examples.js"></script>
<script type="text/javascript" src="/core/coreApp/util/overrideUtil.js"></script>
<script type="text/javascript" src="/core/loader.js"></script>
<script type="text/javascript" src="/core/coreApp/util/comm.js"></script>
<script type="text/javascript" src="/core/app.js"></script>
<link rel="stylesheet" href="/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="/kindeditor/plugins/code/prettify.js"></script>
<style type="text/css">
.addr-panel .x-grid-header-ct {
	border-width: 1px 0 0 0 !important;
}

，.addr-panel .x-panel-header {
	border-width: 0;
}
。
</style>
</head>
<body>
	<script type="text/javascript">
		Ext.Ajax.on('requestcomplete',
				function(conn, response, options) {
					if (response.getResponseHeader) {
						var sessionStatus = response
								.getResponseHeader("sessionstatus");
						if (typeof (sessionStatus) != "undefined") {
							Ext.MessageBox.show({
								title : '服务器超时',
								msg : '服务器登录超时，您需要重新登录系统。',
								buttons : Ext.MessageBox.OK,
								width : 250,
								icon : Ext.MessageBox.WARNING,
								fn : function() {
									var redirect = 'login.jsp';
									window.location = redirect
								}
							})
						}
					}
				});

		<!--加载分辨率大小-->
		var clientWidth = document.body.clientWidth;
		var clientHeight = document.body.clientHeight;
		var screenWidth = document.body.scrollWidth;
		var screenHeight = document.body.scrollHeight;
		var resolutionHeight = window.screen.height;
		var resolutionWidth = window.screen.width;
		comm.add("clientWidth", clientWidth);
		comm.add("clientHeight", clientHeight);
		comm.add("screenWidth", screenWidth);
		comm.add("screenHeight", screenHeight);
		comm.add("resolutionWidth", resolutionWidth);
		comm.add("resolutionHeight", resolutionHeight);
		//开启动态加载
		Ext.Loader.setConfig({
			enabled : true
		});

		Ext.Loader.setPath({
			'Ext.ux' : 'extjs/ux',
			'Ext.app' : 'extjs/app'
		});
		Ext.require([ 'Ext.app.Portlet', 'Ext.app.PortalColumn',
				'Ext.app.PortalPanel', 'Ext.app.PortalDropZone',
				'Ext.ux.TabReorderer', 'Ext.ux.TabCloseMenu' ]);
		Ext.setGlyphFontFamily('FontAwesome'); // 设置图标字体文件，只有设置了以后才能用glyph属性
		/* 	Ext.onReady(function() {
					var tab = Ext.create('Ext.tab.Panel', {
								activeTab : 0,
								enableTabScroll : true,
								animScroll : false,
								border : true,
								autoScroll : false,
								region : 'center',
								split : true,
								closable:false,
								bodyStyle : 'overflow-x:hidden; overflow-y:scroll;border-width:1px 0 1px 0',
								items : [{
									iconCls : 'icon-activity',
									title : '平台首页',
									xtype:'container',
									layout:'column',
									items : [{
											xtype : 'portalcolumn',
											columnWidth : 0.7,
							                items:[{ title: '新闻动态',height : 150,iconCls : 'icon-news' },
							                	{title: '最新通知',height : 150, iconCls : 'icon-notice' },
							                	{title: '业绩报表',height : 150, iconCls : 'icon-chart'}]
							            },{
							            	xtype : 'portalcolumn',
							            	columnWidth : 0.3,
							                items:[{ title: '功能链接', height : 150, iconCls : 'icon-link'},
							                	{title: '待办事项',height : 150,iconCls : 'icon-note' },
							                	{title: '邮件列表', height : 150,iconCls : 'icon-email-list'}]
							            }]
								}],
								plugins: [Ext.create('Ext.ux.TabReorderer'),
				        		  Ext.create('Ext.ux.TabCloseMenu',{
				        		  	closeTabText: '关闭面板',
				        		  	closeOthersTabsText: '关闭其他',
				        		  	closeAllTabsText: '关闭所有'
				        		  })]
							}); */
		/* 	var tree = Ext.create("Ext.panel.Panel", {
						region : 'west',
						title : "系统菜单",
						width : 250,
						iconCls : "icon-tree",
						autoScroll : false,
						layout : 'accordion',
						collapsible : true,
						layoutConfig : {
							animate : true
						},
						split : true
					}); */
		/* var title = Ext.create("Ext.panel.Panel", {
					height : 80,
					html : '保利后台信息管理系统',
					region : 'north',
					split : true,
					bbar : [{
						iconCls : 'icon-user',
						text : '<font color=red>'+comm.get("currentUser").username+'</font> '+comm.get("currentUser").deptName,
					},'-',{
						text : Ext.Date.format(new Date(),'Y年m月d日')
					},'->',{
						text : '退出',
						glyph : 0xf059,
						iconCls : 'icon-logout',
					    handler:function(){
					    	 Ext.MessageBox.confirm("确认框", "你确定要退出系统吗？", function(btn) {  
			        	    	 if("yes"==btn){
			        	    		 window.location.href="rbacUser/logout.action";
			        	    	 }
					    	 });
					    }	 
					}],
					bodyStyle : 'backgroud-color:#99bbe8;line-height : 50px;padding-left:20px;font-size:22px;color:#000000;font-family:黑体;font-weight:bolder;' +
							'background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(153,187, 232, 0.4) ), color-stop(50%, rgba(153, 187, 232, 1) ),color-stop(0%, rgba(153, 187, 232, 0.4) ) )'
				}); */
		/* 	Ext.create('Ext.container.Viewport',{
				layout : 'border',
				items : Ext.create("core.main.view.Main"),
				uses:["core.main.view.Main"],
				autoScroll : false,
				listeners : {
					afterrender : function(){
					//	Ext.getBody().mask('正在加载系统菜单....');
						//开启悬浮提示功能
						Ext.QuickTips.init();
						//创建应用程序的实例
						Ext.application({
							name:"core",//引用的名称
							scope :this,
					        appFolder : "core/coreApp",//应用的目录
							//加载控制器
							controllers: ['core.app.controller.MainController'],
							  launch: function() {
								    coreApp.initMenu();
							  }
					}); 
					}
				}
			});
		}); */

		/* 	function addTree(moduleData,coreApp,tree) {
			for (var i = 0; i < moduleData.length; i++) {
				tree.add(Ext.create("Ext.tree.Panel", {
							title : moduleData[i].text,
							iconCls : moduleData[i].iconCls,
							//useArrows: true,
							autoScroll : false,
							rootVisible : false,
							viewConfig : {
								loadingText : "正在加载..."
							},
							store :comm.add("menuTreeStore"),
							listeners : {
								afterlayout : function() {
									if (this.getView().el) {
										var el = this.getView().el;
										var table = el
												.down("table.x-grid-table");
										if (table) {
											table.setWidth(el.getWidth());
										}
									}
								}, 
						itemclick : function(view,node){
									 alert(0);
									if (node.isLeaf()) { //判断是否是根节点
										if(node.data.type === 'URL'){ //判断资源类型
											var panel = Ext.create('Ext.panel.Panel',{
												title : node.data.text,
												closable : true,
												iconCls : 'icon-activity',
												html : '<iframe width="100%" height="100%" frameborder="0" src="http://www.baidu.com"></iframe>'
											});
											tab.add(panel);
											tab.setActiveTab(panel);
										}else if(node.data.type === 'COMPONENT'){
											var panel = Ext.create(node.data.component,{
												title : node.data.text,
												closable : true,
												iconCls : 'icon-activity'
											});
											tab.add(panel);
											tab.setActiveTab(panel);
										} 
									}
								}
							}
						}));
				tree.doLayout();
			} 
		}*/
	</script>
</body>
</html>