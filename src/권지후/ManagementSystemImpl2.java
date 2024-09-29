package 권지후;

import jun.ManagementSystem;
import jun.Member;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagementSystemImpl2 implements ManagementSystem {

    private Map<Integer, Member> memberMap = new HashMap<>();
    /**
     * 저장소에 멤버를 추가하는 메서드,
     * 이미 존재하는 id라면 추가에 실패해야 함,
     * 추가가 성공하면 true를 반환
     */
    @Override
    public boolean addMember(String name, int age, int id) {
        if(!memberMap.containsKey(id)){
        Member newMember = new Member(name, age, id);
            memberMap.put(id,newMember);
            return true;
        }
        return false;
    }
    /**
     * 저장소에서 멤버를 삭제하는 메서드,
     * 존재하지 않는 id라면 false를 반환
     */
    @Override
    public boolean removeMember(int id) {
        if(memberMap.containsKey(id)){
            memberMap.remove(id);
            return true;
        }
        return false;

    }
    /**
     * 멤버를 수정하는 메서드 1,
     * id를 기준으로 멤버를 찾아서, 이름을 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    @Override
    public boolean updateMember(int id, String name) {
        if(memberMap.containsKey(id)){
            Member updateMember = memberMap.get(id);
            updateMember.setName(name);
            return true;
        }
        return false;
    }
    /**
     * 멤버를 수정하는 메서드 2,
     * id를 기준으로 멤버를 찾아서 나이를 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    @Override
    public boolean updateMember(int id, int age) {
        if(memberMap.containsKey(id)){
            Member updateMember = memberMap.get(id);
            updateMember.setAge(age);
            return true;
        }
        return false;
    }

    /**
     * 멤버를 수정하는 메서드 3,
     * id를 기준으로 멤버를 찾아서 이름과 나이를 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    @Override
    public boolean updateMember(int id, String name, int age) {
        if(memberMap.containsKey(id)){
            Member updateMember = memberMap.get(id);
            updateMember.setAge(age);
            updateMember.setName(name);
            return true;
        }
        return false;
    }
    /**
     * id를 통해 멤버를 검색하는 메서드
     */
    @Override
    public Member findById(int id) {
        return memberMap.get(id);
    }
    /**
     * 조건을 만족하는 멤버들을 반환하는 메서드
     */
    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return memberMap.values().stream()
                .filter(filter).collect(Collectors.toList());
    }
    /**
     * 조건을 만족하는 멤버 하나를 반환하는 메서드,
     * 조건을 만족하는 멤버가 없을 수도 있으니 Optional을 반환
     */
    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        List<Member> collect = memberMap.values().stream().filter(filter).collect(Collectors.toList());
    return collect.isEmpty()? Optional.empty():Optional.of(collect.get(0));
    }
    /**
     * 모든 멤버를 출력하는 메서드
     */
    @Override
    public void printMembers() {
        for(Integer id: memberMap.keySet()){
            Member member = memberMap.get(id);
            System.out.println(member);
        }
    }
    /**
     * 멤버의 수를 반환하는 메서드
     */
    @Override
    public int getSize() {
        return memberMap.size();
    }
}
