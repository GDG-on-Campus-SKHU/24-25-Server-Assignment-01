package daekyeong;

import java.util.List;
import java.util.Optional;

public class Main {

    // 여러분이 구현한 클래스로 교체해서 테스트하면 됩니다
    static ManagementSystem ms = new ManagementSystemImpl();

    public static void main(String[] args) {
        System.out.println("9번까지 모두 출력되면 성공");
        // 멤버 추가 테스트
        ms.addMember("Kim", 34, 1);
        ms.addMember("David", 15, 2);
        ms.addMember("Alex", 25, 3);
        ms.addMember("Watson", 45, 4);
        ms.addMember("Homes", 45, 5);
        ms.addMember("Michael", 30, 6);
        ms.addMember("Anna", 24, 7);
        ms.addMember("Main", 43, 8);
        ms.addMember("Michael", 30, 9);
        if (ms.getSize() == 9) {
            System.out.println("1. 멤버 추가 성공");
        }

        // id 중복 테스트
        boolean addJun = ms.addMember("Jun", 24, 10);
        boolean addSam = ms.addMember("Sam", 35, 5);
        if (addJun && !addSam) {
            System.out.println("2. id 중복 방지 성공");
        }

        // 멤버 삭제 테스트
        boolean deleteJun = ms.removeMember(10);
        boolean deleteWho = ms.removeMember(99);
        if (deleteJun && !deleteWho) {
            System.out.println("3. 멤버 삭제 성공");
        }

        // 멤버 검색 테스트(id를 통해 검색)
        Member kim = ms.findById(1);
        if (kim.getName().equals("Kim")) {
            System.out.println("4. 멤버 검색(id) 성공");
        }

        // 멤버 조건 검색 테스트 1
        List<Member> members = ms.findMembers((member) -> member.getAge() > 30);
        if (members.size() == 4) {
            System.out.println("5. 멤버 조건 검색 성공 1");
        }

        // 멤버 조건 검색 테스트 2
        Optional<Member> david = ms.findMember((member) -> member.getName().equals("David"));
        Optional<Member> hello = ms.findMember((member) -> member.getName().equals("Hello"));
        if (david.isPresent() && hello.isEmpty()) {
            System.out.println("6. 멤버 조건 검색 성공 2");
        }

        // 멤버 수정 테스트
        ms.updateMember(1, "Van");
        if (ms.findById(1).getName().equals("Van")) {
            System.out.println("7. 멤버 이름 변경 성공");
        }
        ms.updateMember(2, 99);
        if (ms.findById(2).getAge() == 99) {
            System.out.println("8. 멤버 나이 변경 성공");
        }
        ms.updateMember(3, "Park", 50);
        if (ms.findById(3).getAge() == 50 && ms.findById(3).getName().equals("Park")) {
            System.out.println("9. 멤버 이름 + 나이 변경 성공");
        }

        System.out.print("멤버 이름 : ");
        ms.printMembers();
    }
}