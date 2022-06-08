package com.example.kursuch.views;

import com.example.kursuch.customType.color.Color;
import com.example.kursuch.models.Cat;
import com.example.kursuch.services.ServicesCat;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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

    private final ServicesCat service;
    private Cat cat;

    @PropertyId("name")
    TextField nameTextField;
    @PropertyId("nameFeline")
    TextField felineNameTextField;
    @PropertyId("parod")
    TextField parodTextField;
    @PropertyId("color")
    ComboBox<Color> colorComboBox;


    Button save = new Button("Сохарнить", VaadinIcon.CHECK.create());
    Button cancel = new Button("Отмена", VaadinIcon.ARROW_BACKWARD.create());
    Button delete = new Button("Удалить", VaadinIcon.TRASH.create());

    Binder<Cat> binder = new Binder<>(Cat.class);

    @Setter
    ChangeHandler change; //поробуй потом нахуй удалить

    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public EditorForHomePage(ServicesCat service) {
        System.out.println("загрузка формы edit");

        this.service = service;

        setVisible(false);

        //setup update-create form
        nameTextField = new TextField("Имя");
        nameTextField.getElement().setAttribute("autocomplete", "off");
        felineNameTextField = new TextField("Имя на кошачьем");
        felineNameTextField.getElement().setAttribute("autocomplete", "off");
        parodTextField = new TextField("Парода");
        parodTextField.getElement().setAttribute("autocomplete", "off");
        colorComboBox = new ComboBox<>("Цвет");
        colorComboBox.setItems(Color.arrColor);
        colorComboBox.setItemLabelGenerator(Color::getTitle);
        //
        binder.bindInstanceFields(this);
        //
        cancel = new Button("Отмена", VaadinIcon.ARROW_BACKWARD.create());
        cancel.addClickListener(e -> setVisible(false));
        delete = new Button("Удалить", VaadinIcon.TRASH.create());
        delete.addClickListener(e -> delete());
        save = new Button("Сохарнить", VaadinIcon.CHECK.create());
        save.addClickListener(e -> save());
        addKeyPressListener(Key.ENTER, e -> save());
        HorizontalLayout divAction = new HorizontalLayout(save, cancel, delete);

        //setup style
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        add(nameTextField, felineNameTextField, colorComboBox, parodTextField, divAction);

    }

    private void save() {

        boolean existing = cat.getId() != 0;

        try {
            if(existing){
                service.update(cat.getId(), cat);
            }
            else{
                service.create(cat);
            }
            change.onChange();
        } catch (Exception e) {
            Notification.show(e.getMessage()).addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        }
    }

    private void delete() {

        try {
            long id = cat.getId();
            service.delete(id);
            change.onChange();
        } catch (Exception e) {
            Notification.show(e.getMessage()).addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        }
    }

    public void editCat(Cat selectCat) {

        if (selectCat == null) {
            setVisible(false);
            return;
        }

        this.cat = selectCat;
        binder.setBean(cat);

        setVisible(true);
    }
}
