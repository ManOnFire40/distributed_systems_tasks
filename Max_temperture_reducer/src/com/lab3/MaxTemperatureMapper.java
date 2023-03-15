package com.lab3;

 

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
	  
	  private static final int MISSING = -1;

	  @Override
	  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
 
		  String Line=value.toString();
		  String[]x=Line.split(",");
		  String[]Date=x[0].split("-");
		  String year=Date[0];
		  Float Temp=Float.parseFloat(x[2]);
		  
		  if(Temp!=MISSING) 
		  {
			  Text y=new Text(year);
			  FloatWritable t=new FloatWritable(Temp);
			  context.write(y, t);
		  }
		  
		  
		  
	  }
	}