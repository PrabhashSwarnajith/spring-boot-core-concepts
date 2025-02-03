package lk.zerocode.api.controller;

import lk.zerocode.api.service.RepeatableReadStudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RepeatableReadStudentController {

    private final RepeatableReadStudentService repeatableReadStudentService;

    @GetMapping("/create2")
    public void test() {
        repeatableReadStudentService.createStudents();
    }


    @GetMapping("/read-repeatable")
    public void readUnCommitted() {
        repeatableReadStudentService.getAllStudents2();
    }
}
