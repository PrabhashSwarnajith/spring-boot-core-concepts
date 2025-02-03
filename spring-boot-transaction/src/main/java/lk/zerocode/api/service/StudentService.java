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
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public void create() {
        System.out.println("start another transaction to save 2 students");
        Student student3 = new Student();
        student3.setName("Mala");

        Student student4 = new Student();
        student4.setName("Champa");

        studentRepository.saveAll(List.of(student3, student4));
        System.out.println("completed transaction by saving 2 students");
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void read(Integer index) {
        System.out.println("started the transaction " + index);
        List<Student> student = studentRepository.findAll();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println("interrupted3");
        }
        System.out.println("completed the transaction " + index);
    }
}
