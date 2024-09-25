package haeun;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagementSystemImpl implements ManagementSystem {

    private final List<Member> members = new ArrayList<>();

    @Override
    public boolean addMember(String name, int age, int id) {
        // Id 중복 여부 검사. 중복시 return false
        for(Member member : members) {
            if(member.getId() == id) {
                return false;
            }
        }
        Member newMember = new Member(name, age, id);
        members.add(newMember);
        return true;
    }

    @Override
    public boolean removeMember(int id) {
        for(Member member : members) {
            if(member.getId() == id) {
                members.remove(member);
                return true;
            }
        }
        // 삭제 실패 - 존재하지 않는 멤버를 삭제하려는 경우
        return false;
    }

    // 이름 수정
    @Override
    public boolean updateMember(int id, String name) {
        for(Member member : members) {
            if(member.getId() == id) {
                member.setName(name);
                return true;
            }
        }
        // 수정 실패 - 존재하지 않는 멤버를 수정하려는 경우
        return false;
    }

    // 나이 수정
    @Override
    public boolean updateMember(int id, int age) {
        for(Member member : members) {
            if(member.getId() == id) {
                member.setAge(age);
                return true;
            }
        }
        // 수정 실패 - 존재하지 않는 멤버를 수정하려는 경우
        return false;
    }

    // 이름, 나이 둘 다 수정
    @Override
    public boolean updateMember(int id, String name, int age) {
        for(Member member : members) {
            if(member.getId() == id) {
                member.setName(name);
                member.setAge(age);
                return true;
            }
        }
        // 수정 실패 - 존재하지 않는 멤버를 수정하려는 경우
        return false;
    }

    @Override
    public Member findById(int id) {
        for(Member member : members) {
            if(member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // 조건에 일치하는 멤버들을 모두 찾아 List 형태로 반환한다.
    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return members.stream().filter(filter).collect(Collectors.toList());
    }

    // 조건에 일치하는 멤버 하나 반환한다.
    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return members.stream().filter(filter).findFirst();
    }

    @Override
    public void printMembers() {
        for(Member member : members) {
            System.out.println(member);
        }

    }

    @Override
    public int getSize() {
        return members.size();
    }
}
