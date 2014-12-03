Ext.define("core.base.resident.store.SettingStore",{
	 	extend : 'Ext.data.Store',
	  	 model:factory.ModelFactory.getModelByName("com.property.base.vo.FeeSettingInfo").modelName,
	  });