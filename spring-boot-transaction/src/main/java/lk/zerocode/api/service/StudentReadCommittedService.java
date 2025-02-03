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
public class StudentReadCommittedService {

    private final StudentRepository studentRepository;

    @Transactional
    public void createStudents() {

        Student student1 = new Student();
        student1.setName("Kamal");

        Student student2 = new Student();
        student2.setName("Nimal");

        studentRepository.saveAll(List.of(student1, student2));
        try {
            Thread.sleep(45000);
        } catch (InterruptedException exception) {
            System.out.println("interrupted");
        }
        System.out.println("save all students completed");
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void getAllCommittedStudents() {

        List<Student> studentList = studentRepository.findAll();
        System.out.println("Isolation.READ_COMMITTED : student count " + studentList.size());

    }


    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void getAllUncommittedStudents() {

        List<Student> studentList = studentRepository.findAll();
        System.out.println("Isolation.READ_UNCOMMITTED : student count " + studentList.size());

    }
}
