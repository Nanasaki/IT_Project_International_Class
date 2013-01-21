package com.calculabot.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.calculabot.model.FormulaBase;

import com.calculabot.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class ListFormula  extends ListActivity{
	LinearLayout body;
	String wall,fsearch;

	//The searched 
	ArrayList<String> fNames;
	ArrayList<Integer> fTypes;
	ArrayList<String> fInfo;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_formula);
        
        body=(LinearLayout)findViewById(R.id.bgform);
        body.setBackgroundColor(Color.WHITE);

        Intent i = this.getIntent();
        fsearch=i.getStringExtra("fSearch");
        wall=i.getStringExtra("wall");
        
        
        
        FormulaBase base = new FormulaBase();
        ArrayList<String> ArrayBase = base.getNames();
        int jab=ArrayBase.size();
       
	ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	
	if(fsearch.equals("all")){
		fNames = ArrayBase;
		fTypes = base.getTypes();
		fInfo = base.getInfo();
	}
	else{
		fNames = new ArrayList<String>();
		fTypes = new ArrayList<Integer>();
		fInfo = new ArrayList<String>();
		
		ArrayList<String> lInfo = base.getInfo();
		ArrayList<Integer> lType = base.getTypes();
		for(int j=0;j<ArrayBase.size();j++){
			String dn=ArrayBase.get(j).toLowerCase();
			String dc=fsearch.toLowerCase();
			if(dn.indexOf(dc)>=0){
				fNames.add(ArrayBase.get(j));	
				fTypes.add(lType.get(j));
				fInfo.add(lInfo.get(j));
			} 
		}
	}
	
	for(int n=0;n<fNames.size();n++){
					HashMap<String, String> map = new HashMap<String, String>();	
					int m=n+1;
					String name=fNames.get(n);
					String types= typer(fTypes.get(n));
					String info= fInfo.get(n);
					
					String gab=name+" :";
					map.put("id", String.valueOf(m));
		        	map.put("formula", gab);
		        	map.put("info", info);
		        	mylist.add(map);	
	}

ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.listitemrumus, new String[] { "formula", "info" }, 
new int[] { R.id.txtListMain, R.id.txtListItem });
setListAdapter(adapter);

final ListView lv = getListView();
lv.setTextFilterEnabled(true);	
lv.setOnItemClickListener(new OnItemClickListener() {
	
	 
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        	
		HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	        		
		String pos=o.get("id");
		
		Toast.makeText(ListFormula.this, "Formula :"+ fNames.get(position) + " has choosen", Toast.LENGTH_LONG).show(); 
		Intent  i= new Intent(ListFormula.this, ChatInterface.class);
		i.putExtra("wall", wall);
		i.putExtra("name", fNames.get(position));//Force
		startActivity(i);
		
		
		ListFormula.this.finish();
	}});
}	       
    
    public boolean onCreateOptionsMenu(Menu menu) {
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
    
    public String typer(int in){
    	if(in==1){
    		return "Number";
    	}else if(in==2){
    		return "String";
    	}
    	return "";
    	
    }
    

}