package com.assignment.bookStore.service.serviceImpl;

import com.assignment.bookStore.dto.AuthorDTO;
import com.assignment.bookStore.dto.BookDTO;
import com.assignment.bookStore.dto.ReviewDTO;
import com.assignment.bookStore.entity.Book;
import com.assignment.bookStore.entity.BookReview;
import com.assignment.bookStore.exceptions.DataAlreadyPresentException;
import com.assignment.bookStore.exceptions.NotFoundException;
import com.assignment.bookStore.repository.BookRepository;
import com.assignment.bookStore.repository.ReviewRepository;
import com.assignment.bookStore.service.AuthorService;
import com.assignment.bookStore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

@Autowired
private AuthorService authorService;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<BookDTO> getAllBooks() {
     List<Book> bookList= bookRepository.findAll();
        List<BookDTO> bookDTOList = bookList.stream().map(p -> new BookDTO(p.getIsbn(), p.getTitle(), p.getDescription(), p.getGenera(), modelMapper.map(p.getAuthor(), BookDTO.class).getAuthor(), p.getPrice(),
                p.getBookReviews().stream().map(r-> new ReviewDTO(r.getReview_id(),r.getBookReview(),r.getStarRating())).toList())).collect(Collectors.toList());
        return bookDTOList;
    }

    @Override
    public BookDTO getBookById(long isbn) {
        Book book=bookRepository.findById(isbn).orElseThrow(()-> new UsernameNotFoundException("INVALID_BOOKID"));
        BookDTO bookDto= modelMapper.map(book, BookDTO.class);
        return bookDto;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDto) throws NotFoundException {
        if(bookDto.getAuthor().getAuthorId()==null){
            throw new NotFoundException("Author Id not  present");
        }
        Optional<Book> bookOptional=bookRepository.findByTitleAndAuthorAuthorId(bookDto.getTitle(),bookDto.getAuthor().getAuthorId());
        if(bookOptional.isPresent()){
            throw new DataAlreadyPresentException("Book already present");
        }
        Book book = modelMapper.map(bookDto,Book.class);
        book.setAvailable(true);
        Book savedBook= bookRepository.save(book);
        BookDTO savedBookDTO = modelMapper.map(savedBook, BookDTO.class);
        AuthorDTO authorDto=authorService.getAuthorById(bookDto.getAuthor().getAuthorId());
        savedBookDTO.setAuthor(authorDto);
        return savedBookDTO;
    }

    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO,Long isbn) {
        BookReview review = modelMapper.map(reviewDTO,BookReview.class);
        Book book= new Book();
        book.setIsbn(isbn);
        review.setBook(book);
        BookReview savedReview= reviewRepository.save(review);
        return modelMapper.map(savedReview,ReviewDTO.class);
    }

    @Override
    public void deleteBookById(long isbn) throws NotFoundException {

        try{bookRepository.deleteById(isbn);}
        catch (Exception e){
            throw  new NotFoundException("Book with given id is not found");
        }
    }

    @Override
    public BookDTO updateBook(long isbn, BookDTO bookDto) throws NotFoundException {
        if(bookDto.getAuthor().getAuthorId()==null){
            throw new NotFoundException("Author Id not  present");
        }
        AuthorDTO authorDto=authorService.getAuthorById(bookDto.getAuthor().getAuthorId());
        getBookById(isbn);
        Book book = modelMapper.map(bookDto,Book.class);
        book.setAvailable(true);
        Book savedBook= bookRepository.save(book);
        BookDTO savedBookDTO = modelMapper.map(savedBook, BookDTO.class);
        savedBookDTO.setAuthor(authorDto);
        return savedBookDTO;
    }
}
