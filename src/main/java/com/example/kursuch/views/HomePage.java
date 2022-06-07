package com.example.kursuch.views;

import com.example.kursuch.models.Cat;
import com.example.kursuch.services.ServicesCat;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;



@Route("")
public class HomePage extends VerticalLayout {

    private Grid<Cat> grid;

    private final ServicesCat service;
    private final EditorForHomePage editor;

    TextField textFieldSearch;
    Button buttonAddNewCat;


    @Autowired
    public HomePage(EditorForHomePage editor, ServicesCat service){

        this.service = service;

        //setup update form
        this.editor = editor;

        //setup toolPanel
        textFieldSearch = new TextField("", "Поиск");
        buttonAddNewCat = new Button("Добавить кота");
        HorizontalLayout toolsPanel = new HorizontalLayout(textFieldSearch, buttonAddNewCat);

        //setup grid
        grid = new Grid<>();
        grid.addColumn(Cat::getId).setHeader("Id").setSortable(true);
        grid.addColumn(Cat::getName).setHeader("Имя").setSortable(true);
        grid.addColumn(Cat::getNameFeline).setHeader("Имя на кошаьем").setSortable(true);
        grid.addColumn((Cat cat) -> cat.getColor().getTitle()).setHeader("Цвет шёрстки").setSortable(true);
        grid.addColumn(Cat::getParod).setHeader("Парода").setSortable(true);
        //
        /*grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addItemDoubleClickListener(event -> {
            Cat cat = event.getItem();
            editor.editCat(cat);
            System.out.println("send cat");
        });*/
        grid.asSingleSelect()
                .addValueChangeListener(event -> {
                    Cat cat = event.getValue();
                    if (cat != null) {
                        editor.editCat(cat);
                        System.out.println("send cat  " + cat);
                    }
                });

        //setup searchTextField
        textFieldSearch.setValueChangeMode(ValueChangeMode.EAGER);
        textFieldSearch.addValueChangeListener(field -> sortingView(field.getValue()));

        //setup addNewCatButton
        buttonAddNewCat.addClickListener(e -> {
            editor.editCat(null);
            System.out.println("send null");
        });

        //setup instruction for update data
        editor.setChange(() -> {
            editor.setVisible(false);
            sortingView(textFieldSearch.getValue());
        });

        add(toolsPanel, grid, editor);

        sortingView("");
    }

    private void sortingView(String name){

        if (name.isEmpty()){
            try{
                grid.setItems(service.readAll());
            }
            catch (Exception e){
                Notification.show(e.getMessage()).addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }
        else{
            try{
                grid.setItems(service.searchByNames(name));
            }
            catch (Exception e){
                Notification.show(e.getMessage()).addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }
    }

}
