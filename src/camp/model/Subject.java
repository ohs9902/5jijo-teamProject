package camp.model;

public class Subject {
    private String subjectId;
    private String subjectName;
    private String subjectType;
    private String studentId;



    public Subject(String seq, String subjectName, String subjectType, String studentId) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.studentId = studentId;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public String getStudentId() {return studentId;}

}
