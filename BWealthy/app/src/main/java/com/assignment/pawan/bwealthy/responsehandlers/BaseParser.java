package com.assignment.pawan.bwealthy.responsehandlers;

import com.assignment.pawan.bwealthy.models.BaseWrapper;

import java.util.List;



public interface BaseParser {
	public List<BaseWrapper> getParsedData(String data);


}
