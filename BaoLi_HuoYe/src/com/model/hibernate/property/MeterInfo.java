package com.model.hibernate.property;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.ufo.framework.annotation.FieldInfo;
import com.ufo.framework.annotation.TableInfo;
import com.ufo.framework.common.model.BaseEntity;

@SuppressWarnings("serial")
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@TableInfo(group = "抄表管理", id =201, title = "抄表信息")
public class MeterInfo extends BaseEntity {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldInfo(title = "ID号", number = 10, hidden = true)
	private  int tf_MeterId;
	@FieldInfo(title = "开始度数", number = 20)
	private double tf_startnumber ;
	@FieldInfo(title = "结束度数", number = 30)
	private double tf_endnumber;
	@Column(length=25)
	@FieldInfo(title = "抄表时间", number =40)
	private String tf_meterdate;
	@Column(length=25)
	@FieldInfo(title = "抄表人员", number =50)
	private String tf_mtermane;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_residentId",nullable=false)
	@FieldInfo(title = "业主", number =60)
	private ResidentInfo tf_ResidentInfo;
	
	@Column(length=500)
	@FieldInfo(title = "备注", number =70)
	private String  tf_remark;

	public int getTf_MeterId() {
		return tf_MeterId;
	}

	public void setTf_MeterId(int tf_MeterId) {
		this.tf_MeterId = tf_MeterId;
	}

	public double getTf_startnumber() {
		return tf_startnumber;
	}

	public void setTf_startnumber(double tf_startnumber) {
		this.tf_startnumber = tf_startnumber;
	}

	public double getTf_endnumber() {
		return tf_endnumber;
	}

	public void setTf_endnumber(double tf_endnumber) {
		this.tf_endnumber = tf_endnumber;
	}

	public String getTf_meterdate() {
		return tf_meterdate;
	}

	public void setTf_meterdate(String tf_meterdate) {
		this.tf_meterdate = tf_meterdate;
	}

	public String getTf_mtermane() {
		return tf_mtermane;
	}

	public void setTf_mtermane(String tf_mtermane) {
		this.tf_mtermane = tf_mtermane;
	}

	public ResidentInfo getTf_ResidentInfo() {
		return tf_ResidentInfo;
	}

	public void setTf_ResidentInfo(ResidentInfo tf_ResidentInfo) {
		this.tf_ResidentInfo = tf_ResidentInfo;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}
	
}
