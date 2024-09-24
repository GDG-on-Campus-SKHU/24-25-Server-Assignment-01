package sanghwa;

import jun.Member;

import java.util.HashSet;

public class addMember {
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
}
