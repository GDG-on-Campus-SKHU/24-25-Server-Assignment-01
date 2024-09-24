package daekyeong;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagementSystemImpl implements ManagementSystem {

    HashMap<Integer, Member> member = new HashMap<>();

    @Override
    public boolean addMember(String name, int age, int id) {
        if(member.containsKey(id)) {
            return false;
        }
        else {
            member.put(id, new Member(name, age, id));
            return true;
        }
    }

    @Override
    public boolean removeMember(int id) {
        if(member.containsKey(id)){
            member.remove(id);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean updateMember(int id, String name) {
        member.put(id, new Member(name, member.get(id).getAge(), id));
        return true;
    }

    @Override
    public boolean updateMember(int id, int age) {
        member.put(id, new Member(member.get(id).getName(), age, id));
        return true;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        member.put(id, new Member(name, age, id));
        return true;
    }

    @Override
    public Member findById(int id) {
        return member.get(id);
    }

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return member.values().stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        if(member.values().stream().filter(filter).count() == 0){
            return Optional.empty();
        }
        return member.values().stream().filter(filter).findAny();
    }

    @Override
    public void printMembers() {
        Iterator<Integer> iterator = member.keySet().iterator();
        while(iterator.hasNext()){
            System.out.print(member.get(iterator.next()).getName() + " ");
        }
    }

    @Override
    public int getSize() {
        return member.size();
    }
}
