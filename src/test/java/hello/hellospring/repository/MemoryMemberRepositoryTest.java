package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore(); // 테스트가 실행 되고 난 후 끝날 때마다 저장소를 다 지움(의존성이 중요함)
    }

    @Test // 테스트 확인 하는 어노테이션
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 반환 타입이 Optional이라서 값을 꺼낼 때는 get() 메서드를 통해서 꺼냄.
        // get() 메서드를 통해서 꺼내는 것은 원래 좋은 방법은 아니다.

        // System.out.println("result = " + (result == member)); // 계속해서 콘솔창을 보는것은 좋지 않음.

        // Assertions.assertEquals(member, null);
        // 기대했던 값은 hello.hellospring.domain.Member@2890c451 인데
        // 널이 들어와서 오류.

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo((member1));
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}