package ee.valja7.experiment.springboot.hello.view;


import ee.valja7.experiment.springboot.hello.domain.Book;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "book", eager = true)
@RequestScoped
public class BookView extends Book {

    public BookView() { }
}
