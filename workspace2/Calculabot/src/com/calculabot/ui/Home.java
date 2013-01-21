package com.calculabot.ui;

import java.util.ArrayList;

import com.calculabot.R;
import com.calculabot.R.layout;
import com.calculabot.controller.BasicCalculationController;
import com.calculabot.model.FormulaBase;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Zeed
 *
 */
public class Home extends Activity 
{
	LinearLayout footer;
	int fs=20;
	SharedPreferences pref;
	
	TextView txthasil;
	EditText edFormula;
	String wall;
	
	FormulaBase base;
	BasicCalculationController eval = new BasicCalculationController();

	ArrayList<String> fNames;
	ArrayList<Integer> fTypes;
	ArrayList<String> fInfo;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    
        pref = this.getSharedPreferences("langPref", Context.MODE_PRIVATE);
        
        txthasil=(TextView)findViewById(R.id.menuTeksHasil);
        txthasil.setVisibility(View.GONE);
      
        FormulaBase base = new FormulaBase();
        
       
        
        Intent i = this.getIntent();
        wall=i.getStringExtra("wall");
        
        fNames = base.getNames();
        /*
        fTypes = base.getTypes();
        fInfo = base.getInfo();*/
    	

        edFormula=(EditText)findViewById(R.id.menuTeksedit);
    
        ImageView  menuGbrCari=(ImageView)findViewById(R.id.gbrgo); 
        
        //NOTFINISHEDPETIR sedih
        //txthasil.setHint("@string/cihintbah");
        
        menuGbrCari.setOnClickListener(new View.OnClickListener() 
        {
    		public void onClick(View arg0) 
    		{
    			String dti=edFormula.getEditableText().toString();
    			if(dti.indexOf("=")==0)
    			{
	    			txthasil.setVisibility(View.VISIBLE);
	    		
	    			dti=dti.substring(1,dti.length());	//=9+3
	    			
	    			eval = new BasicCalculationController();
	    			String postfix = eval.convert2Postfix(dti);
	    			
	    			String hsl=String.valueOf(eval.evaluatePostfix(postfix));
	    			txthasil.setText(dti+"\n="+hsl);
	    			
	    			//rip notuseful1
	    			 
    			}
    			else{
					String dti2=dti.toLowerCase();//force
    				int ja=0;
					for(int j=0;j<fNames.size();j++){
						String dcr=fNames.get(j).toLowerCase();
    					if(dcr.indexOf(dti2)>=0){
    						if (pref.getInt("langPref", 0)==0)
    						{
    							Toast.makeText(Home.this, "Mencari "+dti, Toast.LENGTH_LONG).show();
    						}
    						
    						else
    						{
    							Toast.makeText(Home.this, "Searching "+dti, Toast.LENGTH_LONG).show();
    						}
    						 
    						Intent  i= new Intent(Home.this, ListFormula.class);
    		    			i.putExtra("fSearch", dti);
    		    			i.putExtra("wall", wall);
    		    			startActivity(i);   
    		    			ja=ja+1;
    						break;
    					} 
    					
    				}
					
					if(ja==0)
					{
						 
						if (pref.getInt("langPref", 0)==0)
						{
							Toast.makeText(Home.this, "Maaf, "+dti+" tidak dapat ditemukan!", Toast.LENGTH_LONG).show();
						}
						
						else
						{
							Toast.makeText(Home.this, "Sorry, "+dti+" not found!", Toast.LENGTH_LONG).show();
						}
					}
	    			bersih();
    				
    			}
    		}
    	});
   
