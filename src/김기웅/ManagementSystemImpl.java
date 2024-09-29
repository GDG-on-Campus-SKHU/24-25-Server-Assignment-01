package 김기웅;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagementSystemImpl implements ManagementSystem {

    private Map<Integer, Member> memberlist = new HashMap<>();

    @Override
    public boolean addMember(String name, int age, int id) {

        if(memberlist.containsKey(id)) {return false;}

        Member newMember = new Member(name, age, id);
        memberlist.put(id, newMember);
        return true;
    }

    @Override
    public boolean removeMember(int id) {

        if (memberlist.containsKey(id)){
            memberlist.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMember(int id, String name) {

        if (memberlist.containsKey(id)){
            Member members = memberlist.get(id);
            members.setName(name);
        }
        return false;
    }

    @Override
    public boolean updateMember(int id, int age) {
        if (memberlist.containsKey(id)){
            Member members = memberlist.get(id);
            members.setAge(age);
        }
        return false;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        if (memberlist.containsKey(id)){
            Member members = memberlist.get(id);
            members.setName(name);
            members.setAge(age);
        }
        return false;
    }

    @Override
    public Member findById(int id) {
        if (memberlist.containsKey(id)){
            return memberlist.get(id);
        }
        return null;
    }
    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return memberlist.values().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<김기웅.Member> findMember(Predicate<Member> filter) {
        return memberlist.values().stream()
                .filter(filter)
                .findFirst();
    }

    @Override
    public void printMembers() {

    }

    @Override
    public int getSize() {
        return memberlist.size();
    }
}
