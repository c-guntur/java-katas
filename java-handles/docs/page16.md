# Unsafe Replacement: VarHandle (Java 9)

* JSR-292 led to lightweight references to attributes
* The references are called VarHandles
* Standard replacements for features in :
  * **`java.util.concurrent.atomic.*`**
  * **`sun.misc.Unsafe`**
* The VarHandle API provides :
  * **Field and array index element access**
  * **Memory fences** for fine-grained control of memory ordering
  * A strong **reachability-fence** operation for an object

[<< Prev](page15.md) 
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; 
[Next >>](page17.md) 