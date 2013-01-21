package com.calculabot.controller;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.graphics.Path;
import android.os.Environment;
//import android.util.Log;
import com.calculabot.formulas.BasicFormula;
import com.calculabot.model.FormulaBase;
/**
 *
 * @author Zeed
 */
@SuppressLint("DefaultLocale")
public class ChatController extends ClassLoader {
	
	BasicFormula formula;
	FormulaBase base;
	String packageDir = Environment.getExternalStorageDirectory()+"/CalculabotFormula/";
	
	public ChatController(){
		formula = null;
		base = new FormulaBase();
		packageDir = Environment.getExternalStorageDirectory()+"/CalculabotFormula/";
	}
	
	public boolean checkEx(String name){
		return base.isExist(name);
		
	}
	
	public int checkType(String name){
		return base.checkType(name);
	}
	
	public byte[] loader(String name) throws IOException{
		RandomAccessFile f = new RandomAccessFile(name, "r");
		byte[] b = new byte[(int)f.length()];
		f.read(b);
		return b;
	}
	//@author Fikri Adri
	public BasicFormula getClass(String name) throws Exception {
		name = name.toLowerCase();
		
		byte[] b = loader(name);
		Class jagung = defineClass(packageDir + name + ".class", b, 0, b.length);
		
		Class clazz = jagung;
        Class[] param = new Class[]{};
        Constructor cons = clazz.getConstructor(param);
        Object[] arguments = new Object[]{};
       
        formula = (BasicFormula)cons.newInstance(arguments);
		
        
		return formula;
	}
	
	public ArrayList<String> getQuetion(String name) throws Exception {
    	getClass(name);
        
        formula.initiate();
        ArrayList<String> question = formula.getQuestion();
        
        return question;
    }

    public String sendAnswer(ArrayList<String> in){
    	return formula.operate(in);
    }
    
    /*	private byte[] loadClassData(String name) throws IOException {
    // load the class data from the connection
	//assign the designated class to zewblah
	Class zewblah = null;
	
	String className = zewblah.getName();
	String classPath = className.replace('.','/')+".class";
	InputStream str = zewblah.getClassLoader().getResourceAsStream(packageDir + name + ".class");
	
	ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	int nRead;
	byte[] data = new byte[1024];

	while ((nRead = str.read(data, 0, data.length)) != -1) {
	  buffer.write(data, 0, nRead);
	}

	buffer.flush();
	

	return buffer.toByteArray();
}*/
}
