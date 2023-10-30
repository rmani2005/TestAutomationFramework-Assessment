package com.clover.solution.ui.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.clover.solution.ui.util.logs.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class jsonReader
{
	private String testcaseName;
	public jsonReader(String testcaseName)
	{
		this.testcaseName=testcaseName;
	}
	
	public List<LinkedHashMap<String, String>> getJsonData()
	{
		return this.JSONDataReader(testcaseName);
	}
	
	private List<LinkedHashMap<String, String>> JSONDataReader(String testcaseName)
    {
    	String fileName = null;
    	String currentDirectory=System.getProperty("user.dir");
    	JSONParser parser = new JSONParser();
    	JSONArray arrayOfData = null; 
        List<LinkedHashMap<String, String>> dataMap=new LinkedList<LinkedHashMap<String,String>>();
        JSONArray arrayOfData1 = null;   
        try { 
        	
   
        	fileName=currentDirectory + "/src//main//java//com//clover//solution//ui//data//" + testcaseName + ".json";
        	Log.info("JSON file reader from path - " + fileName );
        	arrayOfData = (JSONArray)parser.parse(new FileReader(fileName));
            //Object obj = parser.parse(new FileReader(fileName));
        	System.out.println(arrayOfData);
	        } catch (FileNotFoundException e) {
	        	Log.error("JSONDataReader-" + e.toString());
	            e.printStackTrace();
	        }catch (IOException e) {
	        	Log.error("JSONDataReader-" + e.toString());
	            e.printStackTrace();
	        } catch (ParseException e) {
	        	Log.error("JSONDataReader-" + e.toString());
				e.printStackTrace();
			} catch (Exception e) {
				Log.error("JSONDataReader-" + e.toString());
				e.printStackTrace();
			} 
        
        try { 
        	Set<Entry<String,String>> tempSet;
        	LinkedHashMap<String,String> tempData;
            for (Object oject : arrayOfData){
	              JSONObject data = (JSONObject) oject;
	              tempSet=data.entrySet();
	              tempData=new LinkedHashMap<String,String>();
	              
	              for (Entry<String,String> set: tempSet) {
						Log.info("Adding Key and Value from JSON file - " + set.getKey() + "=" + set.getValue());
						tempData.put(set.getKey(), set.getValue());
					}
		            dataMap.add(tempData);
		            Log.info("Adding the hashmap value to LinkedList - " + tempData);
		            //String name = (String) person.get("name");
		            //JSONArray cars = (JSONArray) person.get("cars");
            	}   
            }catch (Exception e) {
	            e.printStackTrace();
	        }
		return dataMap;
    }
}