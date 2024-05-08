package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
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

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
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

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // ----이인빈
    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : studentStore) {
            System.out.println("고유 번호: " + student.getStudentId());
            System.out.println("이름 : " + student.getStudentName());
            System.out.println();
            System.out.println();
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // --------- 어동선 --------
    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        // 기능 구현 (조회할 특정 과목)
        System.out.println("조회할 수강생 이름을 입력하세요: ");
        String studentName = sc.next();


        System.out.println("고유번호를 입력하세요: ");
        String studentId = sc.next();

        Student student = findStudentByName(studentName, studentId);

        System.out.print("조회할 과목 이름을 입력하세요: ");
        String subjectName = sc.next();

        Subject subject = findSubjectByName(subjectName);

        if (studentName == null) {
            System.out.println("해당 이름의 수강생이 존재하지 않습니다.");
            return;
        }

        if (studentId == null) {
            System.out.println("해당 고유번호가 존재하지 않습니다.");
        }

        if (subjectName == null) {
            System.out.println("해당 이름의 과목이 존재하지 않습니다.");
            return;
        }

        System.out.println(studentName + " 수강생의 " + subjectName + " 과목 회차별 등급을 조회합니다...");

        for (int i = 1; i <= 10; i++) {
            Score score = findScoreByStudentAndSubject(student, subject, i);
            if (score != null) { // 해당 회차에 대한 점수가 존재하는 경우에만 등급 계산
                assert subject != null;
                String grade = calculateGrade(score.getScore(), subject.getSubjectType());
                System.out.println(i + "회차 " + " 등급: " + grade);
            } else {
                System.out.println(i + "회차 " + " 등급: 점수 없음");
            }
        }
    }

    // 필수, 선택 과목 분류 후 등급 값 리턴 메서드
    private static String calculateGrade(int score, String subjectType) {
        if (subjectType.equals(SUBJECT_TYPE_CHOICE)) {
            return calculateChoiceSubjectGrade(score);
        } else if (subjectType.equals(SUBJECT_TYPE_MANDATORY)) {
            return calculateMandatorySubjectGrade(score);
        } else {
            return "등급 계산 기준이 없습니다.";
        }
    }

    // 필수 과목 등급 계산
    private static String calculateMandatorySubjectGrade(int score) {
        if (score >= 95) {
            return "A";
        } else if (score >= 90) {
            return "B";
        } else if (score >= 80) {
            return "C";
        } else if (score >= 70) {
            return "D";
        } else if (score >= 60) {
            return "F";
        } else {
            return "N";
        }
    }

    // 선택 과목 등급 계산
    private static String calculateChoiceSubjectGrade(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else if (score >= 50) {
            return "F";
        } else {
            return "N";
        }
    }

    // 해당 이름의 수강생, 고유번호를 찾아서 반환하는 메서드
    private static Student findStudentByName(String studentName, String studentId) {
        for (Student student : studentStore) {
            if (student.getStudentName().equals(studentName)) {
                return student;
            }
        }
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null; // 해당 이름의 수강생이 없는 경우
    }

    // 해당 이름의 과목을 찾아서 반환하는 메서드
    private static Subject findSubjectByName(String subjectName) {
        for (Subject subject : subjectStore) {
            if (subject.getSubjectName().equals(subjectName)) {
                return subject;
            }
        }
        return null; // 해당 이름의 과목이 없는 경우
    }

    private static Score findScoreByStudentAndSubject(Student student, Subject subject, int round) {
        for (Score score : scoreStore) {
            if (score.getSubjectId().equals(subject.getSubjectId()) && score.getStudentId().equals(student.getStudentId())) {
                // 해당 수강생과 과목에 해당하는 점수인 경우
                // 여기에서 점수가 몇 회차인지 확인해야 함
                // 여기에서 점수가 몇 회차인지 확인해야 함
                // 이 부분은 해당 메서드의 구현에 따라 달라질 수 있음
                // 이 예시에서는 getRound() 메서드를 이용하여 점수의 회차를 가져옴
                if (score.getRound() == round) {
                    return score;
                }
            }
        }
        return null; // 해당 수강생과 과목에 해당하는 회차의 점수가 없는 경우

    }
    // --------- 어동선 --------

} // 맨 마지막