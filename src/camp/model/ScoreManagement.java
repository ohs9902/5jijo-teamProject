package camp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ScoreManagement {
    Scanner sc = new Scanner(System.in);
    //점수 등록
    public void createScore(){
        int score = 0;
        System.out.println("수강생 번호를 입력하세요 : ");
        String studentId = sc.next();
        studentId = validationStudentId(studentId);

        if(!studentId.equals("empty")){
            int count = 0;
            ArrayList<Subject> sub = new ArrayList<>();
            for (Subject subject : InitData.getSubjectStore()) {
                if(subject.getStudentId().equals(studentId)){
                    count++;
                    sub.add(subject);
                    System.out.println(count+"."+subject.getSubjectName() );
                }
            }

            System.out.println("과목을 입력하새요 : ");
            int selectNum = sc.nextInt();
            Subject sub2 = null;
            sub2 = sub.get(selectNum-1);
            int times = getTestTimes(sub2.getStudentId(),sub2.getSubjectId());
            System.out.println(sub2.getSubjectName());
            System.out.println("점수를 입력하세요 : ");
            score = sc.nextInt();
            char rank = calculateGrade(score, Objects.requireNonNull(sub2).getSubjectType());
            InitData.getScoreStore().add(new Score(InitData.sequence(InitData.getIndexTypeScore()),sub2.getSubjectId(),studentId,times,score,rank));

            System.out.println("점수 등록 완료!!");
            for (Score score1 : InitData.getScoreStore()) {
                System.out.println(score1.getScore());
                System.out.println(score1.getTimes());
                System.out.println(score1.getScoreId());

                System.out.println(score1.getRank());
            }
        }else{
            System.out.println("없는 학생번호 입니다.");
        }

    }

    //학생번호 검증
    public String validationStudentId(String studentId){
        for (Student student : InitData.getStudentStore()) {
            if(studentId.equals(student.getStudentId())){
                return studentId;
            }
        }
        return "empty";
    }


    public char calculateGrade(int score, String subjectType) {
        if (subjectType.equals(InitData.getSubjectTypeChoice())) {
            return calculateChoiceSubjectGrade(score);
        } else if (subjectType.equals(InitData.getSubjectTypeMandatory())) {
            return calculateMandatorySubjectGrade(score);
        } else {
            return 'X';
        }
    }
    // 필수 과목 등급 계산
    public char calculateMandatorySubjectGrade(int score) {
        if (score >= 95) {
            return 'A';
        } else if (score >= 90) {
            return 'B';
        } else if (score >= 80) {
            return 'C';
        } else if (score >= 70) {
            return 'D';
        } else if (score >= 60) {
            return 'F';
        } else {
            return 'N';
        }
    }

    // 선택 과목 등급 계산
    public char calculateChoiceSubjectGrade(int score) {
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else if (score >= 50) {
            return 'F';
        } else {
            return 'N';
        }
    }
    public void inquireRoundGradeBySubject (){
        System.out.println("수강생 번호를 입력하세요 : ");
        String studentId = sc.next(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        studentId = validationStudentId(studentId);
        if (!studentId.equals("empty")) {
            System.out.println("조회할 과목을 입력하세요 :");
            int count = 0;
            ArrayList<Subject> sub = new ArrayList<>();
            for (Subject subject : InitData.getSubjectStore()) {
                if(subject.getStudentId().equals(studentId)){
                    count++;
                    sub.add(subject);
                    System.out.println(count+"."+subject.getSubjectName());
                }
            }
            int selectNum = 0;
            selectNum = sc.nextInt();
            Subject sub2;

            if (selectNum < 1 || selectNum > sub.size()) {
                System.out.println("유효하지 않은 선택입니다. 다시 시도하세요.");
                return;  // 인덱스 범위 벗어남을 방지하기 위해 종료
            }
            sub2 = sub.get(selectNum-1);

            System.out.println(sub2.getSubjectName());


            //과목 유효성 검사
            for (Subject subject1 : InitData.getSubjectStore()) {
                if (sub2.getSubjectId().equals(subject1.getSubjectId())) {
                    break;
                }else{
                    sub2 = null;
                }
            }
            if (sub2 != null) {
                System.out.println("회차별 등급을 조회합니다...");

                for (Score score1 : InitData.getScoreStore()) {
                    if (score1.getStudentId().equals(studentId) && score1.getSubjectId().equals(sub2.getSubjectId())) {
                        System.out.println(score1.getTimes() + "회차 " + "등급: " + score1.getRank());
                    }
                }
                System.out.println("\n등급 조회 성공!");
            } else {
                System.out.println("존재하지 않는 과목입니다.");
            }
        } else {
            System.out.println("입력한 학생번호가 존재하지 않습니다.");
        }
    }

    //시험 회차를 반환하는 메서드
    public int getTestTimes(String studentIdInput, String subjectIdInput){
        List<Score> scores = new ArrayList<>();
        for (Score score : InitData.getScoreStore()) {
            if(score.getStudentId().equals(studentIdInput) && score.getSubjectId().equals(subjectIdInput)){
                scores.add(score);
            }
        }
        return scores.size()+1;
    }



}