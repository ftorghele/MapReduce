package org.mmt.algor;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AlgorJob {

	public static void main(String[] args) throws Throwable {

		Job job = new Job();
		job.setJarByClass(AlgorJob.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(AlgorMapper.class);
		job.setReducerClass(AlgorReducer.class);
		
		job.setMapOutputKeyClass(IndexPairsWritable.class);
		job.setMapOutputValueClass(DoublePairsWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
