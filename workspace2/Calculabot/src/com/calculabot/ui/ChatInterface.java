package com.calculabot.ui;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.calculabot.controller.ChatController;
import com.calculabot.formulas.BasicFormula;
import com.calculabot.model.Bubble;
import com.calculabot.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

/**
 * @author Zeed
 *
 */
public class ChatInterface extends Activity implements OnClickListener, OnEditorActionListener {
	LinearLayout footer;
	LinearLayout header;
	LinearLayout background;
	ImageButton gobutton;
	ImageButton resetbutton;
	ImageButton copybutton;
	ImageButton listbutton;
	TextView text;
	
	SharedPreferences pref;//petir
	
	ScrollView scroller;
	ChatController cont;
	BufferedWriter out;
	
	int calt = 0; // current calculation type
	ArrayList<String> curQue = new ArrayList<String>(); //Current Question
	ArrayList<String> answers = new ArrayList<String>();
	String p; //Preference counter name

	BasicFormula method;
	int phase = 0;
	int qn = 0; //question number
	
	String result ="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_interface);
		
		header=(LinearLayout)findViewById(R.id.header);
      
        
        footer=(LinearLayout)findViewById(R.id.input_bar);
  
        
        background=(LinearLayout)findViewById(R.id.tengah);
		
		pref = this.getSharedPreferences("langPref", Context.MODE_PRIVATE);//petir
		
		cont = new ChatController();
		
		gobutton = (ImageButton) findViewById(R.id.go);
		gobutton.setOnClickListener(this);
		
		resetbutton = (ImageButton) findViewById(R.id.reset);
		resetbutton.setOnClickListener(this);
		
		copybutton = (ImageButton) findViewById(R.id.copy);
		copybutton.setOnClickListener(this);
		
		listbutton = (ImageButton) findViewById(R.id.list);
		listbutton.setOnClickListener(this);
		
		scroller = (ScrollView) findViewById(R.id.scrollbar);
		
		text = (TextView) findViewById(R.id.TextPut);
		text.setOnEditorActionListener(this);
		
	    
		
		
		String FILENAME = "chatlog.botlog";

