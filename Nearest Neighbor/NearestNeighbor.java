import java.util.*;
import java.lang.Math;

//Author: Conor McGullam
public class NearestNeighbors<V> implements NearestNeighborInterface<V[], V> {
	private V target;
	//true for nearest, false for farthest
	private boolean nearest = true;
	private ArrayList<Double> doubPool = new ArrayList<Double>();
	private ArrayList<Integer> intPool = new ArrayList<Integer>();

	class DoubleComparatorNear implements Comparator<Double[]> {
		//The comparator used in the PriorityQueue when trying to find the nearest Double values.
		@Override
		public int compare(Double[] o1, Double[] o2) {
			return o1[1].compareTo(o2[1]) * -1;
		}
	}
	class DoubleComparatorFar implements Comparator<Double[]> {
		//The comparator used in the PriorityQueue when trying to find the farthest Double values.
		@Override
		public int compare(Double[] o1, Double[] o2) {
			return o1[1].compareTo(o2[1]);
		}
	}
	class IntegerComparatorNear implements Comparator<Integer[]> {
		//The comparator used in the PriorityQueue when trying to find the nearest Integer values.
		@Override
		public int compare(Integer[] o1, Integer[] o2) {
			return o1[1].compareTo(o2[1]) * -1;
		}
	}
	class IntegerComparatorFar implements Comparator<Integer[]> {
		//The comparator used in the PriorityQueue when trying to find the farthest Integer values.
		@Override
		public int compare(Integer[] o1, Integer[] o2) {
			return o1[1].compareTo(o2[1]);
		}
	}

	public NearestNeighbors(V target) {
		this.target = target;
	}

	public NearestNeighbors(V target, boolean nearest) {
		this.target = target;
		this.nearest = nearest;
	}

	@Override
	public void add(Object value) {
		if(value instanceof Double) {
			this.doubPool.add((Double)value);
		}
		if(value instanceof Integer) {
			this.intPool.add((Integer)value);
		}
	}

	@Override
	public void clear() {
		this.doubPool = new ArrayList<Double>();
		this.intPool = new ArrayList<Integer>();
	}

	@Override
	public void reset() {
		//not sure what this is for
		this.target = null;

	}

	@Override
	public void setTarget(Object target) {
		this.target = (V)target;
	}

	@Override
	public void setNearest(boolean nearest) {
		this.nearest = nearest;
	}

