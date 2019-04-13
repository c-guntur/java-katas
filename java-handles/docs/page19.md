# Unsafe -to - VarHandle Recap

* Get a MethodHandles.Lookup via :
  * **`lookup().in(`** requestedLookupClass **`)`**
  * **`privateLookupIn(`** targetClass, lookup **`)`**
* Find a VarHandle :
  * For fields :
    * **`findVarHandle(`** receiverClass, attributeName, attributeType **`)`**  
  * For array elements : 
    * **`arrayElementVarHandle(`** arrayClass **`)`**
    
    
[<< Prev](page18.md) 
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; 
[Next >>](page20.md)