InputTemplateJ
https://github.com/satoshi-ron-tamura/InputTemplateJ

A template engine used for program input written in the Java programming language. InputTemplateJ is released under the MIT license (http://opensource.org/licenses/mit-license.php).

1. Introduction
--------------------

There are many template engines. Most of them are used for program output, typically for outputing HTML. By contrast, the InputTemplateJ is used for program input. With the template engines for program output, we make the template file in which 'template variables' are embedded. The program set template variables to the template engine before output. With the InputTemplateJ, we make the template file with 'content structure'. We can get the tree structure like the DOM (Document Object Model) from the template engine.

2. Examples
--------------------

```sql
select [$column_clause$[
 product_id
][ ,product_name
][ ,product_type_no]]
from product
where [$where_clause$[
 product_id = ?
][ and product_name = ?]]
order by [$order_by_clause$[
 product_id]]
```

```java

```





