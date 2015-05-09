package edu.asu.ser322.data.access;

public enum ComparisonType
{
	LESS_THAN("<"),
	LESS_THAN_EQ("<="),
	GREATER_THAN(">"),
	GREATER_THAN_EQ(">="),
	EQUAL_TO("="),
	NOT_EQUAL_TO("<>");
	
	private String symbolicRepresentation;
	
	private ComparisonType(String symbolicRepresentation)
	{
		this.symbolicRepresentation = symbolicRepresentation;
	}
	
	public String symbolicRepresentation()
	{
		return symbolicRepresentation;
	}
}
