import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.commons.lang.math.NumberUtils;

public class MoneyCounter
{

	public static class TokenizerMapper extends Mapper<Object, Text, Text, DoubleWritable>
	{
		private DoubleWritable val = new DoubleWritable();
		private Text word = new Text();

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

      		String line = value.toString();
        	String[] air = line.split(" *, *(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        	
        	if(air.length > 1)
        	{
            	String a = air[0].replaceAll("\"", "");
            	String b = air[7].replaceAll("\"", "");
            	
		    if(NumberUtils.isNumber(b)) 
		    {
                	double d = Double.parseDouble(b);
                	val.set(d);
                	word.set(a);
                	context.write(word, val);
		    }
        	}
        	
    	}
    	
  	}

  	public static class DoubleSumReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable> 
  	{

	    private DoubleWritable result = new DoubleWritable();

    	public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException 
    	{
      		double sum = 0;
      		for (DoubleWritable val : values) 
      		{
        		sum += val.get();
      		}
      		result.set(sum);
      		context.write(key, result);
    	}
    	
  	}

  public static void main(String[] args) throws Exception 
  {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "money counter");
    job.setJarByClass(MoneyCounter.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(DoubleSumReducer.class);
    job.setReducerClass(DoubleSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(DoubleWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
