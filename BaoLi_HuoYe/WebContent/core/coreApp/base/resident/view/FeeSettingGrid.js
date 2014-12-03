Ext.define("core.base.resident.view.FeeSettingGrid",{
extend:"Ext.grid.Panel",
alias:"widget.resident.feesettinggrid",
	

	columns : [{
		xtype:"rownumberer",
		width : 35,
		text :'No.',
		align : 'center'
	}
	
	 ],
	store:"core.base.resident.store.SettingStore",
	bbar:{
		xtype:'pagingtoolbar',
		store:"core.base.resident.store.SettingStore",
		dock:'bottom',
		displayInfo:true
	}
});