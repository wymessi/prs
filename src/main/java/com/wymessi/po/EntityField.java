package com.wymessi.po;

/**
 * 项目申请、专家与领域相关联持久类
 * 
 * @author 王冶
 *
 */
public class EntityField {

	// 关联实体的类型
	public static final String ENTITY_TYPE_PROJECT = "PROJECT";
	public static final String ENTITY_TYPE_EXPERT = "EXPERT";

	private long id;
	private long fieldId; // 领域id
	private long entityId; // 关联的实体id
	private String entityType; // 关联实体的类型

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFieldId() {
		return fieldId;
	}

	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

}
