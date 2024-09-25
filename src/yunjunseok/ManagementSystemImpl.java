package yunjunseok;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class ManagementSystemImpl implements ManagementSystem {

    // HashMap을 이용하여 ID를 key로 멤버 객체를 value로 사용하였음.
    // 멤버 정보를 저장하고 조회하는데에 사용함.
    // 멤버마다 고유한 id를 가지는 특성을 Main.java에서 발견해서 사용하였음.
    private Map<Integer, Member> memList = new HashMap<>();

    @Override
    public boolean addMember(String name, int age, int id) {

        // ID 중복을 확인하고, 중복일 경우 false 반환
        if(memList.containsKey(id)) { return false; }

        // Map에 새로운 멤버 객체를 추가 후 true 반환
        memList.put(id, new Member(name, age, id));
        return true;
    }

    @Override
    public boolean removeMember(int id) {
        // memList에 id가 없으면 false 반환
        if (!memList.containsKey(id)) { return false; }

        // id가 존재하면 해당 id를 제거 후 true 반환
        memList.remove(id);
        return true;
    }

    @Override
    public boolean updateMember(int id, String name) {
        // id가 미존재시 false
        if (!memList.containsKey(id)) { return false; }

        // id에 해당되는 멤버 이름을 가져오고 변경 후 true 반환
        Member member = memList.get(id);
        member.setName(name);
        return true;
    }

    @Override
    public boolean updateMember(int id, int age) {
        // id가 미존재시 false
        if (!memList.containsKey(id)) { return false; }

        // id에 해당되는 멤버 나이를 가져오고 변경 후 true 반환
        Member member = memList.get(id);
        member.setAge(age);
        return true;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        // id 미존재시 false
        if (!memList.containsKey(id)) { return false; }
        // id에 해당되는 멤버 이름과 나이를 가져와 변경 후 true 반환
        Member member = memList.get(id);
        member.setName(name);
        member.setAge(age);
        return true;
    }

    // 해당 ID의 멤버를 반환, 없으면 null 반환
    @Override
    public Member findById(int id) { return memList.get(id); }

    // 구현 조건: findMemberes()와 findMember()는 반드시 Stream을 사용해 구현해야 합니다.
    // stream을 사용하여 데이터 연속처리를 수행할 수 있고 조건에 맞는 멤버를 찾는 필터를 사용할 수 있음
    // 마지막에 .toList()로 불변 리스트로 변환해줘야 함. 아니면 스트림으로 반환됨.
    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return memList.values().stream()
        .filter(filter)
        .toList();
    }
    // optional로 반환해주는 findFirst() 메서드를 찾아서 해당 메서드를 사용해주었음.
    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return memList.values().stream().filter(filter).findFirst();
    }

    // Main.java에선 안쓰는데 리턴없이 냅둬도 호출하면 멤버 리스트 반환되긴 함. forEach로 sout 반환값 주는 방법이 있어서 추가.
    @Override
    public void printMembers() {
        memList.values().forEach(System.out::println);
    }

    // HashMap의 길이를 반환하는 메서드
    @Override
    public int getSize() { return memList.size(); }
}

