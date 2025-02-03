package lk.zerocode.api.controller;

import lk.zerocode.api.service.SerializableReadStudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SerializableTransactionalStudentController {

    private final SerializableReadStudentService serializableReadStudentService;

    @GetMapping("/serializable")
    public void readUnCommitted() {
        serializableReadStudentService.getAllStudents();
    }
}
