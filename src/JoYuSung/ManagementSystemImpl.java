package JoYuSung;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagementSystemImpl implements ManagementSystem {
    private Map<Integer, Member> members = new HashMap<>(); // 생성된 멤버를 저장하기 위한 해시 맵

    @Override
    public boolean addMember(String name, int age, int id) {
        Member member = new Member(name, age, id); // 멤버 인스턴스 생성
        if (members.containsKey(member.getId())) {
            return false; // 해당 id가 이미 존재할 경우 저장 실패
        }
        
        
        members.put(member.getId(), member);
        return true; // 저장 성공


    }

    @Override
    public boolean removeMember(int id) {
        if (members.containsKey(id)) { // 해당 id가 존재하면 삭제
            members.remove(id);
            return true;
        }

        return false; // 삭제 실패
    }

    @Override
    public boolean updateMember(int id, String name) {
        if (members.containsKey(id)) { // 해당 id가 존재하면 수정
            Member member = members.get(id);
            member.setName(name);
            return true;
        }

        return false; // 수정 실패
    }

    @Override
    public boolean updateMember(int id, int age) {
        if (members.containsKey(id)) {  // 해당 id가 존재하면 수정
            Member member = members.get(id);
            member.setAge(age);
            return true;

        }
        return false; // 수정 실패
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        if (members.containsKey(id)) { // 해당 id가 존재하면 수정
            Member member = members.get(id);
            member.setName(name);
            member.setAge(age);
            return true;

        }
        return false; // 수정 실패
    }

    @Override
    public Member findById(int id) {
            return members.get(id); // 해당 id를 찾아 객체 반환


    }

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {

        // 주어진 조건을 만족하는 멤버들을 반환
        return members.values().stream().filter(filter)
                .collect( Collectors.toList());

    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {

        return members.values().stream()
                .filter(filter)
                .findFirst(); // 첫 번째 일치하는 멤버를 Optional로 반환
    }

    @Override
    public void printMembers() {
        // 멤버들이 저장된 컬렉션을 출력하는 메서드
        members.values().forEach(member -> System.out.println(member));
    }

    @Override
    public int getSize() {
        // 컬렉션에 저장된 멤버의 수를 반환하는 메서드
        return members.size();

    }
}
