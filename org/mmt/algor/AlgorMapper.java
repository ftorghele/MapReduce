package org.mmt.algor;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

import au.com.bytecode.opencsv.*;

public class AlgorMapper extends
		Mapper<LongWritable, Text, IndexPairsWritable, DoublePairsWritable> {
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		try {

			CSVParser parser = new CSVParser();
			String[] currentLine = parser.parseLine(value.toString());

			int size = currentLine.length;

			for (int i = 0; i < size; i++) {
				for (int j = i + 1; j < size; j++) {
					IndexPairsWritable newKey = new IndexPairsWritable(i, j);
					DoublePairsWritable newValue = new DoublePairsWritable(
							Double.parseDouble(currentLine[i]),
							Double.parseDouble(currentLine[j]));
					context.write(newKey, newValue);
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
