package com.calculabot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.calculabot.model.HelpData;
import com.calculabot.ui.ListFormula;

public class HelpController  {
	ArrayList<HelpData> helpDatas = new ArrayList<HelpData>();
	Context ctx;
	SharedPreferences pref;

	
	public HelpController(Context c){
		helpDatas = new ArrayList<HelpData>();
		readData(c);
	}
	
	public HelpData searchGetData(String name){
		HelpData ret = null;
		
		
		for(HelpData h : helpDatas){
			if (h.getTitle().equals(name)){
				ret = h;
			}
		}
		return ret;
	}
	
	public ArrayList<String> getNameArray(){
		ArrayList<String> ret = new ArrayList<String>();
		for(HelpData h : helpDatas){
			ret.add(h.getTitle());
		}
		return ret;
	}
	
	public ArrayList<HelpData> getHelpDatas() {
		return helpDatas;
	}
	
	public void readData(Context c){
		ctx=c;
		String fname;
		
		pref = c.getSharedPreferences("langPref", Context.MODE_PRIVATE);
		if (pref.getInt("langPref", 0)==0)
		{
			fname = "helptextbah.txt";
		}
		
		else
		{
			fname = "helptext.txt";
		}
		

		 try {
			   Scanner scan = new Scanner(c.getAssets().open(fname));
			   String title = "";
			   String text = "";
			   while(scan.hasNext()){
				   title = scan.nextLine();
				   text = scan.nextLine();
				   helpDatas.add(new HelpData(title, text));
				   
			   }
			   scan.close();
			  } catch (Exception e) {
			   Log.i("ReadNWrite, readFile()", "bzzzz"+"Exception e = " + e);
			  }
	}

}
