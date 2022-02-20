package mizutani_lab.groupcreater.model;

import java.io.Serializable;
import java.util.ArrayList;

public class EachGroupResult implements Serializable{

	public int group;
	public StudentData sd;
	public double groupPerformance;
	public GroupingResult gr;
	public ArrayList<StudentResult> sr = new ArrayList<StudentResult>();
}
