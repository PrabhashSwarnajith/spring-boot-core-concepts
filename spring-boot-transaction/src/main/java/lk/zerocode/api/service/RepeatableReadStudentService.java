package lk.zerocode.api.service;

import lk.zerocode.api.model.Student;
import lk.zerocode.api.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RepeatableReadStudentService {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Transactional
    public void createStudents() {

        Student student1 = new Student();
        student1.setName("Kamal");

        Student student2 = new Student();
        student2.setName("Nimal");

        Student student3 = new Student();
        student3.setName("Amal");

        Student student4 = new Student();
        student4.setName("Namal");

        studentRepository.saveAll(List.of(student1, student2, student3, student4));
        try {
            Thread.sleep(12000);
        } catch (InterruptedException exception) {
            System.out.println("interrupted1");
        }
        System.out.println("save all students completed");
    }

    //here you don't have the phantom read issue. no other transaction modifying the data
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void getAllStudents() {

        List<Student> studentList = studentRepository.findAll();
        System.out.println("Attempt 1 : Isolation.REPEATABLE_READ : student count " + studentList.size());

        try {
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            System.out.println("interrupted2");
        }

        
        System.out.println("re-checking all students again");
        studentList = studentRepository.findAll();
        System.out.println("Attempt 3 : Isolation.REPEATABLE_READ : student count " + studentList.size());
    }


    //here you have the phantom read issue
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void getAllStudents2() {

        List<Student> studentList = studentRepository.findAll();
        System.out.println("Attempt 1 : Isolation.REPEATABLE_READ : student count " + studentList.size());

        //phantom read happens because of this. new transaction is started and completed within the main/parent transaction
        studentService.create();
        studentList = studentRepository.findAll();
        System.out.println("Attempt 2 : Isolation.REPEATABLE_READ : student count " + studentList.size());


        try {
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            System.out.println("interrupted2");
        }

        System.out.println("re-checking all students again");
        studentList = studentRepository.findAll();
        System.out.println("Attempt 3 : Isolation.REPEATABLE_READ : student count " + studentList.size());
    }
}
