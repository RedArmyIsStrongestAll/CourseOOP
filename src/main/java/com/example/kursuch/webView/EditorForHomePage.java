package com.example.kursuch.webView;

import com.example.kursuch.customType.color.Color;
import com.example.kursuch.models.Cat;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@UIScope
public class EditorForHomePage extends VerticalLayout implements KeyNotifier {

    //тут должен быть сервис
    private Cat cat;

    @PropertyId("name")
    TextField nameTextField;
    @PropertyId("nameFeline")
    TextField felineNameTextField;
    @PropertyId("parod")
    TextField parodTextField;
    ComboBox<Color> colorComboBox;


    Button save = new Button("Сохарнить", VaadinIcon.CHECK.create());
    Button cancel = new Button("Отмена", VaadinIcon.ARROW_BACKWARD.create());
    Button delete = new Button("Удалить", VaadinIcon.TRASH.create());

    Binder<Cat> binder= new Binder<>(Cat.class);

    @Setter
    ChangeHandler change; //поробуй потом нахуй удалить

    public interface ChangeHandler{
        void onChange();
    }

    @Autowired
    public EditorForHomePage() {

        setVisible(false);

        //setup update-create form
        nameTextField = new TextField("Имя");
        felineNameTextField = new TextField("Имя на кошачьем");
        parodTextField = new TextField("Парода");
        colorComboBox = new ComboBox<>("Цвет");
        colorComboBox.setItems(Color.arrColor);
        colorComboBox.setItemLabelGenerator(Color::getTitle);
        //
        binder.forField(nameTextField).bind(Cat::getName,Cat::setName);
        binder.forField(felineNameTextField).bind(Cat::getNameFeline,Cat::setNameFeline);
        binder.forField(parodTextField).bind(Cat::getParod,Cat::setParod);
        binder.forField(colorComboBox).bind(Cat::getColor, Cat::setColor);
        binder.readBean(cat);
        //
        save = new Button("Сохарнить", VaadinIcon.CHECK.create());
        cancel = new Button("Отмена", VaadinIcon.ARROW_BACKWARD.create());
        delete = new Button("Удалить", VaadinIcon.TRASH.create());
        HorizontalLayout divAction = new HorizontalLayout(save, cancel, delete);
        addKeyPressListener(Key.ENTER, e -> save());
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCat(cat));

        //setup style
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        add(nameTextField, felineNameTextField, colorComboBox, parodTextField, divAction);
    }

    private void save() {

        //repository.create(cat);
        change.onChange();
    }

    private void delete() {
        //repository.delete(cat.getId());

        change.onChange();
    }

    public void editCat(Cat selectCat) {

        if (selectCat != null) {
            //update cate
            //находи по id кота на которого нажали, чтобы получить его экземпляр
            binder.setBean(this.cat);
        } else {
            //create cat
            cat = new Cat();
            binder.setBean(this.cat);
        }

        setVisible(true);
        nameTextField.focus();
    }
}
