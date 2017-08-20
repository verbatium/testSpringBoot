package ee.valja7.experiment.springboot.hello.view;

import ee.valja7.experiment.springboot.hello.domain.Authority;
import ee.valja7.experiment.springboot.hello.persistence.AuthoritiesRepository;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

public class AuthorityLazyModel extends LazyDataModel<Authority> {
    private AuthoritiesRepository repository;
    private List<Authority> data;

    public AuthorityLazyModel(AuthoritiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Authority> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        data = repository.findAll();
        this.setRowCount(data.size());
        this.setPageSize(pageSize);
        return data;
    }

    @Override
    public String getRowKey(Authority object) {
        return object.getAuthority();
    }

    @Override
    public Authority getRowData(String rowKey) {
        return data.stream()
                .filter(o -> o.getAuthority().equals(rowKey))
                .findFirst().get();
    }
}
