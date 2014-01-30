package com.vma.mcbmadrid;

import java.util.logging.Logger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentHora extends Fragment{
	private final Logger log = Logger.getLogger(FragmentHora.class.getName());

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
		return inflater.inflate(R.layout.escoge_hora_fragment, container);
	}
	
	@Override
	public void onDestroyView(){
		log.info("Fragment hora destruido");
	}
}
