Ext.define("core.prop.fees.controller.FeesController",{
	extend:"Ext.app.Controller",
	mixins: {
		suppleUtil:"core.util.SuppleUtil"
	},
init:function(){
		var self=this
		//事件注册
	this.control({
			"container[xtype=fees.gridModue] button[ref=addButton]":{
							click : function (btn){
							 var modulegrid = btn.up("grid[xtype=fees.gridModue]");	
							 var store=modulegrid.getStore();
			                 var tree=modulegrid.ownerCt.down("container[xtype=fees.levelTree]");
			                 var selection=tree.getSelectionModel().getSelection();
			                 if(!selection&&selection.length==0){
			                  return ;
			                 }else{
			                    if(selection[0].get("nodeInfoType")=="0"||selection[0].get("nodeInfoType")=="1"){
			                    system.errorInfo("请选择对应的房号在进行添加","错误提示");
			                    return ;
			                   }
			                 }
							 var viewModel=system.getViewModel(201);
                	         if(!store.navigates||store.navigates.length==0){
                	            system.errorInfo("请选择对应的房号在进行添加","错误提示");
                	         	return;
                	          }
							 
						     var model = Ext.create(modulegrid.getStore().model);
			                 model.set(model.idProperty, null); // 设置主键为null,可自动
			                 var  window=  Ext.createWidget("fees.window",{
			                   viewModel:viewModel,
				                grid:modulegrid
			                 });
			                    window.down('form[xtype=baseform]').setData(model);
			                    var title=selection[0].get("text")+" 水表信息录入";
			                    window.setTitle(title);
	                            window.show();
								}, // 这里不要用handler，而要用click,因为下面要发送click事件
								// 删除按钮在渲染后加入可以Drop的功能
								render : function(btn) {
									// 可以使Grid中选中的记录拖到到此按钮上来进行复制新增
									var modulegrid= btn.up("resident.gridModue");
									btn.dropZone = new Ext.dd.DropZone(btn.getEl(), {
												// 此处的ddGroup需要与Grid中设置的一致
												ddGroup : 'DD_grid_' + viewModel.get('tf_moduleName'),
												getTargetFromEvent : function(e) {
													return e.getTarget('');
												},
												// 用户拖动选中的记录经过了此按钮
												onNodeOver : function(target, dd, e, data) {
													return Ext.dd.DropZone.prototype.dropAllowed;
												},
												// 用户放开了鼠标键，删除记录
												onNodeDrop : function(target, dd, e, data) {
													var b = btn.menu.down('#newwithcopy');
													b.fireEvent('click', b);
												}
											})
								}
				},
				
			"container[xtype=fees.gridModue]  button[ref=editButton] ":{
		   click:function(btn){
			var modulegrid = btn.up("grid[xtype=fees.gridModue]");	
			var viewModel=modulegrid.viewModel;
			var window = Ext.create('core.app.view.region.BaseWindow', {
				viewModel:viewModel,
				grid:modulegrid
			});
	       window.down('baseform').setData(modulegrid.getSelectionModel().getSelection()[0]);
	       window.show();
				}
			},	
			
		"container[xtype=fees.gridModue]  button[ref=removeButton] ":{
			click:function(btn){
			var modulegrid=btn.up("grid[xtype=fees.gridModue]");
			var module=modulegrid.viewModel;
			var selection=modulegrid.getSelectionModel().getSelection();
			var message='';
			var infoMessage='';
			if (selection.length == 1) { // 如果只选择了一条
				message = ' 『' + selection[0].getNameValue() + '』 吗?';
				infoMessage = '『' + selection[0].getNameValue() + '』';
			} else { // 选择了多条记录
				message = '<ol>';
				Ext.Array.each(selection, function(record) {
							message += '<li>' + record.getNameValue() + '</li>';
						});
				message += '</ol>';
				infoMessage = message;
				message = '以下 ' + selection.length + ' 条记录吗?' + message;
			}
			var moduletitle = '<strong>' + module.get('tf_title')
					+ '</strong>';
			Ext.MessageBox.confirm('确定删除', '确定要删除 ' + moduletitle + ' 中的' + message,
					function(btn) {
						if (btn == 'yes') {
							modulegrid.getStore().remove(modulegrid.getSelectionModel().getSelection());
							modulegrid.getStore().sync();
							 Ext.toast({
										title : '删除成功',
										html : moduletitle + infoMessage + '已成功删除！',
										bodyStyle : 'background-color:#7bbfea;',
										header : {
											border : 1,
											style : {
												borderColor : '#9b95c9'
											}
										},
										border : true,
										style : {
											borderColor : '#9b95c9'
										},
										saveDelay : 10,
										align : 'tr',
										closable : true,
										minWidth : 200,
										maxheight:250,
										useXAxis : true,
										slideInDuration : 500
									});
						}
					})
					
				},
					render : function(button) {
									// 可以使Grid中选中的记录拖到到此按钮上来进行删除
									button.dropZone = new Ext.dd.DropZone(button.getEl(), {
												// 此处的ddGroup需要与Grid中设置的一致
												ddGroup : 'DD_grid_' + viewModel.get('tf_moduleName'),
												// 这个函数没弄明白是啥意思,没有还不行
												getTargetFromEvent : function(e) {
													return e.getTarget('');
												},
												// 用户拖动选中的记录经过了此按钮
												onNodeOver : function(target, dd, e, data) {
													return Ext.dd.DropZone.prototype.dropAllowed;
												},
												// 用户放开了鼠标键，删除记录
												onNodeDrop : function(target, dd, e, data) {
													button.fireEvent('click', button); // 执行删除按钮的click事件
												}
											})
								}
				},
				
			"container[xtype=fees.levelTree]":{
				itemclick:function(treeview,node,item,index,e,eOpts){
					var tree=treeview.ownerCt;
					var gridModue=treeview.ownerCt.ownerCt.down("grid[xtype=fees.gridModue]");
					var modue=system.getModuleDefine(node.raw.nodeInfo);
					var nodeInfoType=node.raw.nodeInfoType;
					var navigate={
                			moduleName:node.raw.nodeInfo,
                			tableAsName:"_t"+modue.tf_moduleId,
                			text:node.raw.text,
                			primarykey:modue.tf_primaryKey,
                		    fieldtitle:node.raw.description,
                		    equalsValue:node.raw.code,
                		    isCodeLevel:false
                	};
                	var store=gridModue.store;
                	if(store.navigates){
                		store.navigates.splice(0,store.navigates.length);
                		store.navigates.push(navigate);
                	}
                  	var proxy=store.getProxy();
                  	console.log(proxy.extraParams);
                    proxy.extraParams.nodeInfoType=nodeInfoType;
					proxy.extraParams.navigates=Ext.encode(store.navigates);
					store.load();	  
				}
			},
			"container[xtype=fees.levelTree] basecombobox[ref=vicombobox]":{
				 select:function(combo,record,opts) {  
				 	 var  vid=record[0].get("itemCode");
				 	 var tree= combo.ownerCt.ownerCt;
			        var store=tree.getStore();
				   	var proxy=store.getProxy();
											proxy.extraParams.vid=vid;
											store.load();	
											
				}
			}
		  


		});
	},
	views:[
	'core.prop.fees.view.MainLayout',
	'core.prop.fees.view.LevelTree',
	"core.prop.fees.view.FeesGrid",
	"core.prop.fees.view.FeeWinodw"
	],
	stores:[
	'core.prop.fees.store.LevelStore',
	],
    models : []
});