	/**
	 * Organize values to determine a maximum of three according to
	 * their distance from a target value.
	 * If 'nearest' is false then the most distance values are determined.
	 * If fewer values of type V are found than are specified, then
	 * the array is truncated to the actual size.
	 * @param value array of values to add to the pool of values
	 * @return array of values of type V truncated to the actual size
	 */
	@Override
	public V[] execute(V... value) {
		//Check if value array is Double[] or Integer[]
		if(value instanceof Double[]) {
			//Create Double array to hold three nearest/farthest values. Won't be used until the end.
			Double[] ans = new Double[3];
			//A copy of the "value" array that was passed into the method that has been cast as a Double array.
			Double[] vals = (Double[])value;
			for(Double val : vals) {
				this.doubPool.add(val);
			}
			//Check if calculating for nearest or farthest values.
			if(nearest) {
				PriorityQueue<Double[]> threeTargetsDist = new PriorityQueue<Double[]>(3, new DoubleComparatorNear());
				//iterating through pool of values
				for(Double x : doubPool) {
					//calculating distance from target
					Double distance = Math.abs((Double)target - x);
					//making sure my PriorityQueue is full before I start replacing values
					if(threeTargetsDist.size() != 3) {
						Double[] valDist = {x, distance};
						threeTargetsDist.add(valDist);
					}else if(threeTargetsDist.peek()[1] > distance) {
						//removing old value
						threeTargetsDist.poll();
						Double[] valDist = {x, distance};
						//adding in new, closer value
						threeTargetsDist.add(valDist);
					}
				}
				int count = 0;
				//Taking the values out of our PriorityQueue to be put in the Array that we need to return.
				while(threeTargetsDist.size() > 0) {
					Double[] temp = threeTargetsDist.poll();
					ans[count] = temp[0];
					count++;
				}
				//Trimming empty spaces out of the ans Array incase there are fewer than 3 nearest values.
				if(ans.length < 3) {
					Double[] tempAns = new Double[ans.length];
					for(int i = 0; i < ans.length; i++) {
						tempAns[i] = ans[i];
					}
					ans = tempAns;
				}
				//casting and returning our array
				return (V[])ans;
			}else {
				PriorityQueue<Double[]> threeTargetsDist = new PriorityQueue<Double[]>(3, new DoubleComparatorFar());
				for(Double x : doubPool) {
					Double distance = Math.abs((Double)target - x);
					if(threeTargetsDist.size() != 3) {
						Double[] valDist = {x, distance};
						threeTargetsDist.add(valDist);
					}else if(threeTargetsDist.peek()[1] < distance) {
						threeTargetsDist.poll();
						Double[] valDist = {x, distance};
						threeTargetsDist.add(valDist);
					}
				}
				int count = 0;
				while(threeTargetsDist.size() > 0) {
					Double[] temp = threeTargetsDist.poll();
					ans[count] = temp[0];
					count++;
				}
				if(ans.length < 3) {
					Double[] tempAns = new Double[ans.length];
					for(int i = 0; i < ans.length; i++) {
						tempAns[i] = ans[i];
					}
					ans = tempAns;
				}
			}
			return (V[])ans;
		}else if(value instanceof Integer[]) {
			Integer[] ans = new Integer[3];
			Integer[] vals = (Integer[])value;
			for(Integer val : vals) {
				this.intPool.add(val);
			}
			if(nearest) {
				PriorityQueue<Integer[]> threeTargetsDist = new PriorityQueue<Integer[]>(3, new IntegerComparatorNear());
				for(Integer x : intPool) {
					Integer distance = Math.abs((Integer)target - x);
					if(threeTargetsDist.size() != 3) {
						Integer[] valDist = {x, distance};
						threeTargetsDist.add(valDist);
					}else if(threeTargetsDist.peek()[1] > distance) {
						threeTargetsDist.poll();
						Integer[] valDist = {x, distance};
						threeTargetsDist.add(valDist);
					}
				}
				int count = 0;
				while(threeTargetsDist.size() > 0) {
					Integer[] temp = threeTargetsDist.poll();
					ans[count] = temp[0];
					count++;
				}
				if(ans.length < 3) {
					Integer[] tempAns = new Integer[ans.length];
					for(int i = 0; i < ans.length; i++) {
						tempAns[i] = ans[i];
					}
					ans = tempAns;
				}
				return (V[])ans;
			}else {
				PriorityQueue<Integer[]> threeTargetsDist = new PriorityQueue<Integer[]>(3, new IntegerComparatorFar());
				for(Integer x : intPool) {
					Integer distance = Math.abs((Integer)target - x);
					if(threeTargetsDist.size() != 3) {
						Integer[] valDist = {x, distance};
						threeTargetsDist.add(valDist);
					}else if(threeTargetsDist.peek()[1] < distance) {
						threeTargetsDist.poll();
						Integer[] valDist = {x, distance};
						threeTargetsDist.add(valDist);
					}
				}
				int count = 0;
				while(threeTargetsDist.size() > 0) {
					Integer[] temp = threeTargetsDist.poll();
					ans[count] = temp[0];
					count++;
				}
				if(ans.length < 3) {
					Integer[] tempAns = new Integer[ans.length];
					for(int i = 0; i < ans.length; i++) {
						tempAns[i] = ans[i];
					}
					ans = tempAns;
				}
			}
			return (V[])ans;
		}else {
			/*Just a return statement to satisfy the method in the case that the user doesn't
			pass in a Double[] or an Integer[].*/
			V[] ans = null;
			return ans;
		}
	}

}
