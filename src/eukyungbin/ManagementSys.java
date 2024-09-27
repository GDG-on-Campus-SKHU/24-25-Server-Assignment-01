package eukyungbin;

import jun.ManagementSystem;
import jun.Member;

import java.util.*;
import java.util.function.Predicate;

public class ManagementSys implements ManagementSystem {

    HashMap<Integer, Member> map = new HashMap<>(); //멤버의 id와 Member객체를 갖는 map

    @Override
    public boolean addMember(String name, int age, int id) {
        if(!map.containsKey(id)){
            Member newMember = new Member(name, age, id); //새로운 Member객체 만들기
            map.put(id, newMember); // 없는 id면 map에 추가하기
            return true;

        }

        return false;
    }

    @Override
    public boolean removeMember(int id) {

        if(map.containsKey(id)){ //id가 map 안에 있다면

            map.remove(id); // 해당 값을 map에서 지우기
            return true;
        }
        return false;
    }
    // 이름 수정하기
    @Override
    public boolean updateMember(int id, String name) {
        if(map.containsKey(id)){
            map.get(id).setName(name); //get()메소드로 해당 값을 불러와서 setName()으로 name을 수정한다.
        }

        return false;
    }
    //나이 수정
    @Override
    public boolean updateMember(int id, int age) {
        if(map.containsKey(id)){
            map.get(id).setAge(age); //get()으로 해당 값을 불러 setAge()메소드로 age를 수정한다.
        }
        return false;
    }
    //이름과 나이 수정
    @Override
    public boolean updateMember(int id, String name, int age) {
        if(map.containsKey(id)){
            map.get(id).setName(name); //get()메소드로 해당 값을 불러와서 setName()으로 name을 수정한다.
            map.get(id).setAge(age); //get()으로 해당 값을 불러 setAge()메소드로 age를 수정한다.
        }
        return false;
    }

    @Override
    public Member findById(int id) {
        if(map.containsKey(id)){
            return map.get(id); // 해당 id에 있는 값을 return한다.

        }
        return null;
    }

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        List<Member> members = map.values().stream()
                /*
                * map에 있는 모든 Member를 Stream으로 바꿔서
                * Member들을 리스트형태로 저장하는 members 변수에 저장한다.
                */

                .filter(filter) // 조건에 맞는 멤버만 필터링한다.
                .toList(); //Member를 리스트로 바꾼다.


        if (members.isEmpty()) { //만약에 조건에 맞는 멤버가 없다면
            return List.of(); // 빈 리스트를 return한다.
        }
        return members;
    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        for (Member member : map.values()){ //map에 있는 모든 Member값들을 돌면서 member에 저장
            if(filter.test(member)){ // 조건에 맞는 멤버가 맞다면
                return Optional.of(member); //그 멤버를 return한다.
            }
        }
        return Optional.empty(); //조건에 맞는 멤버가 없는 경우

    }

    @Override
    public void printMembers() {
        for(Member member : map.values()){
            System.out.println(member);
        }


        }



    @Override
    public int getSize() {
        if(map.isEmpty()){ // 만약 map이 비었다면
            return 0; // 0을 return한다.
        }
        return map.size(); // 맵에 있는 값들의 개수를 return한다.
    }
}
