package mo_1;

 

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
public class avg_salaries {

	  public static void main(String[] args) throws Exception {
	    if (args.length != 2) {
	      System.err.println("Usage: avg_salaries <input path> <output path>");//printing message in red in case of the arguments exceeds or below 2 to notify by error
	      System.exit(-1);//terminating because of the error we just clarified in previous line
	    }

	    Job job = new Job();//creating a job
	    job.setJarByClass(avg_salaries.class);//setting the class of the job
	    job.setJobName("average_salary");//setting the name of the job

	    FileInputFormat.addInputPath(job, new Path(args[0]));//taking the name of input file
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));//taking the name of output file

	    job.setMapperClass(avg_salaries_Mapper.class);//setting mapper class of the job
	    job.setReducerClass(avg_salaries_Reducer.class);//setting reducer class of the job

	    job.setOutputKeyClass(Text.class);//setting the Key class type
	    job.setOutputValueClass(FloatWritable.class);//setting the value class type

	    System.exit(job.waitForCompletion(true) ? 0 : 1);//checking if there is running job or not to prevent conflict on resources
	  }//Greatest job salary Principal Data Engineer	328333.34

	}