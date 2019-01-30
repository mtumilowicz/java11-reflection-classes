package child;

import parent.Parent;

import java.io.Serializable;

/**
 * Created by mtumilowicz on 2019-01-30.
 */
public abstract class Child<T> extends Parent implements ChildInterface, Serializable {

    public static void main(String[] args) {
        System.out.println(Child.class.getModule().getName());
    }
}
