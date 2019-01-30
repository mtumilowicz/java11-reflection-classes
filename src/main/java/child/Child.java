package child;

import parent.Parent;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Created by mtumilowicz on 2019-01-30.
 */
public abstract class Child<T> extends Parent implements ChildInterface, Serializable {
    public static void main(String[] args) {
        Class<Child> childClass = Child.class;

        System.out.println(Modifier.toString(childClass.getModifiers() & Modifier.classModifiers()));
        System.out.println(childClass.getSimpleName());
        System.out.println(childClass.getName());
        System.out.println(childClass.getSuperclass()); // if object => null
        System.out.println(Arrays.toString(childClass.getInterfaces()));
        System.out.println(Arrays.toString(childClass.getTypeParameters()));
        System.out.println(childClass.toGenericString());
        System.out.println(childClass.getPackageName());
        System.out.println(childClass.getPackage());
        System.out.println(childClass.getModule());
    }
}
