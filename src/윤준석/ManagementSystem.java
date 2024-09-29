package 윤준석;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ManagementSystem {

    /**
     * 저장소에 멤버를 추가하는 메서드,
     * 이미 존재하는 id라면 추가에 실패해야 함,
     * 추가가 성공하면 true를 반환
     */
    boolean addMember(String name, int age, int id);

    /**
     * 저장소에서 멤버를 삭제하는 메서드,
     * 존재하지 않는 id라면 false를 반환
     */
    boolean removeMember(int id);

    /**
     * 멤버를 수정하는 메서드 1,
     * id를 기준으로 멤버를 찾아서, 이름을 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    boolean updateMember(int id, String name);

    /**
     * 멤버를 수정하는 메서드 2,
     * id를 기준으로 멤버를 찾아서 나이를 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    boolean updateMember(int id, int age);

    /**
     * 멤버를 수정하는 메서드 3,
     * id를 기준으로 멤버를 찾아서 이름과 나이를 수정함,
     * 존재하지 않는 id라면 false를 반환
     */
    boolean updateMember(int id, String name, int age);

    /**
     * id를 통해 멤버를 검색하는 메서드
     */
    Member findById(int id);

    /**
     * 조건을 만족하는 멤버들을 반환하는 메서드
     */
    List<Member> findMembers(Predicate<Member> filter);

    /**
     * 조건을 만족하는 멤버 하나를 반환하는 메서드,
     * 조건을 만족하는 멤버가 없을 수도 있으니 Optional을 반환
     */
    Optional<Member> findMember(Predicate<Member> filter);

    /**
     * 모든 멤버를 출력하는 메서드
     */
    void printMembers();

    /**
     * 멤버의 수를 반환하는 메서드
     */
    int getSize();
}
