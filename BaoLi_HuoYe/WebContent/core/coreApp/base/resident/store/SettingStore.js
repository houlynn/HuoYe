Ext.define("core.base.resident.store.SettingStore",{
	 	extend : 'Ext.data.Store',
	  	 model:factory.ModelFactory.getModelByName("com.ufo.framework.system.model.ui.JSONTreeNode","checked").modelName,
	  });