import java.util.ArrayList;
import java.util.List;


public class Project {
	public int id;
	public String name;
	public int numberOfPeriods;
	public static List<Project> projectList = new ArrayList<Project>();
	
	public Project(String name, int numberOfPeriods){
		this.name = name;
		projectList.add(this);
		this.id = projectList.size()-1;
		this.numberOfPeriods = numberOfPeriods;
	}
	
	public Project(String name){
		new Project(name, 1);
	}
	
	
	public String toString(){
		return this.name + " ID: "+id;
	}
	
	public static Project getProject(String name){
		for(Project p : Project.projectList){
			if(p.name.equals(name)){
				return p;
			}
		}
		return null;
	}

	public static List<Project> getMultiPeriodProjects() {
		List<Project> multiPeriodProjects = new ArrayList<Project>();
		
		for(Project p : projectList){
			if(p.numberOfPeriods > 1){
				multiPeriodProjects.add(p);
			}
		}
		return multiPeriodProjects;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Project){
			Project p = (Project)obj;
			return (p.id == this.id);
		}
		return false;
	}
}
