import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class RoadMap implements Comparable {
	public List<Project> projectSequence;
	public List<Integer> idSequence;
	public static int equalsCalls = 0;

	public RoadMap(List<Project> p) {
		this.projectSequence = p;
		this.sortSequence();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		int count = 0;
		for (Project p : projectSequence) {
			if ((count % Main.COUNT_PROJECTS_MAX_PER_PERIOD) == 0) {
				sb.append("[ ");
			}
			
			sb.append(p.name + " ");
			count++;

			if ((count % Main.COUNT_PROJECTS_MAX_PER_PERIOD) == 0) {
				sb.append("] ");
			}
		}
		return sb.toString();
	}
	
	public void sortSequence(){
		idSequence = new ArrayList<Integer>();
		
		for(int period=0;period<Main.COUNT_PERIODS;period++){
			int arr[] = new int[Main.COUNT_PROJECTS_MAX_PER_PERIOD];
			for(int s=0;s<Main.COUNT_PROJECTS_MAX_PER_PERIOD;s++){
				arr[s] = projectSequence.get(period*Main.COUNT_PROJECTS_MAX_PER_PERIOD+s).id;
			}
			Arrays.sort(arr);
			for(int i=0;i<arr.length;i++){
				idSequence.add(arr[i]);
			}
		}
	}

	//period = 0 -> 1st. Period, period = 1 -> 2nd. Period...
	public List<Project> getPeriod(int period) {
		List<Project> listPeriod = new ArrayList<Project>();

		for (int i = 0; i < Main.COUNT_PROJECTS_MAX_PER_PERIOD; i++) {
			listPeriod.add(projectSequence.get(period
					* Main.COUNT_PROJECTS_MAX_PER_PERIOD + i));
		}

		return listPeriod;
	}
	
	public HashSet<Integer> getIDsInPeriod(int period) {
		HashSet<Integer> hashPeriod = new HashSet<Integer>();

		for (int i = 0; i < Main.COUNT_PROJECTS_MAX_PER_PERIOD; i++) {
			hashPeriod.add(projectSequence.get(period
					* Main.COUNT_PROJECTS_MAX_PER_PERIOD + i).id);
		}

		return hashPeriod;
	}

//	public boolean equals(Object obj) {
//		equalsCalls++;
//		if (obj instanceof RoadMap) {
//			RoadMap rm = (RoadMap) obj;
//			
//			// Check every period
//			for (int period = 0; period < Main.COUNT_PERIODS; period++) {
//				if (!( (this.getPeriod(period).containsAll(rm.getPeriod(period))) && rm.getPeriod(period).containsAll(this.getPeriod(period)))) {
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
	
//	public boolean equals(Object obj) {
//		equalsCalls++;
//		if (obj instanceof RoadMap) {
//			RoadMap rm = (RoadMap) obj;
//			
//			// Check every period
//			for (int period = 0; period < Main.COUNT_PERIODS; period++) {
//				if (!(this.getIDsInPeriod(period).equals(rm.getIDsInPeriod(period)))) {
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
	
	
//	public int hashCode(){
//		int result=0;
//		int r = 3; //1779033703
//		
//		// Check every period
//		for (int period = 0; period < Main.COUNT_PERIODS; period++) {
//			int c = 0;
//			for(Integer id: this.getIDsInPeriod(period)){
//				c=c*(r+(2*id));
//			}
//			
//			c=c/2;
//			
//			result = 37*result+c;
//		}
//		return result;
//	}
	
	public boolean equals(Object obj) {
	equalsCalls++;
	if (obj instanceof RoadMap) {
		RoadMap rm = (RoadMap) obj;
		
		return this.idSequence.equals(rm.idSequence);
	}
	return false;
}

@Override
public int compareTo(Object arg0) {
	if(arg0 instanceof RoadMap){
		return this.idSequence.toString().compareTo(((RoadMap) arg0).idSequence.toString());
	}
	return 0;
}
	
}
