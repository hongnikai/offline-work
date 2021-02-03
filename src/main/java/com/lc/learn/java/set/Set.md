1.HashSet

集合类型主要有3种：set(集）、list(列表）和map(映射)。
三者关系
                                              Iterator
                                            
                                    collection     ListIterator     Map        
                              
                              Set           List                HashMap     TreeMap
                              
                    HashSet TreeSet  ArrayList LinkedList    LinkedHashMap             
                    
                    
Set
set接口是Collection接口的一个子接口，是无序的，set中不包含重复的元素，也就是说set中不存在两个这样的元素a1.equals(a2)结果为true。
又因为Set接口提供的数据结构是数学意义上的集合概念的抽象，因此他支持对象的添加和删除。                 
Set集合不允许包含相同的元素，如果试把二个相同的元素加入到同一个Set集合中，则添加操作失败。
Set判断二个对象是否相同不是使用 ==运算符，而是根据equals()方法。
Set接口有三个实现类，HashSet,LinkedHashSet,TreeSet,但它的典型实现是HashSet，大多数时候使用Set集合时都是用这个实现类。
对于存放在Set容器中的对象，对应的类一定要重写equals()方法和hashCode(Object obj)方法，以实现对象相等规则。即：“相等的对象必须具有相等的散列码”。
                    
TreeSet
以升序对对象排序

                    