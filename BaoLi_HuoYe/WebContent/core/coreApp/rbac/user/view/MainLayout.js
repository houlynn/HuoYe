Ext.define("core.rbac.user.view.MainLayout",{
	extend:"Ext.container.Container",
	alias : 'widget.rbac.mainlayout',
	layout : 'border',
	frame:true,
	baseCls:"panel-border",
	bodyPadding : '0 0 0 0',
	bodyStyle: 'border-width:1px 0 1px 0',
	items : [{
		xtype:"rbac.depttree",
		region:"west",
		width:comm.get("clientWidth")*0.25,
		hidden:true
	},{
		xtype:"rbac.centerlayout",
		region:"center"
	}]
})