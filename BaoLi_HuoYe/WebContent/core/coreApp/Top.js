
/**
 * 系统主页的顶部区域，主要放置系统名称，菜单，和一些快捷按钮
 */
Ext.define('core.Top', {

			extend : 'Ext.toolbar.Toolbar',
			alias : 'widget.maintop', // 定义了这个组件的xtype类型为maintop

			style : 'background-color : #cde6c7',

			height : 40,

			items : [{
						xtype : 'image',
					}, {
						xtype : 'label',
						style : 'font-size:20px;color:blue;'
					}, {
						xtype : 'label',
						style : 'color:grey;',
					}, '->', ' ', ' ', {
						text : '首页',
						glyph : 0xf015,
					}, {
						text : '帮助',
						glyph : 0xf059
					}, {
						text : '关于',
						glyph : 0xf06a
					}, '->', '->', {
						text : '搜索',
						glyph : 0xf002
					},  {

						text : '注销',
						glyph : 0xf011
					}, {
						glyph : 0xf102,
						tooltip : '隐藏顶部和底部区域',
						disableMouseOver : true
					}]

		});