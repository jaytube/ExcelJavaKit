package com.linus.excel.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Value must be a value of a set.
 * @author lyan2
 */
public class RangeColumnConstraint extends ColumnConstraint {
	private static ObjectMapper mapper = new ObjectMapper();
	private String[]  pickList;
	private Boolean mustInRange = true;

	public RangeColumnConstraint() {
		super();
		this.message = "excel.validation.range.message";
	}
	
	@Override
	public boolean isValid(Object value) {
		if (!this.getMustInRange()) {
			return true; 
		}
		
		for (String entry : pickList) {
			if (equal(entry, value)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String resolveMessage(String message) {
		if (message != null) {
			try {
				message = message.replaceAll("\\{range\\}", mapper.writeValueAsString(pickList));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return message;
	}
	
	public String[] getPickList() {
		return pickList;
	}

	public void setPickList(String[] pickList) {
		this.pickList = pickList;
	}

	public Boolean getMustInRange() {
		return mustInRange;
	}

	public void setMustInRange(Boolean mustInRange) {
		this.mustInRange = mustInRange;
	}
	
}
