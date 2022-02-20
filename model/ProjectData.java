package mizutani_lab.groupcreater.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectData implements Serializable{

	public String projectName;
	public ArrayList <StudentData> studentData=new ArrayList<StudentData>();
	public ArrayList <GroupingResult> gr = new ArrayList<GroupingResult>();
	public DivideRoleData dr;
	public WeightData w;
	public SettingData sData;
	public String saveDirectory;
}
