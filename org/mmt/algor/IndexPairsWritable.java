package org.mmt.algor;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class IndexPairsWritable implements WritableComparable<IndexPairsWritable> {

	private long i;
	private long j;
	
	public IndexPairsWritable() {
		this(0, 0);
	}
	
	public IndexPairsWritable(long i, long j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	public boolean equals(Object object) {
		if(null == object)
			return false;
		if(!(object instanceof IndexPairsWritable))
			return false;
		IndexPairsWritable indexPairs = (IndexPairsWritable)object;
		long i1 = getI();
		long j1 = getJ();
		long i2 = indexPairs.getI();
		long j2 = indexPairs.getJ();
		
		return (i1 == i2 && j1 == j2);
	}
	
	@Override
	public int hashCode() {
		return 37 + (new Long(i)).hashCode() + (new Long(j)).hashCode();
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
		i = in.readLong();
		j = in.readLong();

	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(getI());
		out.writeLong(getJ());
	}

	public long getI() {
		return i;
	}

	public long getJ() {
		return j;
	}

	public void setI(long i) {
		this.i = i;
	}

	public void setJ(long j) {
		this.j = j;
	}

	@Override
	public int compareTo(IndexPairsWritable object) {
		Long i1 = getI();
		Long j1 = getJ();
		Long i2 = object.getI();
		Long j2 = object.getJ();
		
		int result = i1.compareTo(i2);
		if(0 == result) {
			return j1.compareTo(j2);
		}
		
		return result;
	}
}
