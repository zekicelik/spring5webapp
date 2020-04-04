package guru.springframework.spring5webapp;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Data initialization started.");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Desing", "121546");
        Book tp = new Book("Tutorials Book", "1515");

        Publisher p = new Publisher("İlk Kitap Evi", "Soğanlık/Yein", "Kartal", "Istanbul", 34000);

        publisherRepository.save(p);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        eric.getBooks().add(tp);
        tp.getAuthors().add(eric);

        ddd.setPublisher(p);
        p.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        tp.setPublisher(p);
        p.getBooks().add(tp);

        authorRepository.save(eric);
        bookRepository.save(tp);

        System.out.println("Data initialization finished.");
        System.out.println("Total number of book : " + bookRepository.count());
        System.out.println("Total number of publisher : " + publisherRepository.count());
        System.out.println("Total number of publisher books : " + p.getBooks().size());

    }
}
