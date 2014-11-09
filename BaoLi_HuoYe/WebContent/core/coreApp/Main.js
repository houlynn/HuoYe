/**
 * This class is the main view for the application. It is specified in app.js as
 * the "autoCreateViewport" property. That setting automatically applies the
 * "viewport" plugin to promote that instance of this class to the body element.
 * 
 * TODO - Replace this content of this view to suite the needs of your
 * application.
 */
Ext.define('core.Main', {
			extend : 'Ext.window.Window',
			xtype : 'app-main',
			width:300,
			height:200,
		    background:"green",
			uses : [ 'core.Top', ],
			initComponent : function() {
				Ext.setGlyphFontFamily('FontAwesome'); // 设置图标字体文件，只有设置了以后才能用glyph属性
				this.callParent();
			},
			   tbar: [
			          { text: "保存", glyph : 0xf059},
			          { text: "新建", iconCls: "qicon-add" }
			      ],
			layout : {
				type : 'border' // 系统的主页面的布局
			},

			items : [
					{
						xtype:"panel",
						region : 'center',
					}
					]
		});
