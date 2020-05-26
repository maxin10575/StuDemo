package xmlread;
public class Emp {
		private int id;
		private int age;
		private String name;
		private String gender;
		private int salary;
		
		public Emp(int id,String name,int age,String gender,int salary){
			super();
			this.id = id;
			this.age =age;
			this.name =name;
			this.gender = gender;
			this.salary = salary;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public int getSalary() {
			return salary;
		}
		public void setSalary(int salary) {
			this.salary = salary;
		}
		public String toString(){
			return name+age+gender+salary+id;
		}
}
