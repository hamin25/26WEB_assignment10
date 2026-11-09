package com.webservice.sample.service;

import com.webservice.sample.dto.BookRequest;
import com.webservice.sample.dto.BookResponse;
import com.webservice.sample.entity.Book;
import com.webservice.sample.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookResponse create(BookRequest request) {
        Book saved = repository.save(new Book(request)); // INSERT
        return BookResponse.from(saved);
    }

    public List<BookResponse> findAll() {
        return repository.findAll().stream().map(BookResponse::from).toList();
    }

    public BookResponse findById(Long id) {
        return BookResponse.from(findEntity(id));
    }

    public BookResponse update(Long id, BookRequest request) {
        Book book = findEntity(id);
        book.update(request);
        // Week 09: explicitly save (Week 10 changes this to Dirty Checking).
        return BookResponse.from(repository.save(book));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found: " + id);
        }
        repository.deleteById(id);
    }

    private Book findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Book not found: " + id));
    }

    public List<BookResponse> findByCategory(String category){
        return repository.findByCategory(category);
    };

    public List<BookResponse> findByPriceBetween(Integer min, Integer max){
        return repository.findByPriceBetween(min,max);
    }
}

/*
Q7. save()를 호출하면 새로운 데이터 등록과 기존 데이터 수정이 어떻게 처리되는가?
: 이번과제에서는 수정과 등록을 다른 메서드로 접근하는 게 아니라 같은 save()메서드로 접근했다. 그래서 id가 null인지에 따라 새 엔티티인지 구분하고 그 결과에 따라 있으면 UPDATE를 없다면 INSERT를 실행해 처리한다.
Q8. @Transactional과 Dirty Checking은 어떤 관계가 있는가?
: Transaction은 여러 DB작업을 하나의 단위로 묶어 성공 실패 처리에 따라 DB에 commit/rollback 처리를 하는 것이고, dirtychecking은 영속성 컨테스트가 관리하는 entity 처음과 flush 시점의 상태 변경을 감지하여 update sql을 자동으로 실행하는 것이다.
따라서 여러 DB 작업을 하나의 트랜잭션으로 묶어 처리해 entity 필드를 변경하면 save()를 실행하지 않아도 자동으로 dirtyChecking이 발생해 UPDATE문을 실행된다.
 */