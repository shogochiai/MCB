package com.vma.mcbmadrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vma.mcbmadrid.EscogerHora.EscogerHoraListener;
/*
 * Menu is disabled
 */
/*
import android.view.Menu;
*/

public class MainActivity extends FragmentActivity implements EscogerHoraListener{
	private EditText txtHoraSalida;
	
	GooglePlaces googlePlaces;
	GPSTracker gps;
	ListAutocomplete listaAutocomplete;
	Context context;
		
	private final String CLASE = MainActivity.class.getName();
	
	public static String KEY_REFERENCE = "reference";
	public static String KEY_NAME = "name";
	public static String KEY_VICINITY = "vicinity";
	
	List<String> descripciones = new ArrayList<String>();
	ArrayAdapter<String> adaptDesc;
	
	AutoCompleteTextView textoBuscarPlace;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		
		Spinner spinner = (Spinner) findViewById(R.id.spinnerTransporte);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.listaTransportes, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new SpinnerTransportes());
		
		txtHoraSalida = (EditText) findViewById(R.id.txtTiempoSalida);
		txtHoraSalida.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
				EscogerHora escogerHora = new EscogerHora();
				escogerHora.show(fragmentManager, "fragment_tag");
				
			}
		});
		
		final Button btnSalir = (Button) findViewById(R.id.btnSalir);
		btnSalir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		final Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
		btnBuscar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		textoBuscarPlace = (AutoCompleteTextView) findViewById(R.id.txtDestino);
		textoBuscarPlace.setThreshold(1);
		adaptDesc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, descripciones);
		textoBuscarPlace.setAdapter(adaptDesc);
		adaptDesc.setNotifyOnChange(true);
		
		textoBuscarPlace.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				textoBuscarPlace.showDropDown();
				return false;
			}
		});
		
		textoBuscarPlace.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				new PlacesTask().execute(arg0.toString());
				
			}
			
		});
		
	}

	@Override
	public void onFinishEditDialog(String inputText) {
		Log.i(CLASE, "onFinishEditDialog");
		txtHoraSalida.setText(inputText);
	}
	
	public class SpinnerTransportes implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			Log.i(CLASE, "Seleccionado " + parent.getItemAtPosition(pos).toString());			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
						
		}
		
	}
	
	public class AutoCompleteText extends AutoCompleteTextView{
		public AutoCompleteText(Context context, AttributeSet attrs){
			super(context, attrs);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		protected CharSequence convertSelectionToString(Object selectedItem){
			HashMap<String, String> hm = (HashMap<String, String>) selectedItem;
			return hm.get("description");
		}
	}
	
	private class PlacesTask extends AsyncTask<String, Void, String>{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... place) {
			googlePlaces = new GooglePlaces();
			String p = place[0];
			
			try{
				listaAutocomplete = googlePlaces.getAutocomplete(p);
			}catch(Exception e){
				//Log.e(CLASE, e.getMessage() + ": " + e.getLocalizedMessage());
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String file_url){
			
			runOnUiThread(new Runnable(){

				@Override
				public void run() {
					if (listaAutocomplete != null){
						String status = listaAutocomplete.status;
						
						
						if (status.equals("OK")){
							if (listaAutocomplete.predictions != null){
								
								adaptDesc.clear();
								
								for(int i=0;i<listaAutocomplete.predictions.size();i++){
									descripciones.add(listaAutocomplete.predictions.get(i).description);
									//Log.d(CLASE, descripciones.get(i));
								}
								
								String texto = "";
								for(int i=0;i<descripciones.size();i++){
									//Log.d(CLASE, descripciones.get(i));
									texto = descripciones.get(i);
									
									adaptDesc.add(texto);
								}
								//Log.i(CLASE, texto);
								
							}
							else{
								Log.e(CLASE, "valor nulo");
							}
						}
						else if (status.equals("ZERO_RESULTS")){
							Toast.makeText(context, "No hay resultados", Toast.LENGTH_SHORT).show();
						}
						else if (status.equals("UNKOWN_ERROR")){
							Toast.makeText(context, "Error, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
						}
						else if (status.equals("OVER_QUERY_LIMIT")){
							Toast.makeText(context, "Error de cuota en Google, vuelva a intentarlo en unos minutos", Toast.LENGTH_SHORT).show();
						}
						else if (status.equals("REQUEST_DENIED")){
							Toast.makeText(context, "Error. Consulte con el autor del programa", Toast.LENGTH_SHORT).show();
						}
						else if (status.equals("INVALID_REQUEST")){
							Toast.makeText(context, "Petición no válida", Toast.LENGTH_SHORT).show();
						}
						else{
							Toast.makeText(context, "Ha ocurrido un error. Si persiste contacte con el autor", Toast.LENGTH_SHORT).show();
						}
					}
					
				}
				
			});
		}
		
	}

	/*
	 * Menu is disabled
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/

}
