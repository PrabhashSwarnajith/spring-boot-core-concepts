package lk.zerocode.api.controller;

import lk.zerocode.api.service.StudentReadCommittedService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReadCommittedStudentController {

    private final StudentReadCommittedService studentService;

    @GetMapping("/create")
    public void test() {
        studentService.createStudents();
    }

    @GetMapping("/read-uncommitted")
    public void readUnCommitted() {
        studentService.getAllUncommittedStudents();
    }

    @GetMapping("/read-committed")
    public void readCommitted() {
        studentService.getAllCommittedStudents();
    }
}
