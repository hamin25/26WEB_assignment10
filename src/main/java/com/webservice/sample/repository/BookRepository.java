package com.webservice.sample.repository;

import com.webservice.sample.dto.BookResponse;
import com.webservice.sample.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Week 09: All basic CRUD methods are inherited from JpaRepository.

    List<BookResponse> findByCategory(String category);

}
/*
STEP2 - 2
Q3. JpaRepository<Entity, Long>에서 두 타입은 각각 무엇을 의미하는가?
: 엔티티 클래스와 primary key 값의 type 을 의미한다.
Q4. JpaRepository에서 기본 제공하는 CRUD 메서드를 5개 이상 찾아 메서드명과 역할을 주석으로 작성하세요.
: save() - INSERT or UPDATE, findById(1L) - SELECT, findAll() - SELECT 전체, deleteById(1L) - DELETE, deleteAll() - 전체 조회 후 하나씩 삭제
Q5. findById()의 반환 타입이 Optional인 이유는 무엇인가?
: Id 값에 따라 객체를 조회 하는 것이라 값이 존재하지 않을 수도 있어, 값이 없는 경우를 처리하기 위해서다.
Q6. JpaRepository에서 기본 제공하는 메서드와 개발자가 직접 작성하는 Query Method의 차이는 무엇인가?
: 기본 메서드는 형식과 리턴값이 정해져 있어 선언을 하지 않아도 사용할 수 있다. Query Method는 직접 규칙에 맞는 메서드 이름을 선언하면 id이외의 필드 값으로도 조회가 가능하다.
 */
