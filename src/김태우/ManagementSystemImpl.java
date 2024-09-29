package 김태우;

import java.util.*;
import java.util.function.Predicate;

import jun.Member;
import jun.ManagementSystem;

public class ManagementSystemImpl implements ManagementSystem {

    // Set타입을 사용한 것을 직관적으로 알 수 있도록 변수명을 membersSet 으로 지정함
    // 코드의 복잡도가 높지 않아 굳이 Key,Value 형태의 Map을 사용할 필요가 없다고 판단했으며
    // 자료형 하나를 사용해 직관성이 더 높다고 생각되는 Set을 사용했습니다
    HashSet<Member> membersSet = new HashSet<Member>();

    @Override
    public boolean addMember(String name, int age, int id) {
        // findById를 날렸을때 값이 없으면 멤버를 추가함
        if (findById(id) == null) {
            membersSet.add(new Member(name, age, id));

            return true;
        }
        // findByID 날렸을때 값이 있으면 추가 불가능
        return false;
    }

    @Override
    public boolean removeMember(int id) {
        // findById를 날렸는데 값이 없으면 멤버 삭제 불가
        if (findById(id) == null) {
            return false;
        }
        // findById를 날렸는데 값이 있으면 그 멤버 객체를 set에서 삭제
        membersSet.remove(findById(id));

        return true;
    }

    @Override
    public boolean updateMember(int id, String name) {
        // id로 찾아서 없는 멤버면 수정불가
        if (findById(id) == null) {
            return false;
        }
        // id로 찾아서 있는 멤버면 get()으로 그 객체의 필드변수 수정
        findById(id).setName(name);

        return true;
    }

    @Override
    public boolean updateMember(int id, int age) {
        // id로 찾아서 없는 멤버면 수정불가
        if (findById(id) == null) {
            return false;
        }
        // id로 찾아서 있는 멤버면 get()으로 그 객체의 필드변수 수정
        findById(id).setAge(age);

        return true;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        // id로 찾아서 없는 멤버면 수정불가
        if (findById(id) == null) {
            return false;
        }
        // id로 찾아서 있는 멤버면 get()으로 그 객체의 필드변수 수정
        findById(id).setName(name);
        findById(id).setAge(age);

        return true;
    }

    @Override
    public Member findById(int id) {
        return membersSet.stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /*
        구조 파악을 위해 하나씩 풀어 써본 코드
        1. 해쉬셋 -> 스트림 변환
        Stream<Member> memberStream = membersSet.stream();
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
        -> 4,5번 과정을 orElse(null)로 축약 가능
    */

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        // 자바 16부터 도입된 toList() 메소드 사용, 백준에서는 자바 15까지 지원하므로 사용불가
        return membersSet.stream().filter(filter).toList();
        // 아래와 같은 방법으로도 리스트로 변환 가능
        // 스트림의 모든 요소들을 리스트로 수집하는 Collector 객체를 생성한뒤 toList() 로 리스트로 변환
        // return membersSet.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return membersSet.stream().filter(filter).findFirst();
    }

    @Override
    public void printMembers() {
        // 리스트 형태의 자료구조를 싹 돌면서 각 요소에 대해 내가 원하는 작업을 해주는 forEach() 메소드 사용
        membersSet.forEach(member -> System.out.println(member.toString()));
    }

    @Override
    public int getSize() {
        return membersSet.size();
    }
}
