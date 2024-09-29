package 채경완;

import java.util.*;
import java.util.function.Predicate;

public class ManagementSystemImpl implements ManagementSystem {

    private static HashMap<Integer , Member> store = new HashMap<>();
    //private static Member member = new Member(); // member로 객체를 생성해서 넣어줬었는데 필요없다는걸 깨달음


    @Override
    public boolean addMember(String name, int age, int id) {
        if(!store.containsKey(id)) {
            store.put(id, new Member(name,age,id));
            return true;
        }
            return false;

    }

    @Override
    public boolean removeMember(int id) {
        if(store.containsKey(id)) {
            store.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMember(int id, String name) {
        Member member = store.get(id);
        if(member!=null){
            member.setName(name);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMember(int id, int age) {
        Member member = store.get(id);
        if(store.get(id)!=null){
            store.get(id).setAge(age);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        Member member = store.get(id);
        if(store.get(id)!=null){
            store.get(id).setAge(age);
            store.get(id).setName(name);
            return true;
        }
        return false;
    }

    @Override
    public Member findById(int id) {
        return store.get(id);
    }

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return store.values().stream()
                .filter(filter).toList(); //콜렉터가 변경가능 , 투 리스트는 불변  (백준에서는 사용불가)

    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return store.values().stream().
                filter(filter).findFirst();  //findFirst()는 스트림의 첫번째 요소를 반환하고, 요소가 없으면 빈 Optianal 반환한다.
    }

    @Override
    public void printMembers() {
        store.values().forEach(System.out::println);

    }

    @Override
    public int getSize() {
        return store.size();
    }
}
