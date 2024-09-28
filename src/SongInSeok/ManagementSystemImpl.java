package SongInSeok;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ManagementSystemImpl implements ManagementSystem {

    HashMap<Integer, Member> memberGather = new HashMap<>();            //Map 객체 생성

    @Override
    public boolean addMember(String name, int age, int id) {
        if (memberGather.containsKey(id)) {return false;}

        memberGather.put(id, new Member(name, age, id));                //id값에 맞는 Member 객체 생성
        return true;
    }


    @Override
    public boolean removeMember(int id) {
        if(memberGather.get(id)==null) {return false;}

        memberGather.remove(id);                                        //키값(id)에 맞는 value 제거
        return true;
    }

    @Override
    public boolean updateMember(int id, String name) {
        if(memberGather.get(id)==null) {return false;}

        Member modifiedMember = memberGather.get(id);                   //키값(id)에 맞는 객체에 접근 후 객체 변수 값 변경
        modifiedMember.setName(name);
        return true;
    }

    @Override
    public boolean updateMember(int id, int age) {
        if(memberGather.get(id)==null) {return false;}

        Member modifiedMember = memberGather.get(id);                   //키값(id)에 맞는 객체에 접근 후 객체 변수 값 변경
        modifiedMember.setAge(age);
        return true;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        if(memberGather.get(id)==null) {return false;}

        Member modifiedMember = memberGather.get(id);                  //키값(id)에 맞는 객체에 접근 후 객체 변수 값 변경
        modifiedMember.setName(name);
        modifiedMember.setAge(age);
        return true;
    }


    @Override
    public Member findById(int id) {
        Member serchMember = memberGather.get(id);                     //키값(id)에 맞는 객체에 접근 후 리턴
        return serchMember;
    }

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return memberGather.values().stream()                          //values()로 컬랙션화 하고 , stream을 사용해서 filter로 람다 함수에 true인 값을 toList로 변환 후 리턴
                .filter(filter)
                .toList();

    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter){

        return memberGather.values().stream()
                .filter(filter)
                .findFirst();                                           //filter 조건에 일치하는 element 1개를 Optional로 리턴

    }

    @Override
    public void printMembers() {
        memberGather.values().stream()
                .forEach(System.out::println);                          //모든 객체 정보 출력


    }

    @Override
    public int getSize() {
        return memberGather.size();
    }
}
