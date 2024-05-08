package camp.model;

import java.util.ArrayList;
import java.util.List;

public class InitData {
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    //condition 관리 필드
    private static final String CONDITION_RED = "Red";
    private static final String CONDITION_GREEN = "Green";
    private static final String CONDITION_YELLOW = "Yellow";

    //Getter
    public static List<Student> getStudentStore() {
        return studentStore;
    }

    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }

    public static List<Score> getScoreStore() {
        return scoreStore;
    }

    public static String getSubjectTypeMandatory() {
        return SUBJECT_TYPE_MANDATORY;
    }

    public static String getSubjectTypeChoice() {
        return SUBJECT_TYPE_CHOICE;
    }
    public static String getIndexTypeStudent(){
        return INDEX_TYPE_STUDENT;
    }
    public static String getIndexTypeSubject(){
        return INDEX_TYPE_SUBJECT;
    }

    public static String getIndexTypeScore(){
        return INDEX_TYPE_SCORE;
    }

    public static String getConditionRed() { return CONDITION_RED; }
    public static String getConditionGreen() { return CONDITION_GREEN; }
    public static String getConditionYellow() { return CONDITION_YELLOW; }


    //Setter
    public static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = new ArrayList<>();
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    public static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }
}
