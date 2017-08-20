package ee.valja7.experiment.springboot.hello.view;

import org.primefaces.model.LazyDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CrudModel<T, ID extends Serializable, R extends JpaRepository<T, ID>> {
    @Inject
    R repository;

    T currentItem;
    ID currentItemId;
    Class<T> clazz;
    List<T> selectedItems = new ArrayList<>();
    private LazyDataModel<T> lazyModel;

    public R getRepository() {
        return repository;
    }

    public List<T> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<T> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public ID getCurrentItemId() {
        return currentItemId;
    }

    public void setCurrentItemId(ID id) {
        this.currentItemId = id;
    }

    public T getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(T currentItem) {
        this.currentItem = currentItem;
    }

    public Collection<T> findAll() {
        return repository.findAll();
    }

    public void init() throws IllegalAccessException, InstantiationException {
        if (currentItemId != null)
            this.currentItem = repository.findOne(currentItemId);
        if (currentItem == null && clazz != null)
            this.currentItem = clazz.newInstance();
    }

    public void init(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        this.currentItem = clazz.newInstance();
    }

    public void save() {
        this.repository.save(currentItem);
    }

    public String save(String url) {
        save();
        return url + "?facesRedirect=true";
    }

    public String create() {
        repository.save(currentItem);
        return "success";
    }

    public void delete() {
        throw new NotImplementedException();
    }

    public void onTabChange() {
        throw new NotImplementedException();
    }

    public void createOrUpdateListener() {
        throw new NotImplementedException();
    }

    public void clearListener() {
        throw new NotImplementedException();
    }

    public LazyDataModel<T> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<T> lazyModel) {
        this.lazyModel = lazyModel;
    }

}
