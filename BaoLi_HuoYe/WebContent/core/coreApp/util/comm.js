 /**静态变量声明*/
 var comm = Ext.create("Ext.util.MixedCollection");
 /**声明主控制器*/
 var coreApp=null;
 /**持久化登录用户信息*/
 Ext.Ajax.request({
 	url:"/rbacUser/getCurrentUser.action",
 	method:"POST",
	async:false,
	timeout:4000,
	success:function(response){
		data = Ext.decode(Ext.value(response.responseText,'{}'));
		if(data.success){
			comm.add("currentUser",data.obj);
		}
	}
 });
 /**表单必填项样式*/
 comm.add('required','<span style="color:red;font-weight:bold" data-qtip="必填项">*</span>');
 
var ajax = function(config) { 
		Ext.Ajax.request({
					url : config.url, 
					params : config.params, 
					method : 'post',
					callback : function(options, success, response) {
						config.callback(Ext.JSON.decode(response.responseText));
					}
				});
		return false;
	};