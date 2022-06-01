import com.example.kursuch.models.Cat;
import com.example.kursuch.otherTools.Color;
import com.example.kursuch.repositories.RepositoryCatPostgres;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {

        List<Cat> listCat = new ArrayList<>();
        Cat cat1 = new Cat(1, "sdafsdf", "sadfsaf", Color.BLACK, "sdfdf");
        Cat cat2 = new Cat(2, "sdafsdf", "sadfsaf", Color.BLACK, "sdfdf");
        Cat cat3 = new Cat(3, "sdafsdf", "sadfsaf", Color.BLACK, "sdfdf");
        Cat cat = new Cat(100, "df", "df", Color.WHITE, "ds");
        listCat.add(cat1);
        listCat.add(cat2);
        listCat.add(cat3);
        System.out.println(listCat);

        listCat.stream();
    }
}
