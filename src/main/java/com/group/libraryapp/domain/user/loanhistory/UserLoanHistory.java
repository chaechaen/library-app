package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;

    protected UserLoanHistory() {}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false; // 처음 빌리면 무조건 false
    }

    public void doReturn() {
        this.isReturn = true; // 반납
    }

    public String getBookName() {
        return bookName;
    }
}
