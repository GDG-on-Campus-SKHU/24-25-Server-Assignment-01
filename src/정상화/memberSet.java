package 정상화;

import jun.Member;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class memberSet {
    public static HashSet<Member> set = new HashSet<>();

    public boolean addMember(String name, int age, int id) {
        Member member = new Member(name, age, id);
        boolean isIn = set.add(member);
        if (isIn){
            return true;
        }
        return false;
    }
    public static int getSize(){
        return set.size();
    }

    public  boolean removeMember(int id){
        Iterator<Member> iterator = set.iterator(); //Iterator<Member> ?? set의 iterator메서드,
        while(iterator.hasNext()){
            Member member = iterator.next();
            if(member.getId() == id){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static Member findById(int id){
        Iterator<Member> iterator = set.iterator();
        while(iterator.hasNext()){
            Member member = iterator.next();
            if(member.getId() == id){
                return member;
            }

        }
        System.out.println("해당 id를 가진 member가 없습니다.");
        return null;
    }

    public static List<Member> findMembers(Predicate<Member> condition) {
        return set.stream().filter(condition).collect(Collectors.toList()); // filter(~).collect(Collertors.toList()) -> 조건에 맞는 오브젝트들 리스트로 변환
    }

    public static Optional<Member> findMember(Predicate<Member> condition){

        Optional<Member> opMember = set.stream().filter(condition).findAny();
        if (opMember.isPresent()){
            return opMember; //조건문이 참이면 객체반환
        }
        return Optional.empty(); //거짓일 경우 빈 객체반환
    }

    public boolean updateMember(int id, String name) {
        Optional<Member> opMember = set.stream().filter(m -> m.getId() == id).findFirst(); //조건 직접 작성

        if (opMember.isPresent()) {
            Member member = opMember.get(); //조건에 맞는 객체를 수정하기 위해 새로운 객체 생성
            member.setName(name); // 객체의 주소값을 받았기 때문에 수정 가능한 것
            return true;

        }
        return false;
    }


    public boolean updateMember(int id, int age) {
        Optional<Member> opMember = set.stream().filter(m -> m.getId() == id).findFirst();

        if (opMember.isPresent()){
            Member member = opMember.get();
            member.setAge(age);
            return true;
        }
        return false;
    }


    public boolean updateMember(int id, String name, int age) {
        Optional<Member> opMember = set.stream().filter(m -> m.getId() == id).findFirst();
        if(opMember.isPresent()){
            Member member = opMember.get();
            member.setAge(age);
            member.setName(name);
        }
        return false;
    }
}
