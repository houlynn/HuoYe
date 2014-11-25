Ext.define("core.base.102.controller.ResidentController",{
	extend:"Ext.app.Controller",
	mixins: {
		suppleUtil:"core.util.SuppleUtil",
		messageUtil:"core.util.MessageUtil",
		formUtil:"core.util.FormUtil",
		treeUtil:"core.util.TreeUtil",
		gridActionUtil:"core.util.GridActionUtil"
	},
	init:function(){
		var self=this
		//事件注册
		this.control({
			"container[xtype=102.levelTree]":{
				itemclick:function(treeview,record,item,index,e,eOpts){
					var tree=treeview.ownerCt;
					var treeDel=tree.down("button[ref=treeDel]");
					treeDel.setDisabled(false);
		/*			var store=userGrid.getStore();
					var proxy=store.getProxy();
					proxy.extraParams={
						whereSql:" and deptId in("+ids.join(",")+")"					
					};
					store.load();
					*/
				}
			},
			
			"container[xtype=102.levelTree] button[ref=treeIns]":{
				click:function(btn){
						var tree=btn.ownerCt.ownerCt;
						var commbox=tree.down("basecombobox[ref=vicombobox]");
						 var vid=commbox.getValue();
						 if(!vid){
						 	system.errorInfo("请选择小区再进行添加操作","错误提示");
						 	return;
						 }
	               Ext.MessageBox.prompt('设置楼宇', '请输入楼宇名称', function(btn, leveName) {
                         if(btn=="ok"){
         	             var params={vid:vid,leveName:leveName}
         	            var resObj=self.ajax({url:"/102/A001.action",params:params});
         	            var store=tree.getStore();
                     	var proxy=store.getProxy();
											proxy.extraParams.vid=vid;
											store.load();	
                       } });
           
				}
			},
			"container[xtype=102.levelTree] basecombobox[ref=vicombobox]":{
				 select:function(combo,record,opts) {  
				 	 var  vid=record[0].get("itemCode");
				 	 var tree= combo.ownerCt.ownerCt;
				     var store=tree.getStore();
				   	var proxy=store.getProxy();
											proxy.extraParams.vid=vid;
											store.load();	
											
				}
			},
			
			"container[xtype=102.levelTree] button[ref=treechildIns]":{
				click:function(btn){
					alert(0);
					var tree=btn.up("panel[xtype=rbac.depttree]");
					var records=tree.getSelectionModel().getSelection();
					if(records.length<=0){
						alert("请选中节点!");
						return;
					}
					var parent=records[0];
					var params={
						layer:parent.getDepth()+1,
						nodeInfo:"Department",
						parentId:parent.get("id"),
						nodeType:"LEAF"
					}
					var resObj=self.ajax({url:"/rbacDept/doSave.action",params:params});
					if(resObj.success){
						var deptObj=resObj.obj;
						var nodeObj={
							id:deptObj.deptId
						}
						params.parent=params.parentId;
						params.id=deptObj.deptId;
						params.leaf=true;
						parent.data.leaf=false;
						parent.data.expanded=true;
						parent.commit();
						var node=parent.appendChild(params);
						tree.selectPath(node.getPath())
						tree.fireEvent("itemclick",tree.getView(),node);	
					}
				}
			},
			"container[xtype=102.levelTree] button[ref=treeDel]":{
				click:function(btn){
				var tree=btn.up("container[xtype=102.levelTree]");
				var records=tree.getSelectionModel().getSelection();
			    if(records.length<=0){
				system.warnInfo('请选中节点！')
				return;
			    }
			 var params={
			 tf_leveId:records[0].get("id")
			 };
			    var resObj=self.ajax({url:"/102/D001.action",params:params});
			    var tree=btn.ownerCt.ownerCt;
				var commbox=tree.down("basecombobox[ref=vicombobox]");
				var vid=commbox.getValue();
			    var store=tree.getStore();
                var proxy=store.getProxy();
				proxy.extraParams.vid=vid;
			    store.load();	
			    
			    
			    
				}
			}
		});
	},
	views:[
	'core.base.102.view.MainLayout',
	'core.base.102.view.LevelTree'
	],
	stores:[
	'core.base.102.store.LevelStore'
	]
});