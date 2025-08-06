package controller;

import entity.Student;
import service.StudentService;
import service.StudentServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name="studentBean")
@ViewScoped
public class StudentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Student student;
    private List<Student> studentList;
    private StudentService service;
    private List<String> subjectList;
    private Student selectedStudent;

    public StudentBean() {
        student = new Student();
        selectedStudent = new Student();
        subjectList = new ArrayList<>();
    }

     @PostConstruct
    public void init() {
        try {
            service = new StudentServiceImpl();
            loadStudents();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Error initializing service: " + e.getMessage()));
        }
    }

    public void loadStudents() {
        studentList = service.findAll();
    }

    public void saveStudent() {
        service.save(student);
        student = new Student();  
        loadStudents();
    }

    public void deleteStudent(Student stu) {
        service.delete(stu);
        loadStudents();
    }

    public void editStudent(Student stu) {
        selectedStudent = stu;
    }

    public void updateStudent() {
        service.update(selectedStudent);
        selectedStudent = new Student();
        loadStudents();
    }

     public void onClassChange() {
        subjectList.clear();
        if (student.getStudentClass().equals("Science")) {
            subjectList.addAll(Arrays.asList("Physics", "Chemistry", "Biology"));
        } else if (student.getStudentClass().equals("Commerce")) {
            subjectList.addAll(Arrays.asList("Accounting", "Economics", "Business Studies"));
        } else if (student.getStudentClass().equals("Arts")) {
            subjectList.addAll(Arrays.asList("History", "Geography", "Political Science"));
        }
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<String> subjectList) {
        this.subjectList = subjectList;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
}
