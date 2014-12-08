Ext.define("ore.prop.fees.view.MainLayout",{
	extend:"Ext.container.Container",
	alias : 'widget.fees.mainlayout',
	layout : 'border',
	items : [{
		xtype:"fess.levelTree",
		region:"west",
		width:comm.get("clientWidth")*0.18
	},{
		xtype:"fess.gridModue",
		title:'抄表信息',
		region:"center",
		
	}]
})