package POI_ex;

public class Student 
{
	int rollno;
	String name;
	int physics, chemistry,maths,biology,english,hindi;
	
	public Student() {
		
	}
	public Student(int rollno,String name, int physics, int chemistry, int english,
			int hindi,int maths,int biology) {
		
		this.rollno= rollno;
		this.name = name;
		this.physics = physics;
		this.chemistry = chemistry;
		this.maths = maths;
		this.biology = biology;
		this.english = english;
		this.hindi = hindi;
	}

	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhysics() {
		return physics;
	}
	public void setPhysics(int physics) {
		this.physics = physics;
	}
	public int getChemistry() {
		return chemistry;
	}
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}
	public int getMaths() {
		return maths;
	}
	public void setMaths(int maths) {
		this.maths = maths;
	}
	public int getBiology() {
		return biology;
	}
	public void setBiology(int biology) {
		this.biology = biology;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getHindi() {
		return hindi;
	}
	public void setHindi(int hindi) {
		this.hindi = hindi;
	}
}
