package org.mmt.algor;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class DoublePairsWritable implements Writable {

	private double i;
	private double j;
	
	public DoublePairsWritable() {
		this(0.0d, 0.0d);
	}
	
	public DoublePairsWritable(double i, double j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	public int hashCode() {
		return 37 + (new Double(i)).hashCode() + (new Double(j)).hashCode();
	}
	
	@Override
	public String toString() {
		return (new StringBuilder())
				.append('{')
				.append(getI())
				.append(',')
				.append(getJ())
				.append('}')
				.toString();
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		i = in.readDouble();
		j = in.readDouble();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeDouble(getI());
		out.writeDouble(getJ());
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public double getJ() {
		return j;
	}

	public void setJ(double j) {
		this.j = j;
	}
}
