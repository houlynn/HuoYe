package com.model.hibernate.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.ufo.framework.annotation.FieldInfo;
import com.ufo.framework.annotation.TableInfo;
import com.ufo.framework.common.model.BaseEntity;

@DynamicInsert(true)
@DynamicUpdate(true)
@TableInfo(group="基础信息",id=106,title="收费标准")
@Entity
public class FeesInfo extends BaseEntity {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldInfo(title = "ID号", number = 10, hidden = false)
	private int tf_feesid;
	
	 @FieldInfo(title = "计算标准", number = 20)
	 @Column(length=50,nullable=false)
	private String tf_freesName;
	 private String tf_freenType;
	 private  double tf_price;
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	public int getTf_feesid() {
		return tf_feesid;
	}

	public void setTf_feesid(int tf_feesid) {
		this.tf_feesid = tf_feesid;
	}

	public String getTf_freesName() {
		return tf_freesName;
	}

	public void setTf_freesName(String tf_freesName) {
		this.tf_freesName = tf_freesName;
	}

}
