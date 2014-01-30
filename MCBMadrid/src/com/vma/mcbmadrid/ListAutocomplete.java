package com.vma.mcbmadrid;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.Key;

@SuppressWarnings("serial")
public class ListAutocomplete implements Serializable{

	@Key
	public String status;
	
	@Key
	public List<Prediction> predictions;
}
