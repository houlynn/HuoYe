Ext.define("core.base.resident.view.FeeSettingGrid",{
extend:"Ext.grid.Panel",
alias:"widget.resident.feesettinggrid",
 tbar:[{xtype:'button',text:'一键清空',itemId:'clearBtn',iconCls:'table_remove'}],
	columns : [{
		width : 150,
		dataIndex:"itemName",
		text :'收费条目',
		align : 'center' ,
		renderer: Ext.util.Format.manytoOneFieldRenderer
	},
	{
		width : 150,
		dataIndex:"startdate",
		text :'开始日期',
		xtype : 'datecolumn',
		align : 'center',
		renderer : Ext.util.Format.dateRenderer
	},
		{
		width : 150,
		dataIndex:"enddate",
		text :'结束日期',
		xtype : 'datecolumn',
		align : 'center',
		renderer : Ext.util.Format.dateRenderer
	},
		{
		dataIndex:"itemId",
		text :'结束日期',
		hidden:true,
		align : 'center'
	}
	
	 ],
	store:"core.base.resident.store.SettingStore",
});