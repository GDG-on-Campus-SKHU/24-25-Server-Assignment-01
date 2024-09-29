package 김보민;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagementSystemImpl implements ManagementSystem {
    //interface 동작 구현 클래스

    // map같은 자료구조는 데이터를 관리하고, newmember는 새로운 멤버 객체를 생성하고 데이터를 저장하는 역할
    private HashMap<Integer, Member> map = new HashMap<>();

    @Override
    public boolean addMember(String name, int age, int id) {
        if (map.containsKey(id))//확인하고
            return false;//존재하면 false. 추가 실패

        Member newmember = new Member(name, age, id); //main에서 들어오는 데이터들을 관리하는 객체 만들어 주고
        map.put(id, newmember); //map에 id=1로 newmember를 추가한다.
        return true;//추가가 되면 true 반환
    }

    @Override
    public boolean removeMember(int id) {
        if (!map.containsKey(id))//존재하지 않다 -> !map.containsKey
            return false; //존재 하지 않는 id 라면 false
        map.remove(id); //멤버 삭제한다.
        return true;
    }

    @Override
    public boolean updateMember(int id, String name) {
        Member member1 = map.get(id); //id를 이용해서 멤버를 찾아
        if (!map.containsKey(id))//존재x ->false
            return false;

        member1.setName(name); //존재하면 이름 바꿔준다.
        return true;
    }

    @Override
    public boolean updateMember(int id, int age) {
        Member member2 = map.get(id); //멤버 찾기
        if (!map.containsKey(id))
            return false;
        member2.setAge(age);
        return true;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        Member member3 = map.get(id);
        if (!map.containsKey(id))
            return false;
        member3.setAge(age);
        member3.setName(name);
        return true;
    }

    @Override
    public Member findById(int id) {
        return map.get(id);
    }

    @Override //Predicate: 한 타입을 받아서 리턴
    public List<Member> findMembers(Predicate<Member> Lnumber) {
        //List<Member> 타입의 객체들을 여러 개 담을 수 있는 리스트 형태로 반환한다.
        //Predicate<Member>에 Member는 파라미터이자 조건. 이게 true또는 false를 return하도록 작성하기

       return map.values().stream().filter(Lnumber)//value값들 가져와서 stream -> 필터로 입력받은 값(Lnumber)
                .collect(Collectors.toList()); //종결 연산.
        //return List.of(); //생성된 리스트 변경 못하게 하는거
    }

    @Override
    public Optional<Member> findMember(Predicate<Member> Onumber) { //Onumber에 들어오면
        return map.values().stream().filter(Onumber).findFirst(); //findFirst()하나의 멤버만 선택해서 반환
        /* key로 찾으면
        map.keySet().stream().filter(Onumber).findFirst()

        return Optional.empty(); //값이 없는 Optional! */
    }

    @Override
    public void printMembers() {
        for (Member member : map.values()) //for-each문 사용.
            System.out.println(member);
    }

    //컬렉션에 저장된 멤버의 수를 반환하는 메서드
    @Override
    public int getSize() {
        int count=0;
         for(Member member : map.values()) // map.values()로 전체 값 가져와서 Member 객체를 member에 저장
             count++; //더하고
         return count;
    }
}