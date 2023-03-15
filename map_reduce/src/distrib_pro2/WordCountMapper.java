package distrib_pro2;

  
	import java.io.IOException;
	import java.util.StringTokenizer;
	import org.apache.hadoop.io.IntWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapreduce.Mapper;
	import org.apache.hadoop.io.LongWritable;
	public class WordCountMapper extends Mapper <LongWritable, Text, Text, IntWritable>
	{
	private Text outputkey = new Text();
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
	String line = value.toString();  
	String []words=line.split(" ");
	for(String word:words) 
	{
		String wordLow=word.toLowerCase().trim();
		context.write(new Text(wordLow), new IntWritable(1));
	}
 
	}
	}
	


