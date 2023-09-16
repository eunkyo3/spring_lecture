package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0, 1, 2 이렇게 키값을 설정해주는 변수

    @Override
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id)); // 널이 반환될 가능성이 있으므로 Ontional.ofNullable() 메서드를 사용함
    }

    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 파라미터로 넘어온 name과 같은지 확인하고
                .findAny(); // 같은 경우에만 필터링이 되고, 그 중에서 찾으면 반환
    }

    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values()); // 멤버들을 전부 반환
    }

    public void clearStore(){
        store.clear();
    }
}