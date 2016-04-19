package me.ppangya.wiki.framework.constant;

public class SystemEnvironment {

	public static final String OBJECT_REALTIONAL_MAPPING_NAME = "orm";

	public enum ObjectRelationalMapping {
		JDBC,
		MYBATIS,
		JPA;
	}

	private ObjectRelationalMapping objectRelationalMapping;

	public SystemEnvironment(ObjectRelationalMapping objectRelationalMapping) {
		this.objectRelationalMapping = objectRelationalMapping;
	}

	public ObjectRelationalMapping getObjectRelationalMapping() {
		return objectRelationalMapping;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SystemEnvironment{");
		sb.append("objectRelationalMapping=").append(objectRelationalMapping);
		sb.append('}');
		return sb.toString();
	}
}
