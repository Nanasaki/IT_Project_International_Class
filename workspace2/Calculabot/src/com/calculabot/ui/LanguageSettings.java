package com.calculabot.ui;

import com.calculabot.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LanguageSettings extends Activity implements OnClickListener{
//this shared preferences is meant to store a value(integer) which determines the 
//language used in Calculabot(0 = Bahasa Indonesia, 1 = English), add info when new language added
	RelativeLayout bg;
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
		
		bg=(RelativeLayout)findViewById(R.id.bglgset);
        bg.setBackgroundColor(Color.WHITE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		 SubMenu subMenu = menu.addSubMenu("Change Background");
	        subMenu.add(1, 1, 0, "Green").setChecked(true);
	        subMenu.add(1, 2, 0, "Red");
	        subMenu.add(1, 3, 0, "Blue");
	        subMenu.add(1, 4, 0, "Yellow");
	        subMenu.add(1, 5, 0, "Grey");
	        subMenu.add(1, 6, 0, "Pink");
	        subMenu.add(1, 7, 0, "Black");
	        subMenu.add(1, 8, 0, "White");
	        subMenu.setGroupCheckable(1, true, true);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
        	bg.setBackgroundColor(Color.GREEN);
        	return true;
        case 2:
        	bg.setBackgroundColor(Color.RED);
        	 return true;
        case 3:
        	bg.setBackgroundColor(Color.BLUE);
        	 return true;
        case 4:
        	bg.setBackgroundColor(Color.YELLOW);
        	 return true;
        case 5:
        	bg.setBackgroundColor(Color.GRAY);
        	 return true;
        case 6:
        	bg.setBackgroundColor(Color.MAGENTA);
        	 return true;
        case 7:
        	bg.setBackgroundColor(Color.BLACK);
        	 return true;
        case 8:
        	bg.setBackgroundColor(Color.WHITE);
        	 return true;
        }
        return false;
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
