package net.ramso.dita.repository.content;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum DitaTypes {
	BOOKMAP(0, "BOOKMAP", "bookmap"), CONCEPT(1, "CONCEPT", "concept"), GLOSSGROUP(
			2, "GLOSSGROUP", "glossgroup"), GLOSSENTRY(3, "GLOSSENTRY",
			"glossentry"), MAP(4, "MAP", "map"), REFERENCE(5, "REFERENCE",
			"reference"), TASK(6, "TASK", "task"), TOPIC(7, "TOPIC", "topic");

	public static final int BOOKMAP_VALUE = 0;
	public static final int CONCEPT_VALUE = 1;
	public static final int GLOSSGROUP_VALUE = 2;
	public static final int GLOSSENTRY_VALUE = 3;
	public static final int MAP_VALUE = 4;
	public static final int REFERENCE_VALUE = 5;
	public static final int TASK_VALUE = 6;
	public static final int TOPIC_VALUE = 7;
	private static final String PKG = "net.ramso.dita.";
	private static final DitaTypes[] VALUES_ARRAY = new DitaTypes[] { BOOKMAP,
			CONCEPT, GLOSSGROUP, GLOSSENTRY, MAP, REFERENCE, TASK, TOPIC };
	public static final List<DitaTypes> VALUES = Collections
			.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	public static DitaTypes get(int value) {
		switch (value) {
		case BOOKMAP_VALUE:
			return BOOKMAP;
		case CONCEPT_VALUE:
			return CONCEPT;
		case GLOSSGROUP_VALUE:
			return GLOSSGROUP;
		case GLOSSENTRY_VALUE:
			return GLOSSENTRY;
		case MAP_VALUE:
			return MAP;
		case REFERENCE_VALUE:
			return REFERENCE;
		case TASK_VALUE:
			return TASK;
		case TOPIC_VALUE:
			return TOPIC;
		}
		return null;
	}

	public static DitaTypes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DitaTypes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	public static DitaTypes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DitaTypes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	private final int value;
	private final String name;
	private final String literal;

	private DitaTypes(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	public String getLiteral() {
		return literal;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
	
	public String getPackage(){
		return PKG+getLiteral();
	}

	@Override
	public String toString() {
		return literal;
	}
}
