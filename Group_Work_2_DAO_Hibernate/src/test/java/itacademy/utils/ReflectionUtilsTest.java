package itacademy.utils;

import itacademy.entity.EntityClassWithTableName;
import itacademy.entity.EntityClassWithoutTableName;
import itacademy.entity.NotEntityClass;
import itacademy.exceptions.unchecked.AnnotationMissingException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionUtilsTest {
    @Test
    public void getTableNameByClassTest() {
        String expectedTableName = "TestTable";
        String actualTableName =
                ReflectionUtils.getTableNameByClass(EntityClassWithTableName.class);

        assertEquals(expectedTableName, actualTableName);

        expectedTableName = "EntityClassWithoutTableName";
        actualTableName =
                ReflectionUtils.getTableNameByClass(EntityClassWithoutTableName.class);

        assertEquals(expectedTableName, actualTableName);

        assertThrows(AnnotationMissingException.class,
                () -> ReflectionUtils.getTableNameByClass(NotEntityClass.class));
    }

    @Test
    public void getIdFieldTest() throws IllegalAccessException {
        EntityClassWithTableName objWithAnnotation = new EntityClassWithTableName();
        NotEntityClass objWithoutAnnotation = new NotEntityClass();
        objWithAnnotation.setId(1);

        Field field = null;
        field = ReflectionUtils.getIdField(objWithAnnotation);
        assertNotNull(field);

        field = ReflectionUtils.getIdField(objWithoutAnnotation);
        assertNull(field);
    }

    @Test
    public void setIdTest() throws IllegalAccessException {
        EntityClassWithTableName objWithAnnotation = new EntityClassWithTableName();
        NotEntityClass objWithoutAnnotation = new NotEntityClass();
        Integer id = 5;

        ReflectionUtils.setId(objWithAnnotation, id);
        assertEquals(id, objWithAnnotation.getId());

        ReflectionUtils.setId(objWithoutAnnotation, id);
        assertNotEquals(id, objWithoutAnnotation.getId());
    }

    @Test
    public void getColumnsNamesTest() {
       String[] expectedColumnsNames = {"id", "name", "last_name"};

        List<String> actualColumnsNames =
                ReflectionUtils.getColumnsNames(EntityClassWithTableName.class);

        assertArrayEquals(expectedColumnsNames, actualColumnsNames.toArray());
    }

    @Test
    public void getColumnsValuesTest() throws IllegalAccessException {
        String[] expectedValues = {"1", "name", "surname"};
        EntityClassWithTableName objWithAnnotation = new EntityClassWithTableName();
        objWithAnnotation.setId(1);
        objWithAnnotation.setName("name");
        objWithAnnotation.setLastName("surname");

        List<String> actualValues =
                ReflectionUtils.getColumnsValues(objWithAnnotation);

        assertArrayEquals(expectedValues, actualValues.toArray());
    }
}
