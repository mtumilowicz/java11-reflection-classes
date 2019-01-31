[![Build Status](https://travis-ci.com/mtumilowicz/java11-reflection-classes.svg?token=PwyvjePQ7aiAX51hSYLE&branch=master)](https://travis-ci.com/mtumilowicz/java11-reflection-classes)

# java11-reflection-classes
Class descriptors obtained via reflection.

# preface
https://github.com/mtumilowicz/java-reflection

# project description
We will show how to obtain basic info about class (such as
modifiers, implemented interfaces, etc.).

1. We have simple class hierarchy in the `family module`:
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
All tests are in `reflection.ClassReflection` class:
* modifiers
    ```
    assertEquals(Modifier.toString(childClass.getModifiers() & Modifier.classModifiers()), "public abstract");
    ```
    * `int getModifiers()` - returns modifiers as int (https://github.com/mtumilowicz/java11-ORed-container)
    * `int classModifiers()` - returns possible modifiers for class  
    note, that we have other similar methods:
        * `interfaceModifiers()`
        * `constructorModifiers()`
        * `methodModifiers()`
        * `fieldModifiers()`
        * `parameterModifiers()` (since java 8)
    * `Modifier.toString(int mod)` converts from int to
    string representation
* all modifiers at once (since java 8)
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
    * if the only superclass is `Object` the method returns null
        ```
        assertNull(Parent.class.getSuperclass());
        ```
* interfaces

    * only interfaces explicitly declared (inherited are not returned)
        ```
        assertArrayEquals(childClass.getInterfaces(), new Class<?>[]{ChildInterface.class, Serializable.class});
        ```
    * if there is no interfaces, the array is empty
        ```
        assertArrayEquals(Object.class.getInterfaces(), new Class<?>[]{});
        ```
* type parameters
    ```
    assertEquals(Arrays.toString(childClass.getTypeParameters()), "[T]");
    ```
    * if there is no type parameters, the array is empty
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
    * however in main:
        ```
        public static void main(String[] args) {
                System.out.println(Child.class.getModule().getName());
        }
        ```
        will print: family