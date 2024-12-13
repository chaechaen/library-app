package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.dto.book.requestDTO.BookCreateRequestDTO;
import com.group.libraryapp.dto.book.requestDTO.BookLoanRequestDTO;
import com.group.libraryapp.dto.book.requestDTO.BookReturnRequestDTO;
import com.group.libraryapp.repository.book.BookRepository;
import com.group.libraryapp.repository.user.UserLoanHistoryRepository;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequestDTO requestDTO) {
        bookRepository.save(new Book(requestDTO.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequestDTO requestDTO) {

        // 1. 책 정보 가져옴 (이름 기준)
        Book book = bookRepository.findByName(requestDTO.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출 기록 정보 확인해 대출중인지 확인
        // 3. 만약 확인했는데 대출 중이라면 예외 발생
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("Book already loaned");
        }


        // 4. 유저 정보 가져옴
        User user = userRepository.findByName(requestDTO.getUserName())
                .orElseThrow(IllegalArgumentException::new); // 없으면 예외 발생
        user.loanBook(book.getName());

    }

    @Transactional
    public void returnBook(BookReturnRequestDTO requestDTO) {
        User user = userRepository.findByName(requestDTO.getUserName())
                .orElseThrow(IllegalArgumentException::new); // 유저 찾기

        user.returnBook(requestDTO.getBookName());
    }
}
