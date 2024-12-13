package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 20)
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User() {}

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public User(String name, Integer age) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다."));
        }
        this.name = name;
        this.age = age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookname) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookname));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream() // 함수형 프로그래밍을 할 수 있게 stream 시작
                .filter(history -> history.getBookName().equals(bookName)) // 조건을 충족하는 것만 필터링
                .findFirst() // 첫 번째로 해당하는 UserLoanHistory를 찾음
                .orElseThrow(IllegalArgumentException::new); // Optional을 제거하기 위해 없으면 예외 던짐
        targetHistory.doReturn(); // 그렇게 찾은 UserLoanHistory를 반납 처리
    }
}
