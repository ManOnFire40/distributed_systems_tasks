package com.lab3;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			throws IOException, InterruptedException {
		float maxTemperature = -1; // Note that the given data contains only positive Temp values

		for (FloatWritable value : values) {
			if (value.get() > maxTemperature)
				maxTemperature = value.get();
		}
		context.write(key, new FloatWritable(maxTemperature));
	}

}
