/**
 * 一个模块的主控界面的容器，用来安放各个模块控件以及协调他们之间的关系
 */
Ext.define('core.app.module.Module', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.modulepanel',
			requires : ['core.app.module.factory.ModelFactory'],
			uses : ['core.app.view.region.Navigate', 'core.app.view.region.Grid',
					'core.app.view.region.Detail', 'core.app.view.region.BaseWindow'],
			referenceHolder : true,
			layout : 'border', // 模块采用border布局
			initComponent : function() {
				console.log(this.moduleName + ' 正在创建');
				// 从MainModel中取得当前模块的定义数据，包括字段和各种设置的信息
				var viewModel=this.viewModel;
				this.setTitle(viewModel.get("tf_title"));//设置标题
				this.glyph = this.viewModel.get('tf_glyph'); // 由于上面的glyph的bind无效，因此需要在这里加入glyph的设置
				this.model = core.app.module.factory.ModelFactory.getModelByModule(this.viewModel.data);
				this.store = Ext.create('core.app.store.GridStore', {
							module : this.viewModel.data,
							model : this.model,
							modulePanel : this
						});
				this.items = [{
							xtype : 'navigate', // 导航区域
							region : 'west',
							width : 250,
							collapsible : true,
							collapsed : true,
							split : true
						}, {
							xtype : 'modulegrid', // 模块的grid显示区域
							region : 'center',
							store : this.store
						}, {
							xtype : 'recorddetail', // 记录明细
							region : 'east',
							width : 250,
							collapsible : true, // 可以折叠隐藏
							collapsed : true,
							collapseMode : 'mini', // 折叠陷藏模式
							split : true
							// 可以拖动大小
					}]

				this.callParent();
			}

		})