package ktw;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import jun.Member;
import jun.ManagementSystem;

import jun.Member;
;
public class ManagementSystemImpl implements ManagementSystem {


    HashSet<Member> members = new HashSet<Member>();

    @Override
    public boolean addMember(String name, int age, int id) {
        // findById를 날렸을때 값이 없으면 멤버를 추가함
        if(findById(id) == null) {
            members.add(new Member(name, age, id));
            return true;
        }
        // findByID 날렸을때 값이 있으면 추가 불가능
        else {
            return false;
        }
    }

    @Override
    public boolean removeMember(int id) {
        // findById를 날렸는데 값이 없으면 멤버 삭제 불가
        if(findById(id) == null) {
            return false;
        }
        // findById를 날렸는데 값이 있으면 그 멤버 객체를 set에서 삭제
        else {
            members.remove(findById(id));
            return true;
        }

    }

    @Override
    public boolean updateMember(int id, String name) {
        // 1. id로 찾아서 없는 멤버면 수정불가
        if(findById(id) == null) {
            return false;
        }
        // 2. id로 찾아서 있는 멤버면 get()으로 그 객체의 필드변수 수정
        else {
            findById(id).setName(name);
            return true;
        }
    }

    @Override
    public boolean updateMember(int id, int age) {
        // 1. id로 찾아서 없는 멤버면 수정불가
        if(findById(id) == null) {
            return false;
        }
        // 2. id로 찾아서 있는 멤버면 get()으로 그 객체의 필드변수 수정
        else {
            findById(id).setAge(age);
            return true;
        }
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        // 1. id로 찾아서 없는 멤버면 수정불가
        if(findById(id) == null) {
            return false;
        }
        // 2. id로 찾아서 있는 멤버면 get()으로 그 객체의 필드변수 수정
        else {
            findById(id).setName(name);
            findById(id).setAge(age);
            return true;
        }
    }

    @Override
    public Member findById(int id) {
        return members.stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElse(null);
    }
            /*
        if(tempResult.isPresent()){
            return tempResult.get();
        } else {
            return null;
        }
        -> orElse로 축약 가능
         */

   /*
        구조 파악을 위해 하나씩 풀어 써본 코드
        1. 해쉬셋 -> 스트림 변환
        Stream<Member> memberStream = members.stream();
        2. 스트림의 filter메소드로 싹 돌면서 조건에 맞춰 필터링
        Stream<Member> filteredStream = memberStream.filter(member -> member.getId()==id);
        3. 스트림의 findFirst 메소드 이용, 첫 번째 요소가 있으면 그 요소 저장, 없으면 Optional.empty() 저장
        Optional<Member> tempResult = filteredStream.findFirst();


        4. tempResult에 값이 있으면 옵셔널 벗겨서 반환
        if(result.isPresent()) {
            return tempResult.get()
        5. tempResult에 Optional.empty()가 있는 경우 null 반환
        } else {
            return null;
        }
   }
    */

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return members.stream().filter(filter).collect(Collectors.toList());
        // 자바 16부터는 stream 자체에 toList() 메소드가 도입되어 이렇게도 쓸 수 있다고 한다
        // return members.stream().filter(filter).toList();
    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return members.stream().filter(filter).findFirst();
    }

    @Override
    public void printMembers() {
        members.forEach(member -> System.out.println("toString"));
    }

    @Override
    public int getSize() {
        return members.size();
    }
}
