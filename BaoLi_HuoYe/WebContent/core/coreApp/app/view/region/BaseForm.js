/**
 * 
 * 一个form窗口的基类，新增、显示、修改、审核、审批等功能继承了这个窗口
 * 
 */

Ext.define('core.app.view.region.BaseForm', {
			extend : 'Ext.form.Panel',
			alias : 'widget.baseform',
			autoScroll : true,
			buttonAlign : 'center',
			initComponent : function() {
				var items=[];
			  	items=Ext.apply(items,this,config);
				var me = this;
				this.buttons = [];
				this.buttons.push({
							text : '保存',
							itemId : 'save',
							glyph : 0xf0c7,
							handler : function(button){
								var form = button.up('form');
								button.up('form').updateRecord();
								button.up('form').getForm().getRecord().save();
							}
						},{
							text : '关闭',
							itemId : 'close',
							glyph : 0xf148,
							handler : function(button){
								button.up('window').hide();
							}
						});
				     me.items = items;
				     me.callParent(arguments);
			},
			initForm : function() {
			},
			setData : function(model) {
				this.data = model;
				if (this.data) {
					this.getForm().loadRecord(this.data);
				} else
					this.getForm().reset();
			}

		});