package opd.repository.controller;

import lombok.extern.slf4j.Slf4j;
import opd.repository.exception.LectionNotFoundException;
import opd.repository.exception.StudentNotFoundException;
import opd.repository.repository.LectionRepository;
import opd.repository.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spbstu.opd.business.ReportType;
import ru.spbstu.opd.business.model.Lection;
import ru.spbstu.opd.business.model.Student;
import ru.spbstu.opd.business.service.EmailService;
import ru.spbstu.opd.business.service.ReportService;
import ru.spbstu.opd.business.service.SmsService;

import java.util.List;

@RestController
@Slf4j
public class ReportController {
    @Autowired
    ReportService reportService;

    @Autowired
    SmsService smsService;

    @Autowired
    EmailService emailService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LectionRepository lectionRepository;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("checkAttendance")
    void checkAttendance() {
        List<Student> studentList = studentRepository.findAll().stream()
                .map(x -> modelMapper.map(x, Student.class))
                .toList();
        List<Lection> lections = lectionRepository.findAll().stream()
                .map(x -> modelMapper.map(x, Lection.class)).toList();
        emailService.checkAttending(studentList, lections);
    }

    @GetMapping("checkMarks")
    void checkMarks() {
        List<Student> studentList = studentRepository.findAll().stream()
                .map(x -> modelMapper.map(x, Student.class))
                .toList();
        List<Lection> lections = lectionRepository.findAll().stream()
                .map(x -> modelMapper.map(x, Lection.class)).toList();
        smsService.checkMarks(studentList, lections);
    }

    @GetMapping("getStudentReport")
    String getStudentReport(@RequestParam String studentName, @RequestParam String reportTypeStr) throws StudentNotFoundException {
        Student student = modelMapper.map(studentRepository.findByName(studentName), Student.class);
        if (student == null) {
            log.error("Студент {} не найден!", studentName);
            throw new StudentNotFoundException(studentName);
        }
        List<Lection> lections = lectionRepository.findAll().stream()
                .map(x -> modelMapper.map(x, Lection.class)).toList();
        return "<xmp>" + reportService.generateStudentReport(student, lections, ReportType.valueOf(reportTypeStr)) + "</xmp>";
    }

    @GetMapping("getLectionReport")
    String getLectionReport(@RequestParam String lectionName, @RequestParam String reportTypeStr) throws LectionNotFoundException {
        Lection lection = modelMapper.map(lectionRepository.findByName(lectionName), Lection.class);
        if (lection == null) {
            log.error("Лекций {} не найдена!", lectionName);
            throw new LectionNotFoundException(lectionName);
        }
        return "<xmp>" + reportService.generateLectionReport(lection, ReportType.valueOf(reportTypeStr)) + "</xmp>";
    }

}
