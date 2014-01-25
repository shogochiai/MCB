package com.vma.mcbmadrid;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;

public class EscogerHora extends DialogFragment implements OnEditorActionListener{
	private TimePicker escogeHora;
	private Button bGuardaHora;
	private int hora, minuto;
	
	private final String CLASE = EscogerHora.class.getName();

	public EscogerHora(){
	}
	
	public interface EscogerHoraListener{
		void onFinishEditDialog(String inputText);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.escoge_hora_fragment, container);
		escogeHora = (TimePicker) view.findViewById(R.id.escogeHora);
		bGuardaHora = (Button) view.findViewById(R.id.btnEscogeHora);
		
		getDialog().getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		
		final Calendar calendar = Calendar.getInstance();
		hora = calendar.get(Calendar.HOUR_OF_DAY);
		minuto = calendar.get(Calendar.MINUTE);
		
		escogeHora.setIs24HourView(true);
		escogeHora.setCurrentHour(hora);
		escogeHora.setCurrentMinute(minuto);
		
		bGuardaHora.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(CLASE, "Obtiene hora: " + escogeHora.getCurrentHour() + ":" + escogeHora.getCurrentMinute());
				String tiempo = escogeHora.getCurrentHour() + ":" + escogeHora.getCurrentMinute(); 
				
				MainActivity activity = (MainActivity) getActivity();
				activity.onFinishEditDialog(tiempo);
				dismiss();
			}
		});
		
		return view;
	}
	
	@Override
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		Log.i(CLASE, "evento onEditorAction llamado");
		return false;
	}

}
