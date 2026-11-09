package com.webservice.sample.entity;

import com.webservice.sample.dto.BookRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
// STEP 2 -1
/*
    Q1-1. @Entity : JPA가 클래스를 담당할 수 있게 객체와 DB 테이블을 연결하는 역할
    Q1-2. @Id : 해당 변수를 DB의 primary key 로 지정
    Q1-3. @GeneratedValue : primary key를 자동으로 생성할 수 있게, Type을 IDENTITY로 설정해 DB가 id를 자동 생성 및 증가하게 함
    Q2 - 테이블 한개가 엔티티 클래스 하나와 연결되고, 테이블 한 행이 객체 인스턴스 1개에 대응한다. 엔티티 클래스의 어노테이션으로 구분해 대응 선언을 확인하여 SQL문을 생성한 후, 객체와 테이블 행을 변환해준다.
*/

@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 50)
    private String category;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Book() { } // JPA uses the no-args constructor.

    public Book(BookRequest request) {
        this.title = request.title();
        this.author = request.author();
        this.price = request.price();
        this.category = request.category();
    }

    public void update(BookRequest request) {
        this.title = request.title();
        this.author = request.author();
        this.price = request.price();
        this.category = request.category();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getPrice() { return price; }
    public String getCategory() { return category; }
    public LocalDateTime getCreadtedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt(){ return updatedAt;}
}
