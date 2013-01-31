package org.mmt.algor;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.commons.math.stat.correlation.*;

public class AlgorReducer extends
		Reducer<IndexPairsWritable, DoublePairsWritable, Text, Text> {

	@Override
	protected void reduce(IndexPairsWritable key, Iterable<DoublePairsWritable> values,
			Context context) throws IOException, InterruptedException {
		
		double[] ratings_x = new double[100];
		double[] ratings_y = new double[100];
		
		int i = 0;
		
		for(DoublePairsWritable pairs : values) {
			ratings_x[i] = pairs.getI();
			ratings_y[i] = pairs.getJ();
			i++;
		}

		double pearson = new PearsonsCorrelation().correlation(ratings_x, ratings_y);
		double spearman = new SpearmansCorrelation().correlation(ratings_x, ratings_y);

		context.write(new Text(key.getI() + "\t" + key.getJ()), new Text(pearson + "\t" + spearman));
	}
}
