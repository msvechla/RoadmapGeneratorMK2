import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class RMGenerator {

	public static void generateRoadmaps(List<Project> projectList) {
		
		//STEP1 Generate SingleContainers
		for(Project p : projectList){
			
			//Für jedes Projekt einen Container erstellen
			HashSet<Integer> implementedProjectIDs = new HashSet<Integer>();
			implementedProjectIDs.add(p.id);
			RMContainer rmc = new RMContainer(false, implementedProjectIDs);
			
			//Neue Roadmap für implementation in jeder Periode anlegen
			for(int period = 0; period < Main.COUNT_PERIODS - p.numberOfPeriods+1; period++){
				
				Project[][] roadmap = new Project[Main.COUNT_PERIODS][Main.COUNT_PROJECTS_MAX_PER_PERIOD];
				
				for(int i = 0; i < Main.COUNT_PERIODS;i++){
					if((i<period) || (i> (period + p.numberOfPeriods-1))){
						//Kein Projekt durchführen
					}else{
						//Projekt wird implementiert
						roadmap[i][0] = p;
					}
				}
				rmc.addRoadMap(new RoadMap(roadmap,implementedProjectIDs));
			}
			
		}
		
		//STEP2 Generate initial CombinedContainers
		
		
		List<List<RMContainer>> lstSingleCombined = new ArrayList<List<RMContainer>>();
		lstSingleCombined.add(RMContainer.lstRMContainerSingle);
		lstSingleCombined.add(RMContainer.lstRMContainerCombined);
		
		for(List<RMContainer>lstRMContainer : lstSingleCombined){
			
			for(int i=0;i<lstRMContainer.size();i++){
				RMContainer rmcSingle2 = lstRMContainer.get(i);
				
				for(RMContainer rmcSingle : RMContainer.lstRMContainerSingle){
					
					//Falls die Kombination noch nicht generiert wurde -> generieren
					HashSet<Integer> implementedProjectIDs = new HashSet<Integer>();
					implementedProjectIDs.addAll(rmcSingle.getImplementedProjects());
					implementedProjectIDs.addAll(rmcSingle2.getImplementedProjects());
					
					if(!RMContainer.lstCombinedProjectIDs.contains(implementedProjectIDs)){
						//Container erstellen
						RMContainer rmcCombined = null;
						
						for(RoadMap rmSingle: rmcSingle.getLstRM()){
							for(RoadMap rmSingle2: rmcSingle2.getLstRM()){
								
								//Die beiden Roadmaps kombinieren
								Project[][] roadmap = combineRoadMaps(rmSingle,rmSingle2);
								if(roadmap != null){
									
									if(rmcCombined == null) rmcCombined = new RMContainer(true, implementedProjectIDs);
									rmcCombined.addRoadMap(new RoadMap(roadmap,implementedProjectIDs));
								}

							}
						}
						
					}
					
				}
				
			}
			
		}
		
		

		

	}

	private static Project[][] combineRoadMaps(RoadMap rmSingle,
			RoadMap rmSingle2) {
		
		Project[][] rmCombined = new Project[Main.COUNT_PERIODS][Main.COUNT_PROJECTS_MAX_PER_PERIOD];
		
		for(int period = 0; period<rmSingle.getRMArray().length;period++){
			
			//Alle Projekte aus beiden RoadMaps pro periode zusammenfassen
			HashSet<Project> projectsInPeriod = new HashSet<Project>();
			projectsInPeriod.addAll(Arrays.asList(rmSingle.getRMArray()[period]));
			projectsInPeriod.addAll(Arrays.asList(rmSingle2.getRMArray()[period]));
			projectsInPeriod.remove(null);
		
			//Wenn die Maximale anzahl an Projekten per Periode nicht überschritten wurde -> abspeichern
			if(projectsInPeriod.size() <= Main.COUNT_PROJECTS_MAX_PER_PERIOD){
				int i=0;
				for(Project p : projectsInPeriod){
					rmCombined[period][i] =  p;
					i++;
				}
			}else{
				return null;
			}
		}

		return rmCombined;
	}

	// Gibt anhand des Project Slots die Periode zurück (0 = 1. Periode, 1 = 2.
	// Periode...)
	private static int calculatePeriod(int index) {
		double dIndex = (double) index;
		return (int) (Math.ceil(dIndex / Main.COUNT_PROJECTS_MAX_PER_PERIOD) - 1);
	}

}
