package camp.model;

public class Student {
    private String studentId;
    private String studentName;
    private String condition;

    public Student(String seq, String studentName ,String condition) {
        this.studentId = seq;
        this.studentName = studentName;
        this.condition = condition;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCondition() { return condition; }

    // Setter
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
}
