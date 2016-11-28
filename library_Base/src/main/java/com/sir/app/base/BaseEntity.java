package com.sir.app.base;

import java.io.Serializable;

/**
 * 实体基类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public abstract class BaseEntity implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 6337104618534280060L;
	
	/**
	 * 主键ID
	 */
	protected String id;
	
	/**
	 * 备注
	 */
	protected String remark;
	
	/**
	 * 版本号
	 */
	protected Integer version;
	
	/**
	 * 是否有效
	 */
	protected Boolean valid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
