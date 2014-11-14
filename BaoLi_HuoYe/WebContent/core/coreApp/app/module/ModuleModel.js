Ext.define('core.app.module.ModuleModel', {
	data : {
	tf_moduleId : null, // 模块ID号：一个数字的ID号，可以根据此ID号的顺序将相同分组的模块放在一块。
	tf_ModuleGroup : null,// 模块分组：模块分到哪个组里，比如说业务模块1、业务模块2、系统设置、系统管理等。
	tf_moduleName : null, // 模块标识：系统中唯一的模块的标识
	tf_title : null,// 模块名称：能够描述此模块信息的名称。
	tf_glyph : null, // 图标字符值
	tf_shortname : null,// 模块简称：如果名称过长，有些地方可以用简称来代替。
	tf_englishName : null,// 模块英文名称：万一要制作英文版，可以用英文名称。
	tf_englishShortName : null, // 模块英文简称：可以用作生成编码字段。
	tf_description : null,// 模块描述：
	tf_remark : null,
	// 备注：
	// 下面还有若干字段未加入，以后用到的时候再加入
	tf_primaryKey : null, // 主键
	tf_nameFields : null, // 可用于描述记录的字段
	// 此模块的自定义字段，此处先用手工定义，以后换成从数据库中自动取得
	tf_fields : [],
	// 模块的grid方案，可以定义多个方案
	tf_gridSchemes : [{
				tf_schemeOrder : 10,
				tf_schemeName : 'Grid方案1', // 第一个grid方案
				// 表头分组
				tf_schemeGroups : [{
							tf_gridGroupId : 1, // id号
							tf_gridGroupOrder : 10, // 表头分组序号
							tf_gridGroupName : '工程项目基本信息',
							tf_isShowHeaderSpans : true, // 是否显示分组
							tf_isLocked : true, // 是否锁定此分组
							// 每一个表头分组下面的字段
							tf_groupFields : [{
								tf_gridFieldOrder : 5,
								tf_fieldId : 10100010
									// 工程id号
								}, {
								tf_gridFieldOrder : 10,
								tf_fieldId : 10100020, // 工程项目名称字段
								tf_columnWidth : 200
							}, {
								tf_gridFieldOrder : 20,
								tf_fieldId : 10100030, // 工程项目编码字段
								tf_columnWidth : 120
							}]
						}, {
							tf_gridGroupOrder : 20, // 表头分组序号
							tf_gridGroupName : '工程项目附加信息',
							tf_isShowHeaderSpans : false, // 是否显示headerspan
							tf_isLocked : false, // 是否锁定此分组
							tf_groupFields : [{
								tf_gridFieldOrder : 10,
								tf_fieldId : 10100040
								}, {
								tf_gridFieldOrder : 20,
								tf_fieldId : 10100050
								}, {
								tf_gridFieldOrder : 30,
								tf_fieldId : 10100060
								}, {
								tf_gridFieldOrder : 40,
								tf_fieldId : 10100070
								}, {
								tf_gridFieldOrder : 50,
								tf_fieldId : 10100080
								}, {
								tf_gridFieldOrder : 60,
								tf_fieldId : 10100090, // 是否通过验收
								tf_columnWidth : 80
							}, {
								tf_gridFieldOrder : 70,
								tf_fieldId : 10100100,
								tf_columnWidth : -1,  // －1表示这我可以撑足最大宽度
								tf_autoSizeDisabled : true // 不许自动适应宽度
									// 工程方量
								}]
						}]

			}, {
				tf_schemeOrder : 20,
				tf_schemeName : 'Grid方案2', // 第二个grid方案
				tf_schemeGroups : [{
							tf_gridGroupId : 1, // id号
							tf_gridGroupOrder : 10, // 表头分组序号
							tf_gridGroupName : '工程项目主要信息',
							tf_isShowHeaderSpans : false, // 是否显示分组
							tf_isLocked : false, // 是否锁定此分组
							tf_groupFields : [{
								tf_gridFieldOrder : 5,
								tf_fieldId : 10100010,
								tf_isLocked : true
								}, {
								tf_gridFieldOrder : 10,
								tf_fieldId : 10100020, // 工程项目名称字段
								tf_columnWidth : 200,
								tf_isLocked : true
							}, {
								tf_gridFieldOrder : 20,
								tf_fieldId : 10100030, // 工程项目编码字段
								tf_columnWidth : 120,
								tf_isLocked : true

							}, {
								tf_gridFieldOrder : 10,
								tf_fieldId : 10100040
								}, {
								tf_gridFieldOrder : 20,
								tf_fieldId : 10100050
								}]
						}]
			}, {
				tf_schemeOrder : 30,
				tf_schemeName : 'Grid方案3', // 第三个grid方案
				// 表头分组
				tf_schemeGroups : [{
							tf_gridGroupId : 1, // id号
							tf_gridGroupOrder : 10, // 表头分组序号
							tf_gridGroupName : '工程项目基本信息',
							tf_isShowHeaderSpans : true, // 是否显示分组
							tf_isLocked : true, // 是否锁定此分组
							tf_groupFields : [{
										tf_gridFieldOrder : 10,
										tf_fieldId : 10100020, // 工程项目名称字段
										tf_columnWidth : 200
									}, {
										tf_gridFieldOrder : 20,
										tf_fieldId : 10100030, // 工程项目编码字段
										tf_columnWidth : 120
									}]
						}, {
							tf_gridGroupOrder : 20, // 表头分组序号
							tf_gridGroupName : '工程项目附加信息',
							tf_isShowHeaderSpans : true, // 是否显示headerspan
							tf_isLocked : false, // 是否锁定此分组
							tf_groupFields : [{
								tf_gridFieldOrder : 10,
								tf_fieldId : 10100040
								}, {
								tf_gridFieldOrder : 20,
								tf_fieldId : 10100050
								}, {
								tf_gridFieldOrder : 30,
								tf_fieldId : 10100060
								}, {
								tf_gridFieldOrder : 40,
								tf_fieldId : 10100070
								}, {
								tf_gridFieldOrder : 50,
								tf_fieldId : 10100080
								}]
						}]

			}],
			// 模块的form方案，可以定义多个方案
			tf_formSchemes : [{
						tf_schemeOrder : 10,
						tf_schemeName : 'form方案1', // 第一个form方案
						tf_windowWidth : 600, // form window 的宽度
						tf_windowHeight : -1, // 高度-1 即为自动适应高度
						// 表头分组
						tf_schemeGroups : [{
									tf_formGroupId : 1, // id号
									tf_formGroupOrder : 10, // 表头分组序号
									tf_formGroupName : '工程项目基本信息',
									tf_numCols : 1, // 分栏数
									// 每一个表头分组下面的字段
									tf_groupFields : [{
										tf_formFieldOrder : 5,
										tf_fieldId : 10100010,
										tf_colspan : 1, // 此字段占用的栏数
										tf_width : -1,// 宽度，设置-1为 100%
										tf_isEndRow : true
											// 结束本行，下个字段从新的一行开始排列
										}, {
										tf_formFieldOrder : 10,
										tf_fieldId : 10100020, // 工程项目名称字段
										tf_colspan : 1, // 此字段占用的栏数
										tf_width : -1,// 宽度，设置-1为 100%
										tf_isEndRow : true
											// 结束本行，下个字段从新的一行开始排列
										}, {
										tf_formFieldOrder : 20,
										tf_fieldId : 10100030, // 工程项目编码字段
										tf_colspan : 1, // 此字段占用的栏数
										tf_width : -1,// 宽度，设置-1为 100%
										tf_isEndRow : true
											// 结束本行，下个字段从新的一行开始排列
										}]
								}, {
									tf_formGroupOrder : 20, // 表头分组序号
									tf_formGroupName : '工程项目附加信息',
									tf_numCols : 2, // 分栏数
									tf_collapsible : true, // 此fieldSet可折叠
									tf_collapsed : false, // 默认不折叠
									// 每一个表头分组下面的字段
									tf_groupFields : [{
										tf_formFieldOrder : 10,
										tf_fieldId : 10100040
											// 建筑面积
										}, {
										tf_formFieldOrder : 20,
										tf_fieldId : 10100050
											// 投资总额
										}, {
										tf_formFieldOrder : 30,
										tf_fieldId : 10100060,
										tf_isEndRow : true
											// 结束本行，下个字段从新的一行开始排列
											// 容积率
										}, {
										tf_formFieldOrder : 40,
										tf_fieldId : 10100070
											// 计划开工时间
										}, {
										tf_formFieldOrder : 50,
										tf_fieldId : 10100080
											// 计划竣工时间
										}, {
										tf_formFieldOrder : 60,
										tf_fieldId : 10100090
											// 是否通过验收
										}, {
										tf_formFieldOrder : 70,
										tf_fieldId : 10100100
											// 工程方量
										}]
								}]

					}]
},
	formulas : {
		// 模块grid方案的选择Combo是否显示
		gridSchemeHidden : function(get) {
			return this.get('tf_gridSchemes').length <= 1;
		}

	},
	// 根据字段id,找到字段相应的定义
	getFieldDefine : function(fieldId) {
		var result = null;
		Ext.Array.each(this.data.tf_fields, function(field) {
					if (field.tf_fieldId == fieldId) {
						result = field;
						return false;
					}
				});
		return result;
	},
	get:function(key){
		return this.data[key];
	},
	set:function(key,value){
		 this.data[key]=value;
	}
});