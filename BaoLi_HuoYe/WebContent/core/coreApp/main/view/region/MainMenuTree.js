/**
 * 树状菜单，显示在主界面的左边
 */
Ext.define('core.main.view.region.MainMenuTree', {
			extend : 'Ext.tree.Panel',
			alias : 'widget.mainmenutree',
			title : '系统菜单',
			listeners: { itemclick: function (view, node, item, index, e, eOpts) {
				var nodeInfo=node.get("nodeInfo");
	        	var config=nodeInfo.split(",");
	            coreApp.getController(config[1]);
	        	var  mainPanel= view.up('app-main');
            	var maincenter=mainPanel.down("maincenter");
				var panel=  Ext.create('Ext.panel.Panel', {
				            layout: 'fit',
				            title : node.data.text,
				            frame:true,
							closable : true,
							id:node.data.id,
							iconCls : 'icon-activity',
				              items: [
				                 {
				                     xtype: config[0],
				                 }
				             ] 
				         });
				     var  addPanel=maincenter.getComponent(node.data.id);
				     if(addPanel){
				    	return;
				     }
				     maincenter.add(panel);
				     maincenter.setActiveTab(panel);
		}
			},
			rootVisible : false,
			lines : false,
			initComponent : function() {
				this.store=comm.get("menuTreeStore");
				console.log(this.store);
			/*	this.store = Ext.create('Ext.data.TreeStore', {
							root : {
								text : '系统菜单',
								leaf : false,
								expanded : true
							}
						});
				var vm = this.up('app-main').getViewModel()
				var menus = vm.get('tf_MenuGroups');
				var root = this.store.getRootNode();
				for (var i in menus) {
					var menugroup = menus[i];
					var menuitem = root.appendChild({
								text : menugroup.tf_title,
								// 节点默认是否展开
								expanded : menugroup.tf_expand,
								icon : menugroup.tf_iconURL,
								glyph : menugroup.tf_glyph
							});
					for (var j in menugroup.tf_menuModules) {
						var menumodule = menugroup.tf_menuModules[j];

						var module = vm.getModuleDefine(menumodule.tf_ModuleId);
						if (module) {
							var childnode = {
								text : module.tf_title,
								moduleName:module.tf_moduleName,
								handler : 'onMainMenuClick',
								leaf : true,
							};
							menuitem.appendChild(childnode);
						}
					}
				}*/
				this.callParent(arguments);
			}
		})