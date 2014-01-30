package com.vma.mcbmadrid;

import java.io.Serializable;

import com.google.api.client.util.Key;

@SuppressWarnings("serial")
public class Term implements Serializable{

	@Key
	public int offset;
	
	@Key
	public String value;
}
