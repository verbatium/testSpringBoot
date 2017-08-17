package ee.valja7.experiment.springboot.hello.view;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.List;


@Named
public class ThemeSwitcherView {

    private List<Theme> themes;

    @Autowired
    private ThemeService themeService;

    @PostConstruct
    public void init() {
        themes = themeService.getThemes();
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemeService(ThemeService themeService) {
        this.themeService = themeService;
    }
}