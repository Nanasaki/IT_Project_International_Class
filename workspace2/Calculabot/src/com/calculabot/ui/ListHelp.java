package com.calculabot.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.calculabot.R;
import com.calculabot.R.layout;
import com.calculabot.R.menu;
import com.calculabot.controller.HelpController;
import com.calculabot.model.HelpData;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ListHelp extends ListActivity {
	LinearLayout body;
	HelpController helpc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_help);
		
		body=(LinearLayout)findViewById(R.id.bghelp);
        body.setBackgroundColor(Color.WHITE);
		
		helpc = new HelpController(this);
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		
		int count=1;
		for(HelpData h : helpc.getHelpDatas()){
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("id", String.valueOf(count));
			map.put("name", h.getTitle());
        	mylist.add(map);	
		}
		
		

	ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.list_help_item, new String[] { "name" }, 
	new int[] { R.id.txtListMain });
	setListAdapter(adapter);
	
	final ListView lv = getListView();
	lv.setTextFilterEnabled(true);	
	lv.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        	
			HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	        		
			/*String pos=o.get("id");*/
			
			Intent  i= new Intent(ListHelp.this, HelpPage.class);
			i.putExtra("name", helpc.getHelpDatas().get(position).getTitle());//Force
			startActivity(i);   
			}
		}
	);
		
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
