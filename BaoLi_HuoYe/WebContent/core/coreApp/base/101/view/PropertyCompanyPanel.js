Ext.define("core.base.101.view.PropertyCompanyPanel",{
	extend:"core.app.base.BasePanel",
	alias:"widget.base.propertyCompanyPanel",
	funCode:"propertyCompany_main",
	funData:{
	        action:"/base/101", //请求Action
	        whereSql:"",//表格查询条件
	        orderSql:"operatingTime",//表格排序条件
	        pkName:"tf_proid",
	        modelName:"com.model.hibernate.property.PropertyCompany",//实体全路径
	        tableName:"PropertyCompany",//表名
	        defaultObj:{enabled:"1"},//默认信息，用于表格添加的时候字段默认值
	        isChildren:false,//是否子功能
	        children:[{//子功能的配置
	        	funCode:"propertyCompanyitem_main"	        	
	        }],
	        //子功能信息
	        childFun:[],
	        parentCode:"propertyCompany_main",//主功能功能编码
	        connectFields:[{//关联字段
			mainFieldCode:"",//主功能字段名
			childFieldCode:"",//子功能字段名
			foreignKey:"foreignKey",//外键虚字段
			isQuery:true
			}]
	},
		items:[{
		xtype:"basecenterpanel",
				items:[{
					xtype:"basequerypanel",
					region:"north",
					items:[
			]
			},{
			xtype:"base.propertyCompanyGrid",
			region:"center"
		}]
	},{
	xtype:"base.propertyCompanyForm",
		hidden:true
	}]
});