        txthasil.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View arg0) 
        	{
        		bersih();
        	}
        });
    
    
    
        ImageView  menugbrFormula=(ImageView)findViewById(R.id.widget36);
        menugbrFormula.setOnClickListener(new View.OnClickListener() 
        {
    		public void onClick(View arg0) 
    		{
    			Intent  i= new Intent(Home.this, ListFormula.class);
    			i.putExtra("fSearch", "all");
    			i.putExtra("wall", wall);
    			startActivity(i);    			
    		}
        });
    

    
    
        ImageView  btnhelp=(ImageView)findViewById(R.id.widget31);
        btnhelp.setOnClickListener(new View.OnClickListener() 
        {
    		public void onClick(View arg0) 
    		{
    			Intent  i= new Intent(Home.this, ListHelp.class);
     			startActivity(i);
    			
    		}
        });

        // NOTFINISHEDPETIR ganti gambar button
        // to go to languagesettings screen      petir
        ImageView  btnLangSet=(ImageView)findViewById(R.id.imageButton1);
        btnLangSet.setOnClickListener(new View.OnClickListener() 
        {
    		public void onClick(View arg0) 
    		{
    			Intent  i= new Intent(Home.this, LanguageSettings.class);
    			startActivity(i);    
    		
    		}
        });
        
        footer=(LinearLayout)findViewById(R.id.listhelplayout);
    
    
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu("Font Size");
        subMenu.add(1, 1, 0, "4").setChecked(true);
        subMenu.add(1, 2, 0, "8");
        subMenu.add(1, 3, 0, "12");
        subMenu.add(1, 4, 0, "16");
        subMenu.add(1, 5, 0, "20");
        subMenu.add(1, 6, 0, "24");
        subMenu.add(1, 7, 0, "28");
        subMenu.add(1, 8, 0, "32");
        subMenu.setGroupCheckable(1, true, true);
        
        SubMenu subMenu2 = menu.addSubMenu("Font Type");
        subMenu2.add(1, 9, 0, "Sans").setChecked(true);
        subMenu2.add(1, 10, 0, "Serif");
        subMenu2.add(1, 11, 0, "Monospace");
        subMenu2.add(1, 12, 0, "Normal");
        subMenu2.setGroupCheckable(1, true, true);
        
        SubMenu subMenu3 = menu.addSubMenu("Footer Color");
        subMenu3.add(1, 13, 0, "Green").setChecked(true);
        subMenu3.add(1, 14, 0, "Red");
        subMenu3.add(1, 15, 0, "Blue");
        subMenu3.add(1, 16, 0, "Yellow");
        subMenu3.add(1, 17, 0, "Grey");
        subMenu3.add(1, 18, 0, "Pink");
        subMenu3.add(1, 19, 0, "Black");
        subMenu3.add(1, 20, 0, "White");
        subMenu3.setGroupCheckable(1, true, true);
        return true;
        
        
        
        
    }

    /* menangani pemilihan menu*/
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
        	fs=4;
        	txthasil.setTextSize(fs);
        	 return true;
        case 2:
        	fs=8;
        	txthasil.setTextSize(fs);
        	 return true;
        case 3:
        	fs=12;
        	txthasil.setTextSize(fs);
        	 return true;
        case 4:
        	fs=16;
        	txthasil.setTextSize(fs);
        	 return true;
        case 5:
        	fs=20;
        	txthasil.setTextSize(fs);
        	 return true;
        case 6:
        	fs=24;
        	txthasil.setTextSize(fs);
        	 return true;
        case 7:
        	fs=28;
        	txthasil.setTextSize(fs);
        	 return true;
        case 8:
        	fs=32;
        	txthasil.setTextSize(fs);
        	 return true;
        case 9:
        	txthasil.setTextSize(fs);
        	txthasil.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        	 return true;
        case 10:
        	txthasil.setTextSize(fs);
        	txthasil.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
        	 return true;
        case 11:
        	txthasil.setTextSize(fs);
        	txthasil.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
        	 return true;
        case 12:
        	txthasil.setTextSize(fs);
        	txthasil.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        	 return true;
        
        case 13:
        	footer.setBackgroundColor(Color.GREEN);
        	return true;
        case 14:
        	footer.setBackgroundColor(Color.RED);
        	 return true;
        case 15:
        	footer.setBackgroundColor(Color.BLUE);
        	 return true;
        case 16:
        	footer.setBackgroundColor(Color.YELLOW);
        	 return true;
        case 17:
        	footer.setBackgroundColor(Color.GRAY);
        	 return true;
        case 18:
        	footer.setBackgroundColor(Color.MAGENTA);
        	 return true;
        case 19:
        	footer.setBackgroundColor(Color.BLACK);
        	 return true;
        case 20:
        	footer.setBackgroundColor(Color.WHITE);
        	 return true;
        default:
        	fs=20;
        	txthasil.setTextSize(fs);
        	txthasil.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        	footer.setBackgroundColor(Color.GREEN);
        	 return true;
        }
    }
/*
    Typeface.DEFAULT
    Typeface.MONOSPACE
    Typeface.SANS_SERIF
    Typeface.SERIF
    and font-type

    Typeface.NORMAL
    Typeface.BOLD
    Typeface.BOLD_ITALIC
    Typeface.ITALIC
    */
    void bersih(){
    	txthasil.setText("");
		edFormula.setText("");
		txthasil.setVisibility(View.GONE);
    }
    
    public void selesai(){
    	new AlertDialog.Builder(this)
		.setTitle("Hasil Rumus ")
		.setMessage("Hasil proses")
		.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dlg, int sumthin) {
			}})
		.show();
    }

    
}