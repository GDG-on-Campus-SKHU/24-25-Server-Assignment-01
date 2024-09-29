package jun;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 단순 참고를 위한 예제입니다
 * 모범 답안이 아닙니다!!!
 */
public class ManagementSystemImpl implements ManagementSystem {

    Map<Integer, Member> members = new HashMap<>();

    @Override
    public boolean addMember(String name, int age, int id) {
        if (members.containsKey(id)) {
            return false;
        }

        members.put(id, new Member(name, age, id));

        return true;
    }

    @Override
    public boolean removeMember(int id) {
        if (!members.containsKey(id)) {
            return false;
        }

        members.remove(id);

        return true;
    }

    @Override
    public boolean updateMember(int id, String name) {
        if (!members.containsKey(id)) {
            return false;
        }

        Member member = members.get(id);
        member.setName(name);

        return true;
    }

    @Override
    public boolean updateMember(int id, int age) {
        if (!members.containsKey(id)) {
            return false;
        }

        Member member = members.get(id);
        member.setAge(age);

        return true;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        if (!members.containsKey(id)) {
            return false;
        }

        Member member = members.get(id);
        member.setName(name);
        member.setAge(age);

        return true;
    }

    @Override
    public Member findById(int id) {
        return members.get(id);
    }

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return members.values().stream()
                .filter(filter)
                .toList();
    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return members.values().stream()
                .filter(filter)
                .findAny();
    }

    @Override
    public void printMembers() {
        members.values().forEach(System.out::println);
    }

    @Override
    public int getSize() {
        return members.size();
    }
}
