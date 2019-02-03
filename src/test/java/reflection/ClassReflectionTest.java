package reflection;

import child.Child;
import child.ChildInterface;
import org.junit.jupiter.api.Test;
import parent.Parent;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mtumilowicz on 2019-01-30.
 */
class ClassReflectionTest {

    private Class<Child> childClass = Child.class;
    
    @Test
    void modifiers() {
        assertEquals(Modifier.toString(childClass.getModifiers() & Modifier.classModifiers()), "public abstract");
    }
    
    @Test
    void name() {
        assertEquals(childClass.getName(), "child.Child");
    }
    
    @Test
    void simpleName() {
        assertEquals(childClass.getSimpleName(), "Child");
    }
    
    @Test
    void superclass_notObject() {
        assertEquals(childClass.getSuperclass(), Parent.class);
    }
    
    @Test
    void superclass_object() {
        assertNull(Object.class.getSuperclass());
        assertEquals(Parent.class.getSuperclass(), Object.class);
    }
    
    @Test
    void interfaces_notEmpty() {
        assertArrayEquals(childClass.getInterfaces(), new Class<?>[]{ChildInterface.class, Serializable.class});
    }

    @Test
    void interfaces_empty() {
        assertArrayEquals(Object.class.getInterfaces(), new Class<?>[]{});
    }
    
    @Test
    void typeParameters_notEmpty() {
        assertEquals(Arrays.toString(childClass.getTypeParameters()), "[T]");
    }

    @Test
    void typeParameters_empty() {
        var typeParameters = Object.class.getTypeParameters();
        assertEquals(typeParameters.length, 0);
    }
    
    @Test
    void genericString() {
        assertEquals(childClass.toGenericString(), "public abstract class child.Child<T>");
    }
    
    @Test
    void packageName() {
        assertEquals(childClass.getPackageName(), "child");
    }
    
    @Test
    void moduleName() {
        assertTrue(childClass.getModule().toString().contains("unnamed module"));
        assertNull(childClass.getModule().getName());
    }
}
