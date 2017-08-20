package ee.valja7.experiment.springboot.hello.view;

import ee.valja7.experiment.springboot.hello.domain.User;
import ee.valja7.experiment.springboot.hello.persistence.UsersRepository;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;


public class UserLazyModel extends LazyDataModel<User> {
    private UsersRepository repository;
    private List<User> data;

    public UserLazyModel(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        data = repository.findAll();
        this.setRowCount(data.size());
        this.setPageSize(pageSize);
        return data;
    }

    @Override
    public Object getRowKey(User object) {
        return object.getUsername();
    }

    @Override
    public User getRowData(String rowKey) {
        return data.stream()
                .filter(u -> u.getUsername().equals(rowKey))
                .findFirst()
                .get();
    }
}
