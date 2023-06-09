package com.lab3;

 

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
public class MaxTemperature {

	  public static void main(String[] args) throws Exception {
	    if (args.length != 2) {
	      System.err.println("Usage: MaxTemperature <input path> <output path>");
	      System.exit(-1);
	    }

	    Job job = new Job();
	    job.setJarByClass(MaxTemperature.class);
	    job.setJobName("Max temperature");

	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));

	    job.setMapperClass(MaxTemperatureMapper.class);
	    job.setReducerClass(MaxTemperatureReducer.class);

	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(FloatWritable.class);

	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	  }
	}
