package distrib_pro2;

 

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
		  
			//task2
		
		  if(salary!=MISSING) 
		  {
			  Text c=new Text(company_loc);//saving company location 
			  FloatWritable s=new FloatWritable(salary);//saving salary 
			  context.write(c, s);//writing country and salary into file
		  }
			//End task2
		  
		  
		  
	  }
	}