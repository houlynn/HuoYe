Ext.define("core.base.102.view.MainLayout",{
	extend:"Ext.container.Container",
	alias : 'widget.102.mainlayout',
	layout : 'border',
	items : [{
		xtype:"102.levelTree",
		region:"west",
		width:comm.get("clientWidth")*0.18
	},{
		xtype:"gridModue",
		title:'用户信息',
		region:"center",
		
	}]
})