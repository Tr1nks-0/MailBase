package com.tr1nks.model.utils;

import com.tr1nks.model.entities.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * генератор файлов
 */
@Component
public class FileGenerator {
    private static final String SLH = "/";
    @Resource
    PdfGenerator pdfGenerator;

    /**
     *
     * @param outputStream
     * @param facultyEntityMap
     */
    public void writePDFArchive(OutputStream outputStream, HashMap<FacultyEntity, HashMap<Object, ArrayList<PersonEntity>>> facultyEntityMap) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            HashMap<Object, ArrayList<PersonEntity>> arrayListHashMap;
            ArrayList<PersonEntity> arr;
            for (FacultyEntity faculty : facultyEntityMap.keySet()) {
                arrayListHashMap = facultyEntityMap.get(faculty);
                for (Object groupOrCathedra : arrayListHashMap.keySet()) {
                    arr = arrayListHashMap.get(groupOrCathedra);
                    for (PersonEntity person : arr) {
                        if (groupOrCathedra instanceof GroupEntity) {
                            zipOutputStream.putNextEntry(new ZipEntry(faculty.getAbbr() + SLH + ((GroupEntity) groupOrCathedra).getChiper() + SLH + person.getSurname() + "_" + person.getName()));
                        } else if (groupOrCathedra instanceof CathedraEntity) {
                            zipOutputStream.putNextEntry(new ZipEntry(faculty.getAbbr() + SLH + ((CathedraEntity) groupOrCathedra).getAbbr() + SLH + person.getSurname() + "_" + person.getName()));
                        }

                        zipOutputStream.write(pdfGenerator.create(person));//запись PDF todo
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param studentEntityList
     * @return
     */
    //фио группа логин пароль, группировка в папки по группам
    public HashMap<FacultyEntity, HashMap<GroupEntity, ArrayList<StudentEntity>>> sortStudentsForPDF(List<StudentEntity> studentEntityList) {
        HashMap<FacultyEntity, HashMap<GroupEntity, ArrayList<StudentEntity>>> facultyMap = new HashMap<>();
        HashMap<GroupEntity, ArrayList<StudentEntity>> groupMap;
        ArrayList<StudentEntity> students;
        for (StudentEntity student : studentEntityList) {
            if (facultyMap.containsKey(student.getGroup().getFaculty())) {
                groupMap = facultyMap.get(student.getGroup().getFaculty());
            } else {
                groupMap = new HashMap<>();
                facultyMap.put(student.getGroup().getFaculty(), groupMap);
            }
            if (groupMap.containsKey(student.getGroup())) {
                students = groupMap.get(student.getGroup());
            } else {
                students = new ArrayList<>();
                students = groupMap.put(student.getGroup(), students);
            }
            students.add(student);
        }
        return facultyMap;
    }

    /**
     *
     * @param teacherEntityList
     * @return
     */
    public HashMap<FacultyEntity, HashMap<CathedraEntity, ArrayList<TeacherEntity>>> sortTeacherForPDF(List<TeacherEntity> teacherEntityList) {
        HashMap<FacultyEntity, HashMap<CathedraEntity, ArrayList<TeacherEntity>>> facultyMap = new HashMap<>();
        HashMap<CathedraEntity, ArrayList<TeacherEntity>> groupMap;
        ArrayList<TeacherEntity> teachers;
        for (TeacherEntity teacher : teacherEntityList) {
            if (facultyMap.containsKey(teacher.getCathedra().getFaculty())) {
                groupMap = facultyMap.get(teacher.getCathedra().getFaculty());
            } else {
                groupMap = new HashMap<>();
                facultyMap.put(teacher.getCathedra().getFaculty(), groupMap);
            }
            if (groupMap.containsKey(teacher.getCathedra())) {
                teachers = groupMap.get(teacher.getCathedra());
            } else {
                teachers = new ArrayList<>();
                teachers = groupMap.put(teacher.getCathedra(), teachers);
            }
            teachers.add(teacher);
        }
        return facultyMap;
    }
}
