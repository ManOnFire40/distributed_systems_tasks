package mo_1;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class avg_salaries_Reducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			throws IOException, InterruptedException {
		//task1
		float avg_of_salaries = 0; 
		float total=0;
		int counter=0;
		for (FloatWritable value : values) {//looping on salaries to get the summision of them all and number of them
				total += value.get();
				counter++;
		}
		avg_of_salaries=total/counter;//calculating the average
		
		context.write(key, new FloatWritable(avg_of_salaries));//writing job and salary into file
		//End task1
		
		
	}

}