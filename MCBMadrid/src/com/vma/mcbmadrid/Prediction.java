package com.vma.mcbmadrid;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.Key;

@SuppressWarnings("serial")
public class Prediction implements Serializable{

	@Key
	public String description;
	
	@Key
	public String id;
	
	public static class matched_substrings implements Serializable{
		
		@Key
		public int length;
		
		@Key
		public int offset;
	}
	
	@Key
	public List<Term> terms;
	
	@Key
	public List<String> types;
}
