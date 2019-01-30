package child;

import org.junit.jupiter.api.Test;
import parent.Parent;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mtumilowicz on 2019-01-30.
 */
class ClassReflection {

//    private Class<Child> childClass = Child.class;

    @Test
    void info() {
        Class<Child> childClass = Child.class;

        assertEquals(Modifier.toString(childClass.getModifiers() & Modifier.classModifiers()), "public abstract");

        assertEquals(childClass.getName(), "child.Child");

        assertEquals(childClass.getSimpleName(), "Child");

        assertEquals(childClass.getSuperclass(), Parent.class); // if no superclass other than object => null

        assertArrayEquals(childClass.getInterfaces(), new Class<?>[]{ChildInterface.class, Serializable.class});

        assertEquals(Arrays.toString(childClass.getTypeParameters()), "[T]");

        assertEquals(childClass.toGenericString(), "public abstract class child.Child<T>");

        assertEquals(childClass.getPackageName(), "child");

        assertTrue(childClass.getPackage().getName().contains("unnamed module"));
    }
}
