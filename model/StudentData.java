/**
 * 学生の情報
 */
package mizutani_lab.groupcreater.model;

import java.io.Serializable;

public class StudentData implements Serializable{

	public String id;
	public String name;
	public PersonalTestData pt;
	public PMTestScore pmTestData;
	public AttendanceRate ar;
	public Performance p;
	public SubmissionRate sr;
	public String role;
}
