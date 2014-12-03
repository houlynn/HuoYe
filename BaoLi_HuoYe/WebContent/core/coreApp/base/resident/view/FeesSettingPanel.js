Ext.define("core.base.resident.view.FeesSettingPanel",{
	extend:"Ext.container.Container",
	alias:"widget.resident.feesettingpanel",
	layout:"border",
		items : [{
		region:"north",
		xtype:"resident.feesettingfrom"
	},{
		region:"center",
		xtype:"resident.feesettinggrid"
	}]
});