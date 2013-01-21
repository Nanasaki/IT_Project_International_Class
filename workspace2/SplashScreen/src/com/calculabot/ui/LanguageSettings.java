package com.calculabot.ui;

import com.calculabot.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LanguageSettings extends Activity implements OnClickListener{
//this shared preferences is meant to store a value(integer) which determines the 
//language used in Calculabot(0 = Bahasa Indonesia, 1 = English), add info when new language added
	SharedPreferences pref;
	Button bahBut; //Bahasa Indonesia
	Button engBut; //English

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_language_settings);
		
		pref = this.getSharedPreferences("langPref", Context.MODE_PRIVATE);
		pref.getInt("langPref", 1);
		
		
		engBut = (Button) findViewById(R.id.button1);
		engBut.setOnClickListener(this);
		
		bahBut = (Button) findViewById(R.id.button2);
		bahBut.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_language_settings, menu);
		return true;
	}

	@Override
	public void onClick(View click) {
		// setting the language used by using shared preference
		if(click.getId()==R.id.button1)
		{
			pref.edit().putInt("langPref", 0).commit();
			Toast.makeText(LanguageSettings.this, "Bahasa Indonesia telah dipilih sebagai bahasa utama", Toast.LENGTH_LONG).show();
		}
		else
		{
			pref.edit().putInt("langPref", 1).commit();
			Toast.makeText(LanguageSettings.this, "English has chosen as the main language", Toast.LENGTH_LONG).show();
		}
		
	}
	
	
}
