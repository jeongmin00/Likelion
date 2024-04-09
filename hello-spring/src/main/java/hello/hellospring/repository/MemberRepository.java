package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id); // null을 그대로 반환하는 대신 optional 사용해서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 모든 회원 리스트

}
