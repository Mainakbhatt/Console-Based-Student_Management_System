package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = context.getBean("studentdao", StudentDao.class);
////       Student student = new Student(11104,"Dani Daniels","Miraj");
//       Student student=studentDao.getStudent(1104);
//       System.out.println("done.."+student);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Boolean go = true;
		while (go) {

			System.out.println(
					"***************************Welcome Student Management System********************************");
			System.out.println("Press 1 to add Student");
			System.out.println("Press 2 to display all Students");
			System.out.println("Press 3 to display single Student");
			System.out.println("Press 4 to delete a Student");
			System.out.println("Press 5 to update a Student");
			System.out.println("Press 6 to exit");

			try {

				int input = Integer.parseInt(br.readLine());
				switch (input) {
				case 1:
					System.out.println("Enter Student id: ");
					int stuid = Integer.parseInt(br.readLine());

					System.out.println("Enter Student Name: ");
					String stname = br.readLine();

					System.out.println("Enter Student City: ");
					String stCity = br.readLine();

					// Student student = new Student(stuid,stname,stCity);
					Student student = new Student();
					student.setStudentId(stuid);
					student.setStudentName(stname);
					student.setStudentCity(stCity);
					int result = studentDao.insert(student);
					System.out.println("Data entered" + result);
					break;
				case 2:
					System.out.println("***************************************");
					List<Student> allStud = studentDao.getAllStudent();
					for (Student s : allStud) {
						System.out.println("Id:" + s.getStudentId());
						System.out.println("Name:" + s.getStudentName());
						System.out.println("City:" + s.getStudentCity());
						System.out.println("-----------------------------------");
					}
					System.out.println("*******************************************");
					break;
				case 3:
					System.out.println("Enter the Student id: ");
					int uid = Integer.parseInt(br.readLine());
					Student st = studentDao.getStudent(uid);
					System.out.println("Id: " + st.getStudentId());
					System.out.println("Name: " + st.getStudentName());
					System.out.println("Id: " + st.getStudentCity());
					System.out.println("*********************************************");
					break;
				case 4:
					System.out.println("Enter Student id to delete :");
					int delid = Integer.parseInt(br.readLine());
					studentDao.deleteStudent(delid);
					System.out.println("Student Delted.............................");
					break;
				case 5:
					System.out.println("Enter Student id: ");
					int studid = Integer.parseInt(br.readLine());

					System.out.println("Enter Student Name: ");
					String studName = br.readLine();

					System.out.println("Enter Student City: ");
					String studCity = br.readLine();

					// Student student = new Student(stuid,stname,stCity);
					Student update = new Student();
					update.setStudentId(studid);
					update.setStudentName(studName);
					update.setStudentCity(studCity);
					studentDao.updateData(update);
					System.out.println("Data update........................");

					break;
				case 6:
					go = false;
					break;

				default:
					break;
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Invalid Choice Please Select the correct Option");
				System.out.println(e.getMessage());
			}
		}

	}
}
