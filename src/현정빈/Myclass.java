package 현정빈;

import jun.ManagementSystem;
import jun.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Myclass implements ManagementSystem {
    HashMap<Integer,Member> map = new HashMap<>(); //컬렉션 중 Map을 선택하여 HashMap 사용
    // 키는 integer 타입, 값은 Member 타입인 키-값쌍 구조의 map 생성

    /**
     * 저장소에 멤버를 추가하는 메서드,
     * 이미 존재하는 id라면 추가에 실패해야 함,
     * 추가가 성공하면 true를 반환
     */
    @Override
    public boolean addMember(String name, int age, int id) {
        Member newMember = new Member(name, age, id);
        if(!map.containsKey(id)) {//지정된 id를 포함하고 있는지 여부를 반환, 반환값이 false라면
            map.put(id, newMember); //map에 지정된 키(id)와 값(객체 newMember)을 저장
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 저장소에서 멤버를 삭제하는 메서드,
     * 존재하지 않는 id라면 false를 반환
     */
    @Override
    public boolean removeMember(int id) {
        if(!map.containsKey(id)){//지정된 id를 포함하고 있는지 여부를 반환, 반환값이 false라면
            return false;
        }
        else {
            map.remove(id); //지정된 id와 연결된 값을 map에서 제거
            return true;
        }
    }

    /**
     * 멤버를 수정하는 메서드 1,
     * id를 기준으로 멤버를 찾아서, 이름을 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    @Override
    public boolean updateMember(int id, String name) {
        if(map.containsKey(id)) {//지정된 id를 포함하고 있는지 여부를 반환, 반환값이 true라면
            map.get(id).setName(name);//map에서 지정된 id에 연결된 값(참조값=주소)을 반환하면서 Member의 메소드를 사용할 수 있게 된다
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 멤버를 수정하는 메서드 2,
     * id를 기준으로 멤버를 찾아서 나이를 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    @Override
    public boolean updateMember(int id, int age) {
        if(map.containsKey(id)) {//지정된 id를 포함하고 있는지 여부를 반환, 반환값이 true라면
            map.get(id).setAge(age);//map에서 지정된 id에 연결된 값(참조값=주소)을 반환하면서 Member의 메소드를 사용할 수 있게 된다
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 멤버를 수정하는 메서드 3,
     * id를 기준으로 멤버를 찾아서 이름과 나이를 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    @Override
    public boolean updateMember(int id, String name, int age) {
        if(map.containsKey(id)) { //지정된 id를 포함하고 있는지 여부를 반환, 반환값이 true라면
            map.get(id).setName(name); //map에서 지정된 id에 연결된 값(참조값=주소)을 반환하면서 Member의 메소드를 사용할 수 있게 된다
            map.get(id).setAge(age);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * id를 통해 멤버를 검색하는 메서드
     */
    @Override
    public Member findById(int id) {
        if(map.containsKey(id)){ //맵이 지정된 id를 포함하고 있는지 여부 반환, 반환된 값이 true라면
            return map.get(id); //지정된 id에 연결된 값을 반환
        }
        return null;
    }

    /**
     * 조건을 만족하는 멤버들을 반환하는 메서드
     */
    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return map.values().stream() //스트림 생성
                .filter(filter) //필터링 조건 적용
                .collect(Collectors.toList()); //결과를 리스트로 수집하여 반환

    }

    /**
     * 조건을 만족하는 멤버 하나를 반환하는 메서드,
     * 조건을 만족하는 멤버가 없을 수도 있으니 Optional을 반환
     */
    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return map.values().stream() //스트림 생성
                .filter(filter) //필터링 조건 적용
                .findAny(); //멤버 하나 선택하여 Optional로 감싸서 반환, 조건에 맞는 요소가 없다면 Optional,empty() 반환
    }

    /**
     * 모든 멤버를 출력하는 메서드
     */
    @Override
    public void printMembers() {
        for(Member member : map.values()){ //for-each 문으로 임시변수 member에 map.values()를 대입하여 출력
            System.out.println(member);
        }
    }

    /**
     * 멤버의 수를 반환하는 메서드
     */
    @Override
    public int getSize() { 

        return map.size(); //맵에 있는 키-값 쌍의 개수 반환
    }
}
