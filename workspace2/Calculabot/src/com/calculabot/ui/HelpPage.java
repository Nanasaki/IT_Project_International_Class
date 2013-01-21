package com.calculabot.ui;

import com.calculabot.R;
import com.calculabot.R.layout;
import com.calculabot.R.menu;
import com.calculabot.controller.HelpController;
import com.calculabot.model.HelpData;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelpPage extends Activity {
	LinearLayout body;
	TextView title;
	TextView text;
	HelpController helpC;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help_page);
		
		
		title=(TextView)findViewById(R.id.helptitle);
		text=(TextView)findViewById(R.id.helptext);
		
		
		Intent i = this.getIntent();
		String name = i.getStringExtra("name");
		
		helpC = new HelpController(this);
		
		HelpData help = helpC.searchGetData(name);
		
		title.setText(help.getTitle());
		text.setText(help.getDescription().replace("\\n", "\n"));
		
		body=(LinearLayout)findViewById(R.id.HelpList);
  
		
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
        	body.setBackgroundColor(Color.GREEN);
        	return true;
        case 2:
        	body.setBackgroundColor(Color.RED);
        	 return true;
        case 3:
        	body.setBackgroundColor(Color.BLUE);
        	 return true;
        case 4:
        	body.setBackgroundColor(Color.YELLOW);
        	 return true;
        case 5:
        	body.setBackgroundColor(Color.GRAY);
        	 return true;
        case 6:
        	body.setBackgroundColor(Color.MAGENTA);
        	 return true;
        case 7:
        	body.setBackgroundColor(Color.BLACK);
        	 return true;
        case 8:
        	body.setBackgroundColor(Color.WHITE);
        	 return true;
        }
        return false;
	}

}
