Ext.define("core.rbac.user.view.CenterLayout",{
	extend:"Ext.container.Container",
	alias : 'widget.rbac.centerlayout',
	layout:"border",
	items : [{
		xtype:"rbac.deptform",
		region:"north",
		height:comm.get("resolutionHeight")*0.2,
		hidden:true
	},{
		xtype:"rbac.userlayout",
		region:"center"
	}]
})