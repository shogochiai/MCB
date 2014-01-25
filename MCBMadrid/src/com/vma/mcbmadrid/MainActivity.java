package com.vma.mcbmadrid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.vma.mcbmadrid.EscogerHora.EscogerHoraListener;
/*
 * Menu is disabled
 */
/*
import android.view.Menu;
*/

public class MainActivity extends FragmentActivity implements EscogerHoraListener{
	private EditText txtHoraSalida;
	
	private final String CLASE = MainActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
