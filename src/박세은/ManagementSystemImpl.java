package 박세은;

import jun.ManagementSystem;
import jun.Member;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagementSystemImpl implements ManagementSystem {

    private final Map<Integer, Member> members = new HashMap<>();

    @Override
    public boolean addMember(String name, int age, int id) {
        return members.putIfAbsent(id, new Member(name, age, id)) == null;
    }

    @Override
    public boolean removeMember(int id) {
        return members.remove(id) != null;
    }

    @Override
    public boolean updateMember(int id, String name) {
        Member member = members.get(id);
        if (member == null) return false;
        member.setName(name);
        return true;
    }


    @Override
    public boolean updateMember(int id, int age) {
        Member member = members.get(id);
        if (member == null) return false;
        member.setAge(age);
        return true;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        Member member = members.get(id);
        if (member == null) return false;
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
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return members.values().stream()
                .filter(filter)
                .findFirst();
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
