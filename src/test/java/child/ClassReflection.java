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
    void superclass() {
        assertEquals(childClass.getSuperclass(), Parent.class); // if no superclass other than object => null
    }
    
    @Test
    void interfaces() {
        assertArrayEquals(childClass.getInterfaces(), new Class<?>[]{ChildInterface.class, Serializable.class});
    }
    
    @Test
    void typeParameters() {
        assertEquals(Arrays.toString(childClass.getTypeParameters()), "[T]");
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
