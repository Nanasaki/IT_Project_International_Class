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
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Zeed
 *
 */
public class Home extends Activity 
{
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
    
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
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