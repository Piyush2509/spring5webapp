/**
 * 
 */
package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

/**
 * @author piyush.b.kumar
 *
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;	
	private PublisherRepository publishRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publishRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publishRepository= publishRepository;
	}

	private void initData() {
		
		// Eric
		Author eric = new Author("Eric", "Evans");
		Publisher hc = new Publisher("Harper Collins", "New York");
		Book ddd = new Book("Domain Driven Design", "1234", hc);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authorRepository.save(eric);
		publishRepository.save(hc);
		bookRepository.save(ddd);
		
		// Rod
		Author rod = new Author("Rod", "Johnson");
		Publisher worx = new Publisher("Worx", "California");
		Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
		rod.getBooks().add(noEJB);	
		
		authorRepository.save(rod);
		publishRepository.save(worx);
		bookRepository.save(noEJB);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {		
		initData();		
	}
}
