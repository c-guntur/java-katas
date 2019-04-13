# Unsafe Replacement: VarHandles - Purpose

* **Safety** -  JVM cannot be placed in a corrupt memory state : 
  * Content data types must match or be ‘castable’ to field type
  * Array indexes must exist in order to be accessed/written to
* **Integrity** - Field access rules cannot be violated :
  * Same rules as for **`getfield`** and **`putfield`** byte codes
  * A final field cannot be updated
* **Performance** - Must be similar to **`Unsafe`** operations     
* **Usability** - Friendlier, consistent API compared to **`Unsafe`**

[<< Prev](page16.md) 
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; 
[Next >>](page18.md) 