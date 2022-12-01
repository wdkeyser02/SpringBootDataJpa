package willydekeyser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import willydekeyser.model.Address;
import willydekeyser.model.Author;
import willydekeyser.model.Book;
import willydekeyser.model.Member;
import willydekeyser.repository.AddressRepository;
import willydekeyser.repository.AuthorRepository;
import willydekeyser.repository.BookRepository;
import willydekeyser.repository.MemberRepository;

@SpringBootApplication
public class SpringBootDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AuthorRepository authorRepository, MemberRepository memberRepository, AddressRepository addressRepository, BookRepository bookRepository) {
		return args -> {
			System.err.println("\n\nStart Run CommandLineRunner!\n\n");
			
			Address address = Address.builder()
					.street("Kerkstraat")
					.number("1")
					.zipCode("9000")
					.city("Gent")
					.phoneNumber("123456789")
					.build();
			
			Member member = Member.builder()
					.firstName("Willy")
					.lastName("De Keyser")
					.email("test@gmail.com")
					.birthday(LocalDate.of(1988, 7, 1))
					.address(address)
					.build();
			
			Book book1 = Book.builder()
					.bookName("Spring Boot in Action")
					.createdAt(LocalDateTime.now())
					.build();
			Book book2 = Book.builder()
					.bookName("Mastering Spring Boot")
					.createdAt(LocalDateTime.now())
					.build();
			Book book3 = Book.builder()
					.bookName("Learning Spring Boot")
					.createdAt(LocalDateTime.now())
					.build();
			Book book4 = Book.builder()
					.bookName("Spring Boot")
					.createdAt(LocalDateTime.now())
					.build();
			Book book5 = Book.builder()
					.bookName("Mastering Spring")
					.createdAt(LocalDateTime.now())
					.build();
			Book book6 = Book.builder()
					.bookName("Learning Spring")
					.createdAt(LocalDateTime.now())
					.build();
			
			
			Author author1 = Author.builder()
					.authorName("Willy De Keyser")
					.build();
			Author author2 = Author.builder()
					.authorName("Ken De Keyser")
					.build();
			Author author3 = Author.builder()
					.authorName("Bill Gates")
					.build();
			Author author4 = Author.builder()
					.authorName("Kesha Williams")
					.build();
			
			book1.addAuthor(author1);
			book1.addAuthor(author2);
			book2.addAuthor(author2);
			book2.addAuthor(author3);
			book2.addAuthor(author4);
			book3.addAuthor(author1);
			book4.addAuthor(author1);
			book5.addAuthor(author1);
			book6.addAuthor(author1);
			book6.addAuthor(author4);
			
			member.addBook(book1);
			member.addBook(book2);
			member.addBook(book3);
			member.addBook(book4);
			member.addBook(book5);
			member.addBook(book6);
			
			memberRepository.save(member);
			//Author author10 = Author.builder()
			//		//.id(1L)
			//		.authorName("Willy De Keyser")
			//		.build();
			Author author10 = authorRepository.findById(1L).get();
			Book book10 = Book.builder()
					.bookName("Action")
					.createdAt(LocalDateTime.now())
					.build();
			book10.addAuthor(author10);
			System.out.println(author10);
			System.out.println(book10 + " " + book10.getAuthors());
			member.addBook(book10);
			
			try {
				memberRepository.save(member);
			} catch (Exception e) {
				System.err.println("\n\nFout " + e.getMessage());
			}
			
			
			
			
			
			
			
			
			
			memberRepository.findById(1L).ifPresent(return_member -> {
				List<Book> books = bookRepository.findAllByMemberId(return_member.getId());
				System.err.println(return_member);
				books.forEach(
						book -> {
							System.out.println("\t" + book);
							book.getAuthors().forEach(
								author -> {
									System.err.println("\t\t" + book + "\t\t" + author);}
								);});
				});
			System.err.println("\n\nEND Run CommandLineRunner!\n\n");
		};
	}
}
