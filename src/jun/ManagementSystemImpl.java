package jun;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class ManagementSystemImpl implements ManagementSystem {

    @Override
    public boolean addMember(String name, int age, int id) {
        return false;
    }

    @Override
    public boolean removeMember(int id) {
        return false;
    }

    @Override
    public boolean updateMember(int id, String name) {
        return false;
    }

    @Override
    public boolean updateMember(int id, int age) {
        return false;
    }

    @Override
    public boolean updateMember(int id, String name, int age) {
        return false;
    }

    @Override
    public Member findById(int id) {
        return null;
    }

    @Override
    public List<Member> findMembers(Predicate<Member> filter) {
        return List.of();
    }

    @Override
    public Optional<Member> findMember(Predicate<Member> filter) {
        return Optional.empty();
    }

    @Override
    public void printMembers() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
