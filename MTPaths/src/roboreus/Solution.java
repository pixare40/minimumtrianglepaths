package roboreus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.*;
import java.io.IOException;
import java.util.*;

public class Solution {
	
	public static ArrayList<ArrayList<Integer>> getValues(String inputfile) throws IOException{
		ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
		int row = 0;
		int currentrow = 0;
		try {

			BufferedReader br = new BufferedReader(new FileReader(inputfile));
			String line = null;
			
			try{
			while((line=br.readLine())!=null){
				row++;
				String[] parts = line.split(" ");
				ArrayList<Integer> nums = new ArrayList<Integer>();
				for (String part : parts) {
					Integer value = Integer.valueOf(part);
					nums.add(value);
				}
				arr.add(nums);			
			}
			}catch(Exception e){ //Get character errors at rows
				System.out.println("Error in input at row "+ row+"\n*Unsupported Character");
				br.close();
				return null;
			}
			
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		for(int i=0;i<arr.size();i++){
			int rowchecker = i+1;
			currentrow++;
			if(arr.get(i).size()!=rowchecker){//Get data layout errors in rows
				System.out.println("Wrong Data Layout at row "+currentrow+"\n*Please Check Data");
				return null;
			}
		}
		return arr;
	}
	
	public static ArrayList<Integer> minimumTotal(ArrayList<ArrayList<Integer>> triangle) {  
		   int[] preRow = new int[triangle.size()];  
		   String s = "";
		   // BFS  
		   ArrayList<Integer> nlist = new ArrayList<Integer>();
		   for (List<Integer> row : triangle) {  
		     int last = row.size() - 1;
		     if (last > 0) { 
		    	 preRow[last] = preRow[last-1] + row.get(last); 
		     }
		     int pre = preRow[0]; 
		     preRow[0] += row.get(0);  
		     nlist.add(pre); //add the values forming the path
		     for (int i=1; i<last; ++i) {  
		       // save the previous value so that we can use it for next number 
		    	 
		       int temp = preRow[i]; 
		       s+=temp+" ";
		       // either from i-1 or i from the previous row  
		       preRow[i] = Math.min(preRow[i]+row.get(i), pre+row.get(i)); 
		       pre = temp;  
		     }  
		   }  
		   
		   // find the min  
		   int res = preRow[0];  
		   for (int num : preRow) {
		     if (num < res) res = num;  
		   }  
		   
		   nlist.add(res);
		   //System.out.println(nlist.toString());
		   return nlist;  
		 }   
	
	public static void main(String[] args){
		String inputfile = null;
		try {
			ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
//			triangle.add(Arrays.asList(7));
//			triangle.add(Arrays.asList(6,3));
//			triangle.add(Arrays.asList(3,8,5));
//			triangle.add(Arrays.asList(11,2,10,9));
			
			//Obtain Values from input file but first check if arguments were passed
			
			try{
				inputfile=args[0];
			}catch(Exception e){
				System.out.println("No file passed in command line");
				return;
			}
			
			
			triangle = getValues(inputfile); //Generate arraylist values from the input file
			
			ArrayList<Integer> result = minimumTotal(triangle); //Get minimum result
			String res = "Minimal path is: ";
			for (int i = 1; i < result.size(); i++) {
				res+=(result.get(i)-result.get(i-1))+" ";
				if(i==result.size()-1) {
					res+="= " + result.get(result.size()-1);
				} else {
					res+="+ ";
				}
			}
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An Error occured, Please address the above errors");
		}
		
		
	}

}
