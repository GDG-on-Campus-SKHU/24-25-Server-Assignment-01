package sanghwa;

import jun.Member;

import java.util.HashSet;
import java.util.Iterator;

public class addMember { //메서드명 전체를 포괄하는 것으로 수정해야함
    public static HashSet<Member> set = new HashSet<>();

    public static boolean addMember(String name, int age, int id) {
        Member member = new Member(name, age, id); // member 객체 생성과 함께 addMember파라미터들을 member에 저장
        boolean isIn = set.add(member);
        if (isIn){
            System.out.println(member);
        }
        return isIn;
    }
    public static int getSize(){
        return set.size();
    }

    public static boolean removeMember(int id){
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

    public static boolean updateMember(){
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
}
