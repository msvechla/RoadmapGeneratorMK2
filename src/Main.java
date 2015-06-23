import java.util.ArrayList;
import java.util.HashSet;


public class Main {
	
	public static final int COUNT_PERIODS = 3;
	public static final int COUNT_PROJECTS_MAX_PER_PERIOD = 4;
	
	
	public static void main(String[] args) {

		new Project("Project 1");
		new Project("Project 2");
		new Project("Project 3");
		new Project("Project 4");
		new Project("Project 5");
		new Project("Project 6");
		new Project("Project 7");
		new Project("Project 8");
		new Project("Project 9");

		
		long millisStart = System.currentTimeMillis();
		RMGenerator.generateRoadmaps(Project.projectList);
		long millisFinish = System.currentTimeMillis();
		
		
//		for(RMContainer rmc : RMContainer.lstRMContainerSingle){
//			System.out.println("");
//			for(RoadMap rm: rmc.getLstRM()){
//				System.out.println(rm);
//			}
//		}
//		
//		for(RMContainer rmc : RMContainer.lstRMContainerCombined){
//			System.out.println("");
//			for(RoadMap rm: rmc.getLstRM()){
//				System.out.println(rm);
//			}
//		}
		
		System.out.println(RMContainer.countRoadMapsGenerated+" Roadmaps generated in "+(millisFinish-millisStart)+"ms");
		
	}
}
