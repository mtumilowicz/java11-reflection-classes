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
* change the data structure at runtime:
    * add a new field or a method
* change the method execution or add a new method (behavioral intercession)

