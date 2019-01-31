# java11-reflection-classes
Class descriptors obtained via reflection.

_Reference_: 

# reflection
## preface
**Reflection** is the ability to obtain and change the program's 
state during **its execution**.

Reflection is composed of:
* **introspection** - querying information
    * **structural introspection** - querying about the 
    implementation of the data and code
    * **behavioral introspection** - querying about the
    runtime environment
* **intercession** - modifying state, adding new behaviours
    * **structural intercession** - modifying (or creating) new 
    data structures and code
    * **behavioral intercession** - modifying the runtime 
    environment
 
## java   
**Reflection in Java** is mainly - introspection, we can query
about class information:
* fields,
* constructors,
* methods, 
* modifiers, 
* superclass. 

Intercession is supported in very limited way, we can:
* create an instance of a class whose name is not known
  in the compile-time
* invoke methods
* get/set fields

but we **can't:**
* change the data structure at runtime (add a new field or a method):
* change the method execution (behavioral intercession)

# project description
We will show how to obtain basic info about class (such as
modifiers, implemented interfaces, etc.).

1. We have simple class hierarchy in module family:
    * module-info
        ```
        module family {
        }
        ```
    * parent
        ```
        public class Parent implements ParentInterface {
        }
        
        public interface ParentInterface {
        }
        ```
    * child
        ```
        public abstract class Child<T> extends Parent implements ChildInterface, Serializable {
        }
        
        public interface ParentInterface {
        }
        ```
All tests are in `reflection.ClassReflection` class
* modifiers
    ```
    assertEquals(Modifier.toString(childClass.getModifiers() & Modifier.classModifiers()), "public abstract");
    ```
* all modifiers (since java 8)
    ```
    assertEquals(childClass.toGenericString(), "public abstract class child.Child<T>");
    ```
* name
    ```
    assertEquals(childClass.getName(), "child.Child");
    ```
* simple name
    ```
    assertEquals(childClass.getSimpleName(), "Child");
    ```
* superclass
    ```
    assertEquals(childClass.getSuperclass(), Parent.class);
    ```
    ```
    assertNull(Parent.class.getSuperclass());
    ```
* interfaces
    ```
    assertArrayEquals(childClass.getInterfaces(), new Class<?>[]{ChildInterface.class, Serializable.class});
    ```
    ```
    assertArrayEquals(Object.class.getInterfaces(), new Class<?>[]{});
    ```
* type parameters
    ```
    assertEquals(Arrays.toString(childClass.getTypeParameters()), "[T]");
    ```
    ```
    var typeParameters = childClass.getTypeParameters();
    assertEquals(typeParameters.length, 0);
    ```
* package name
    ```
    assertEquals(childClass.getPackageName(), "child");
    ```
* module name
    * in tests:
        ```
        assertTrue(childClass.getModule().toString().contains("unnamed module"));
        assertNull(childClass.getModule().getName());
        ```
    * in main:
        ```
        public static void main(String[] args) {
                System.out.println(Child.class.getModule().getName());
        }
        ```
        will print: family