		try {
			//Log.e("READ", "bbbbb");
			 out = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_APPEND )));
			//openFile
			
			
		} catch (FileNotFoundException e) {
			Log.e("READ", "Bzzx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			//Log.e("READ", "aaaaaa");
			//BufferedReader read = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
			Scanner scan = new Scanner(openFileInput(FILENAME));
			String line;
			String types;
			String[] temp;
			while (scan.hasNextLine()){
				line = scan.nextLine();
				types = scan.nextLine();
				Log.i("cbot", "asd" + line);
				Log.i("cbot", "asd" + types);
				//Log.e("READ", "Line: " + line);
				popchat(line, Integer.parseInt(types));
				
			}
			scan.close();
		} catch (FileNotFoundException e) {
			Log.e("READ", "Bzzr");
			
		} catch (IOException e) {
			Log.e("READ", "Bzzr");
		}/*catch(Exception e){
			Log.e("READ", "Bzza");
		}*/
		finally{
			
		}
		
		
		
		Intent i = this.getIntent();
		@SuppressWarnings("unused")
		String iname = i.getStringExtra("name");
		@SuppressWarnings("unused")
		String iwall = i.getStringExtra("wall");
		
		deploy(iname);
		scroller.fullScroll(ScrollView.FOCUS_DOWN);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		SubMenu subMenu = menu.addSubMenu("Change Header");
        subMenu.add(1, 1, 0, "Green").setChecked(true);
        subMenu.add(1, 2, 0, "Red");
        subMenu.add(1, 3, 0, "Blue");
        subMenu.add(1, 4, 0, "Yellow");
        subMenu.add(1, 5, 0, "Grey");
        subMenu.add(1, 6, 0, "Pink");
        subMenu.add(1, 7, 0, "Black");
        subMenu.add(1, 8, 0, "White");
        subMenu.setGroupCheckable(1, true, true);
        
        SubMenu subMenu2 = menu.addSubMenu("Change Footer");
        subMenu.add(1, 9, 0, "Green").setChecked(true);
        subMenu.add(1, 10, 0, "Red");
        subMenu.add(1, 11, 0, "Blue");
        subMenu.add(1, 12, 0, "Yellow");
        subMenu.add(1, 13, 0, "Grey");
        subMenu.add(1, 14, 0, "Pink");
        subMenu.add(1, 15, 0, "Black");
        subMenu.add(1, 16, 0, "White");
        subMenu2.setGroupCheckable(1, true, true);
        
        SubMenu subMenu3 = menu.addSubMenu("Change Background");
        subMenu.add(1, 17, 0, "Green").setChecked(true);
        subMenu.add(1, 18, 0, "Red");
        subMenu.add(1, 19, 0, "Blue");
        subMenu.add(1, 20, 0, "Yellow");
        subMenu.add(1, 21, 0, "Grey");
        subMenu.add(1, 22, 0, "Pink");
        subMenu.add(1, 23, 0, "Black");
        subMenu.add(1, 24, 0, "White");
        subMenu3.setGroupCheckable(1, true, true);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
        	header.setBackgroundColor(Color.GREEN);
        	return true;
        case 2:
        	header.setBackgroundColor(Color.RED);
        	 return true;
        case 3:
        	header.setBackgroundColor(Color.BLUE);
        	 return true;
        case 4:
        	header.setBackgroundColor(Color.YELLOW);
        	 return true;
        case 5:
        	header.setBackgroundColor(Color.GRAY);
        	 return true;
        case 6:
        	header.setBackgroundColor(Color.MAGENTA);
        	 return true;
        case 7:
        	header.setBackgroundColor(Color.BLACK);
        	 return true;
        case 8:
        	header.setBackgroundColor(Color.WHITE);
        	 return true;
        	 
        case 9:
        	footer.setBackgroundColor(Color.GREEN);
        	return true;
        case 10:
        	footer.setBackgroundColor(Color.RED);
        	 return true;
        case 11:
        	footer.setBackgroundColor(Color.BLUE);
        	 return true;
        case 12:
        	footer.setBackgroundColor(Color.YELLOW);
        	 return true;
        case 13:
        	footer.setBackgroundColor(Color.GRAY);
        	 return true;
        case 14:
        	footer.setBackgroundColor(Color.MAGENTA);
        	 return true;
        case 15:
        	footer.setBackgroundColor(Color.BLACK);
        	 return true;
        case 16:
        	footer.setBackgroundColor(Color.WHITE);
        	 return true;
        	 
        case 17:
        	background.setBackgroundColor(Color.GREEN);
        	return true;
        case 18:
        	background.setBackgroundColor(Color.RED);
        	 return true;
        case 19:
        	background.setBackgroundColor(Color.BLUE);
        	 return true;
        case 20:
        	background.setBackgroundColor(Color.YELLOW);
        	 return true;
        case 21:
        	background.setBackgroundColor(Color.GRAY);
        	 return true;
        case 22:
        	background.setBackgroundColor(Color.MAGENTA);
        	 return true;
        case 23:
        	background.setBackgroundColor(Color.BLACK);
        	 return true;
        case 24:
        	background.setBackgroundColor(Color.WHITE);
        	 return true;
        }
        return false;
	}
	
	@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putBooleanArray("mydata", myData);
    }
	
	
	//----------------------------------------------------------------------------
	
	public void popchat(String text, int type){
		if(type==0){
			popUser(text);
		}else{
			popBot(text);
		}
	}
	
	public void userPost(String message){
		popUser(message);
		
		try {
			out.write(message + "\n" + 0+ "\n");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scroller.fullScroll(ScrollView.FOCUS_DOWN);
	}
	
	public void popUser(String message){
		LinearLayout content = (LinearLayout)findViewById(R.id.chatview);
		
		
		View right = getLayoutInflater().inflate(R.layout.chat_item_right, content, false);
		TextView m = (TextView) right.findViewById(R.id.message);
		
		m.setText(message);
		
		content.addView(right);
	}
	
	public void botPost(String message){
		
		popBot(message);
		
		try {
			out.write(message + "\n" + 1+ "\n");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		scroller.fullScroll(ScrollView.FOCUS_DOWN); 
	}
	
	public void popBot(String message){
		LinearLayout content = (LinearLayout)findViewById(R.id.chatview);
		
		
		View right = getLayoutInflater().inflate(R.layout.chat_item_left, content, false);
		TextView m = (TextView) right.findViewById(R.id.message);
		
		m.setText(message);
		content.addView(right);
	}
	
	

	@Override
	public void onClick(View clicked) {
		// TODO Auto-generated method stub
		switch (clicked.getId()){
		case R.id.go:
			goClick();
			break;
		case R.id.reset:
			resetCalc();
			break;
		case R.id.copy:
			copyResult();
			break;
		case R.id.list:
			listClick();
			break;
		}
		
	}
	
	// Start of go click listener and the algorithm to get the answer
	public void goClick(){
		//Phase check
		if(phase==0){
			phase0();
		}else if(phase==1){
			phase1();
		}else if(phase==2){
			
		}else if(phase==3){
			
		}
	}
	
	public void phase0(){
String in = text.getText().toString();
		
		userPost(in);
		String[] r = in.split(" "); //input in array
		if(r.length>1){
			int p = 0; //pointer
			boolean flag = false;
			
			if (pref.getInt("langPref", 0)==0)//petir
			{
				for(p=0, flag = false; p<r.length-1; p++)
				{
					if(r[p].equalsIgnoreCase("cari")
						|| r[p].equalsIgnoreCase("mencari")
						|| r[p].equalsIgnoreCase("menghitung")
						|| r[p].equalsIgnoreCase("do"))
					{
						if(r.length > 1)
						{
							deploy(in);
						}
					}
				}
			}
			
			else
			{
				for(p=0, flag = false; p<r.length-1; p++)
				{
					if(r[p].equalsIgnoreCase("search")
						|| r[p].equalsIgnoreCase("searching")
						|| r[p].equalsIgnoreCase("calculate")
						|| r[p].equalsIgnoreCase("do"))
					{
						if(r.length > 1)
						{
							deploy(in);
						}
					}
				}
			
			}
		}
		
	}
	
	public void deploy(String in){

		calt = cont.checkType(in);
		//petir
		if (calt > 0 && pref.getInt("langPref", 0)==0){
			botPost("Beep beep, Kalkulasi dapat dilakukan");
			try {
				curQue = cont.getQuetion(in);
				phase = 1;
				changeTyper();
				askQ();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				botPost("Gagal menemukan class");
			}
		}
		else if (calt > 0 && pref.getInt("langPref", 1)==0)
		{
			botPost("Available, Starting calculation");
			try {
				curQue = cont.getQuetion(in);
				phase = 1;
				changeTyper();
				askQ();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				botPost("An error in finding class");
			}
		}
		else{
			if (pref.getInt("langPref", 0)==0)
			{
				botPost("Kalkulasi, Mohon masukkan input!");
			}
			
			else
			{
				botPost("You cannot make me calculate an empty input!");}
			}//petir
	}
	
	public void phase1(){
		String in = text.getText().toString();
		userPost(in);
		if(calt==1){
			if(isNumber(in)){
				answers.add(in);
				qn++;
				
			}else{if (pref.getInt("langPref", 0)==0)
			{
				botPost("Input yang anda masukkan bukan angka");					
			}
			
			else
			{
				botPost("The input is not a number");					
			}

			}
		}else{
			answers.add(in);
			qn++;}
		
		askQ();
	}
	
	//start random just for fun
	public String randomQ(String oriQ){
		Random gen = new Random();
		int t = gen.nextInt(4);
		if (pref.getInt("langPref", 0)==0)
		{
			if(t==0)
			{
				oriQ = "Mohon masukkan " + oriQ + "!";
				}else if(t==1){
				oriQ = "Masukkan " + oriQ + ":)";
				}else if(t==2){
				oriQ = "Silakan masukkan " + oriQ + "nya?";
				}else if(t==3){
				oriQ = "Coba masukkan " + oriQ + " ;)";
			}
		}
		
		else
		{
			if(t==0)
			{
				oriQ = "Please insert " + oriQ + "!";
				}else if(t==1){
				oriQ = "Insert the " + oriQ + ":)";
				}else if(t==2){
				oriQ = "How much the " + oriQ + "?";
				}else if(t==3){
				oriQ = "Insert the " + oriQ + ";)";
			}
		}//petir
		return oriQ;
	}
	public String randomA(String oriQ){
		Random gen = new Random();
		int t = gen.nextInt(4);

		if (pref.getInt("langPref", 0)==0)
		{
			if(t==0)
			{
				oriQ = "Daaan jawaabaaannyaaa adalaaah " + oriQ + "!";
			}
			else if(t==1)
			{
				oriQ = "Karena kita teman, jawabannya adalah " + oriQ + ", kawan!";
			}
			else if(t==2)
			{
				oriQ = "Bip bip bip: " + oriQ;
			}
			else if(t==3)
			{
				oriQ = "Ting tong" + oriQ;
			}
		}
		
		else
		{
			if(t==0){
			oriQ = "Aaaaaand the answeeer iiiiissss " + oriQ + "!";
		}else if(t==1){
			oriQ = "For you, it's " + oriQ + " friends!";
		}else if(t==2){
			oriQ = "Beep beep beep: " + oriQ;
		}else if(t==3){
			oriQ = "Burp, it's " + oriQ;
		}
		}
			
			return oriQ;
		
		
	
	}
	
	//end just for fun
	
	public void calculate(){
		result = cont.sendAnswer(answers);
		botPost(randomA(result));
		answers = new ArrayList<String>();
	}
	//end of go click listener
	
	public void resetCalc(){
		if(phase==0){
			botPost("Redo calculation");
			phase = 1;
			qn = 0;
			if(!curQue.isEmpty()){
				askQ();
				changeTyper();
			}
			answers = new ArrayList<String>();
			
		}else if (phase==1){
			if (pref.getInt("langPref", 0)==0)
			{
				botPost("Mempersiapkan ulang kalkulasi");
			}
			
			else
			{
				botPost("Resetting Calculation");
			}

			qn = 0;
			answers = new ArrayList<String>();
			if(!curQue.isEmpty()){
				askQ();
			}
		
		
		}
		
		
	}
	
	//asking Question
	public void askQ(){
		if (curQue.size()>qn){
			botPost(randomQ(curQue.get(qn)));
		}else{
			calculate();
			phase=0;
			qn = 0;
			changeTyper();
		}
	}
	
	public void changeTyper(){
		if (phase ==0){
			text.setInputType(0x00000001);
		}else{
			if(calt == 1){
				text.setInputType(0x00000002);
			}else if (calt==2){
				text.setInputType(0x00000001);
				
			}
		}
	}
	
	@SuppressLint("NewApi")
	public void copyResult(){
		/*clipboard.setText(result);*/
		if(result.equals("")){
			if (pref.getInt("langPref", 0)==0)
			{
				Toast.makeText(ChatInterface.this, "Tidak ada data yang dapat disalin", Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				Toast.makeText(ChatInterface.this, "No data that can be copied", Toast.LENGTH_SHORT).show();
			}		
		}else{
			
		
		
			int currentapiVersion = android.os.Build.VERSION.SDK_INT;
			if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
			     android.content.ClipboardManager clipboard =  (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
			        ClipData clip = ClipData.newPlainText("result", result);
			        clipboard.setPrimaryClip(clip); 
			} else{
			    android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 
			    clipboard.setText(result);
			}
			if (pref.getInt("langPref", 0)==0)//tingtong
			{
				Toast.makeText(ChatInterface.this, "Hasil telah disalin ke clipboard", Toast.LENGTH_SHORT).show();	
			}
			
			else
			{
				Toast.makeText(ChatInterface.this, "Result saved to clipboard", Toast.LENGTH_SHORT).show();	
			}		
		}
	}
	
	public void listClick(){
		Intent  i= new Intent(ChatInterface.this, ListFormula.class);
		i.putExtra("fSearch", "all");
		i.putExtra("wall", "");
		startActivity(i); 
	}
	
	public void homeClick(){
		
	}
	
	public boolean isNumber(String ring){
		boolean b = true;
		try{
			Double.parseDouble(ring);
		}catch(Exception e){
			b = false;
		}
		
		return b;
	}
	
	

	@Override
	public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
		// TODO Auto-generated method stub
		goClick();
		return false;
	}
}
