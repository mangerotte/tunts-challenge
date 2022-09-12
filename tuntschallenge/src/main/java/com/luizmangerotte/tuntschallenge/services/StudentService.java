package com.luizmangerotte.tuntschallenge.services;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.luizmangerotte.tuntschallenge.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StudentService {
    List<Student> students = new ArrayList<>();
    List<List<Object>> listAllStudent = new ArrayList<>();

    public List<Student> ParseJsonDataForStudent(ValueRange result) {
        log.info("Receiving data from the request and transforming it into an object");
        for (int i = 0; i < result.getValues().size(); i++) {
            students.add(new Student(
                    Long.valueOf((String) result.getValues().get(i).get(0)),
                    (String) result.getValues().get(i).get(1),
                    Double.valueOf((String) result.getValues().get(i).get(2)),
                    Double.valueOf((String) result.getValues().get(i).get(3)),
                    Double.valueOf((String) result.getValues().get(i).get(4)),
                    Double.valueOf((String) result.getValues().get(i).get(5))));
        }
        return students;
    }
    public List<List<Object>> validationAndInsertListObject (ValueRange result) {
        double reproveNote = 50;
        double approve = 70;
        log.info("Analyzing data and changing object variables");
        for (Student student : ParseJsonDataForStudent(result)) {
            if (student.failedForMiss()) {
                student.setSituation("Reprovado");
                student.setNoteApproval(0.0);
            } else if (student.average() < reproveNote) {
                student.setSituation("Reprovado");
                student.setNoteApproval(0.0);
            } else if (student.average() >= reproveNote
                    && student.average() < approve) {
                student.setSituation("Exame Final");
                double finalGrade = (student.average() - approve);
                student.setNoteApproval(
                        Math.ceil((student.average() + finalGrade) / 2));
            } else {
                student.setSituation("Aprovado");
                student.setNoteApproval(0.0);
            }
            log.info("Adding the student object to an object list");
            listAllStudent.add(createRowDataStudent(student));
        }
        return listAllStudent;
    }
    public List<Object> createRowDataStudent(Student student) {
        log.info("Inserting student data into cells");
        List<Object> lineRaw = new ArrayList<>();
        lineRaw.add(student.getId());
        lineRaw.add(student.getName());
        lineRaw.add(student.getMiss());
        lineRaw.add(student.getTestScore1());
        lineRaw.add(student.getTestScore2());
        lineRaw.add(student.getTestScore3());
        lineRaw.add(student.getSituation());
        lineRaw.add(student.getNoteApproval());
        return lineRaw;
    }
}
