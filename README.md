
<h1 align="center">
  <a href="https://github.com/yanghaiji/JsonCleanseETL.git">
  <img src="https://github.com/yanghaiji/JsonCleanseETL/blob/main/doc/img/JsonCleanseETL03.png" 
  alt="Standard - JsonCleanseETL" width="500" ></a>
</h1>
<p align="center">
    <a href="https://spring.io/projects"><img src='https://img.shields.io/badge/license-Apache%202-borightgreen' 
    alt='License'/></a>
    <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/Spring%20Boot-2.6.4-brightgreen)" alt="spring version"></a>
    <a href="https://standardjs.com"><img src="https://img.shields.io/badge/code_style-standard-brightgreen.svg" 
    alt="Standard - Java Style Guide"></a>
</p>

## 项目描述

JSONCleanseETL是一款专业的数据清洗和转换工具，旨在为用户提供高效处理JSON格式数据的解决方案。
该工具不仅支持用户上传JSON数据进行处理，更具备通过HTTP自动调用指定URL获取数据的功能，并在数据到达后即刻进行清洗和转换，
以满足不同场景下的数据处理需求。这项创新技术为用户提供了一种前所未有的便捷性和灵活性，使其能够轻松应对多变的数据处理挑战。

## Logo Idea

JSONCleanseETL犹如蜘蛛，在错综复杂的数据网络中敏捷穿梭，巧妙地捕捉、清洗和转换着JSON数据。就像蜘蛛利用编织的网捕捉猎物一样，
JSONCleanseETL通过其独特的技艺，在数据的纷繁中寻找并处理信息。蜘蛛的网成为数据结构的象征，而蜘蛛捕猎的技巧则恰似JSONCleanseETL处理数据的过程。

## 主要功能

- JSON 数据清洗与校验
> JSONCleanseETL 提供了丰富的功能，能够自动识别并清洗 JSON 数据中的格式错误、无效值、重复记录等问题，确保数据的完整性和一致性。

- 数据结构转换与规范化
>该工具支持用户定义清洗规则和转换逻辑，可以根据需求对 JSON 数据进行结构转换、字段重命名、数据类型转换等操作，从而使数据达到所需的标准化格式。
- 支持多种数据输入方式
>JSONCleanseETL 提供了多种数据输入方式，包括上传 JSON 文件、直接粘贴 JSON 数据以及通过 HTTP 自动调用指定的 URL 获取数据，
为用户提供了更加灵活和便捷的数据导入方式。

## 测试样例

> 将项目下`doc/http/ETL.postman_collection.json` 的json文件导入到postman内进行测试

## JsonPath Test 工具

为方便大家验证编写的`json path`路径,已经保护敏感数据，`JsonCleanse`ETL也提供了界面友好的`json path`测试页面

启动项目访问`http://localhost:8088/etl/index`，效果如下

<img src="https://github.com/yanghaiji/JsonCleanseETL/blob/main/doc/img/jsonpath_test.png"  alt="Standard - jsonpath_test" width="500" height="300">

## 附录 

### JsonPath 语法介绍

### JsonPath的来源

看它的名字你就能知道，这家伙和JSON文档有关系，正如XPath之于XML文档一样，JsonPath为Json文档提供了解析能力，通过使用JsonPath，
你可以方便的查找节点、获取想要的数据，JsonPath是Json版的XPath。

### JsonPath语法

JsonPath的语法相对简单，它采用开发语言友好的表达式形式，如果你了解类C语言，对JsonPath就不会感到不适应。

JsonPath语法要点：

- `$` 表示文档的根元素
- `@` 表示文档的当前元素
- `.node_name` 或 `['node_name']` 匹配下级节点
- `[index]` 检索数组中的元素
- `[start:end:step]` 支持数组切片语法
- `*` 作为通配符，匹配所有成员
- `..` 子递归通配符，匹配成员的所有子元素
- `()` 使用表达式
- `?()`进行数据筛选

下表将列举所有支持的语法，并对XPath进行比较：

| XPath | JsonPath           | 说明                                                         |                                                          |
| :---- | :----------------- | :----------------------------------------------------------- | -------------------------------------------------------- |
| `/`   | `$`                | 文档根元素                                                   |                                                          |
| `.`   | `@`                | 当前元素                                                     |                                                          |
| `/`   | `.`或`[]`          | 匹配下级元素                                                 |                                                          |
| `..`  | `N/A`              | 匹配上级元素，JsonPath不支持此操作符                         |                                                          |
| `//`  | `..`               | 递归匹配所有子元素                                           |                                                          |
| `*`   | `*`                | 通配符，匹配下级元素                                         |                                                          |
| `@`   | `N/A`              | 匹配属性，JsonPath不支持此操作符                             |                                                          |
| `[]`  | `[]`               | 下标运算符，根据索引获取元素，**XPath索引从1开始，JsonPath索引从0开始** |                                                          |
| `     | `                  | `[,]`                                                        | 连接操作符，将多个结果拼接成数组返回，可以使用索引或别名 |
| `N/A` | `[start:end:step]` | 数据切片操作，XPath不支持                                    |                                                          |
| `[]`  | `?()`              | 过滤表达式                                                   |                                                          |
| `N/A` | `()`               | 脚本表达式，使用底层脚本引擎，XPath不支持                    |                                                          |
| `()`  | `N/A`              | 分组，JsonPath不支持                                         |                                                          |

注意：

- JsonPath的索引从0开始计数
- JsonPath中字符串使用单引号表示，例如:`$.store.book[?(@.category=='reference')]`中的`'reference'`

### JsonPath示例

下面是相应的JsonPath的示例，代码来源于https://goessner.net/articles/JsonPath/，JSON文档如下：

```json
{
    "store": {
        "book": [{
                "category": "reference",
                "author": "Nigel Rees",
                "title": "Sayings of the Century",
                "price": 8.95
            }, {
                "category": "fiction",
                "author": "Evelyn Waugh",
                "title": "Sword of Honour",
                "price": 12.99
            }, {
                "category": "fiction",
                "author": "Herman Melville",
                "title": "Moby Dick",
                "isbn": "0-553-21311-3",
                "price": 8.99
            }, {
                "category": "fiction",
                "author": "J. R. R. Tolkien",
                "title": "The Lord of the Rings",
                "isbn": "0-395-19395-8",
                "price": 22.99
            }
        ],
        "bicycle": {
            "color": "red",
            "price": 19.95
        }
    }
}
```

接下来我们看一下如何对这个文档进行解析：

| XPath                  | JsonPath                                   | Result                                   |
| :--------------------- | :----------------------------------------- | :--------------------------------------- |
| `/store/book/author`   | `$.store.book[*].author`                   | 所有book的author节点                     |
| `//author`             | `$..author`                                | 所有author节点                           |
| `/store/*`             | `$.store.*`                                | store下的所有节点，book数组和bicycle节点 |
| `/store//price`        | `$.store..price`                           | store下的所有price节点                   |
| `//book[3]`            | `$..book[2]`                               | 匹配第3个book节点                        |
| `//book[last()]`       | `$..book[(@.length-1)]`，或 `$..book[-1:]` | 匹配倒数第1个book节点                    |
| `//book[position()<3]` | `$..book[0,1]`，或 `$..book[:2]`           | 匹配前两个book节点                       |
| `//book[isbn]`         | `$..book[?(@.isbn)]`                       | 过滤含isbn字段的节点                     |
| `//book[price<10]`     | `$..book[?(@.price<10)]`                   | 过滤`price<10`的节点                     |
| `//*`                  | `$..*`                                     | 递归匹配所有子节点                       |
