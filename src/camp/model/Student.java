package camp.model;

public class Student {
    private String studentId;
    private String studentName;
    private String condition = InitData.getConditionYellow();

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
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
