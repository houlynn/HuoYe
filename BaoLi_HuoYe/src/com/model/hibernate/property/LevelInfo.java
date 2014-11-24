package com.model.hibernate.property;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.ufo.framework.annotation.FieldInfo;
import com.ufo.framework.annotation.NodeType;
import com.ufo.framework.annotation.TableInfo;
import com.ufo.framework.common.core.ext.TreeNodeType;
import com.ufo.framework.common.core.properties.PropUtil;
import com.ufo.framework.common.model.BaseEntity;

/**
 * 楼宇层次
* @author 作者 HouLynn: 
* @version 创建时间：2014年11月23日 上午10:05:43 
* version 1.0
 */
@DynamicUpdate(true)
@DynamicInsert(true)
@Entity
@TableInfo(group = "基础信息", id = 102, title = "楼宇信息")
public class LevelInfo extends BaseEntity {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldInfo(title = "ID号", number = 10, hidden = true)
	private int tf_leveId;
	
	@FieldInfo(title = "楼宇名称", uniqueField = true, number = 20)
	@Column(length = 50, nullable = false)
	@NodeType(type=TreeNodeType.TEXT)
	private String tf_leveName;
	
	@JsonIgnore
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="tf_viid")
	@FieldInfo(title = "楼宇名称", uniqueField = true, number = 30)
	private Village tf_village;
	
	@JsonIgnore
	@Transient
	private String icon=PropUtil.get("sys.leve.LevelInfo");

	public int getTf_leveId() {
		return tf_leveId;
	}

	public void setTf_leveId(int tf_leveId) {
		this.tf_leveId = tf_leveId;
	}

	public String getTf_leveName() {
		return tf_leveName;
	}

	public void setTf_leveName(String tf_leveName) {
		this.tf_leveName = tf_leveName;
	}

	public Village getTf_village() {
		return tf_village;
	}

	public void setTf_village(Village tf_village) {
		this.tf_village = tf_village;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
