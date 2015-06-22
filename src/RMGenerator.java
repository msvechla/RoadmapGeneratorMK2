import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class RMGenerator {

	public static ArrayList<RoadMap> generateRoadmaps(List<Project> projectList) {

		ArrayList<RoadMap> rmList = new ArrayList<RoadMap>();
		List<Project> multiPeriodProjects = Project.getMultiPeriodProjects();
		return rmList;

		

	}

	// Gibt anhand des Project Slots die Periode zurück (0 = 1. Periode, 1 = 2.
	// Periode...)
	private static int calculatePeriod(int index) {
		double dIndex = (double) index;
		return (int) (Math.ceil(dIndex / Main.COUNT_PROJECTS_MAX_PER_PERIOD) - 1);
	}

}
