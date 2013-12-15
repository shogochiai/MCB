package com.vma.mcbmadrid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*
 * Menu is disabled
 */
/*
import android.view.Menu;
*/
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText txtHoraSalida = (EditText) findViewById(R.id.txtTiempoSalida);
		txtHoraSalida.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();
				
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
