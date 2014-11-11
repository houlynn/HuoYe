Ext.define("core.base.101.view.PropertyCompanyGrid", {
	extend : "core.app.base.BaseGrid",
	alias : "widget.base.propertyCompanyGrid",
	tbar:[
			{xtype:'button',text:'添加',ref:'gridInsert',iconCls:'table_add'},
			{xtype:'button',text:'删除',ref:'gridDelete',iconCls:'table_remove'},
			{xtype:'button',text:'保存',ref:'gridSave',iconCls:'table_save'}
		],
	columns : [{
		xtype:"rownumberer",
		width : 35,
		text :'No.',
		align : 'center'
	},{
		text:"主键",
		dataIndex:"tf_proid",
		hidden:true
	}
, {
		text:"物业公司名称",
		dataIndex:"tf_name",
		width : 120,
		 columnType:"textfield",
		field:{
			 xtype:"textfield",
		beforeLabelTextTpl : comm.get('required'),
		emptyText :'物业公司名称必填',
		allowBlank : false,
		  hideTrigger : false
		}
	}
, {
		text:"法人代表",
		dataIndex:"tf_corporate",
		width : 120,
		 columnType:"textfield",
		field:{
			 xtype:"textfield",
		allowBlank : true,
		  hideTrigger : false
		}
	}
, {
		text:"联系人",
		dataIndex:"tf_contact",
		width : 120,
		 columnType:"textfield",
		field:{
			 xtype:"textfield",
		allowBlank : true,
		  hideTrigger : false
		}
	}
, {
		text:"联系电话",
		dataIndex:"tf_phone",
		width : 120,
		 columnType:"textfield",
		field:{
			 xtype:"textfield",
		allowBlank : true,
		  hideTrigger : false
		}
	}
, {
		text:"联系地址",
		dataIndex:"tf_address",
		width : 120,
		 columnType:"textfield",
		field:{
			 xtype:"textfield",
		allowBlank : true,
		  hideTrigger : false
		}
	}
	
	 ],
	store:"core.base.101.store.PropertyCompanyStore",
	bbar:{
		xtype:'pagingtoolbar',
		store:"core.base.101.store.PropertyCompanyStore",
		dock:'bottom',
		displayInfo:true
	}
});