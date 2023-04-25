package mo_1;

 

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
public class avg_salaries_Mapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
	  
	  private static final int MISSING = -1;

	  @Override
	  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
 
		  String Line=value.toString();//spliting every line
		  String[]x=Line.split(",");//spliting line into parts
		  String year=x[0];//year
		  String job_title=x[1];//job_title
		  Float salary=Float.parseFloat(x[2]);//salary
		  String company_loc=x[3];//company_loc
		  
		
			//task1
		  if(salary!=MISSING) 
		  {
			  Text j=new Text(job_title);//saving job title 
			  FloatWritable s=new FloatWritable(salary);//saving job salary 
			  context.write(j, s);//writing job and salary into file
		  }
			//End task1
		  
		  
		  
	  }
	}