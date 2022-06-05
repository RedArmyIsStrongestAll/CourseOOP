package com.example.kursuch.customType.color;

import com.example.kursuch.customType.color.Color;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ColorEnumType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] {Types.VARCHAR};
    }

    @Override
    public Class returnedClass() {
        return Color.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        String first = ((Color)o).getTitle();
        String second = ((Color)o1).getTitle();
        return first.equals(second);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    //ПЕРВЫЙ ПРИ ИЗВЛЕЧЕНИИ
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        Object checkNull = resultSet.getObject(strings[0]);
        String test = resultSet.getString(strings[0]);
        if (checkNull == null) {
            return Color.NO;
        } else {
            String value = StringType.INSTANCE.nullSafeGet(resultSet, strings[0], sharedSessionContractImplementor);
            Color colorOutput = Color.fromString(value);
            System.out.println("зашёл в десериализацию глубокую, colorOutput: " + value);
            return colorOutput;
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        //Р А Б О Т А Е Т   Т О Л Ь К О   Д Л Я  JPQL

         /*if (o == null){
            StringType.INSTANCE.nullSafeSet(preparedStatement, null, i, sharedSessionContractImplementor);
        }
        else{
            Color colorInput = (Color) o;
            StringType.INSTANCE.nullSafeSet(preparedStatement, colorInput.getTitle(), i, sharedSessionContractImplementor);
        }*/
    }

    @Override
    //ВТОРОЕ ПРИ ИЗВЛЕЧЕНИИ
    public Object deepCopy(Object o /*ЭТО ТО, ЧТО МЫ ПОЛУЧАЕМ ИЗ SQL ПРИ МЕТОДЕ GET*/) throws HibernateException {
        Color copy = null;
        if (o != null) {
            Color original = (Color) o;
            copy = Color.fromString(original.getTitle());
        }
        return copy;
    }

    @Override
    //можно менять
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o; //ПРОСТО ТАК ПИШИ
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable; //ПРОСТО ТАК ПИШИ
    }

    @Override
    //хз в update не учавствует
    public Object replace(Object original, Object target, Object hz) throws HibernateException {
        System.out.println("зашёл в замену");
        Color input = (Color) original;
        Color copyInput = Color.fromString(input.getTitle());
        return original;
    }
}
