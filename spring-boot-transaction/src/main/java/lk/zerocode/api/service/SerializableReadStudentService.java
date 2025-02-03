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
public class SerializableReadStudentService {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public void getAllStudents() {
        for (int index = 1; index <= 10; index++) {
            studentService.read(index);
        }
    }


//    //here you have the phantom read issue
//    @Transactional(isolation = Isolation.REPEATABLE_READ)
//    public void getAllStudents2() {
//
//        List<Student> studentList = studentRepository.findAll();
//        System.out.println("Attempt 1 : Isolation.REPEATABLE_READ : student count " + studentList.size());
//
//        //phantom read happens because of this. new transaction is started and completed within the main/parent transaction
//        studentService.create();
//        studentList = studentRepository.findAll();
//        System.out.println("Attempt 2 : Isolation.REPEATABLE_READ : student count " + studentList.size());
//
//
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException ex) {
//            System.out.println("interrupted2");
//        }
//
//        System.out.println("re-checking all students again");
//        studentList = studentRepository.findAll();
//        System.out.println("Attempt 3 : Isolation.REPEATABLE_READ : student count " + studentList.size());
//    }
}
