### Important

##### Requirements: jdk 11
##### Transaction support - https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/transaction.html
##### Money values support - https://pl.wikipedia.org/wiki/IEEE_754
##### Default login - username: user, password: check console for message `Using generated security password:`
##### Swagger access: http://localhost:8080/swagger-ui.html


### Next functionalities:

#### 1. [Admin] Create entity Product with CRUD methods, including tests
#### Status: TODO

Product entity:
- id
- name (required)
- price (BigDecimal)  (required)
- quantity  (required)
- category: Category  (required)

#### 2. [Admin] Create entity Category with CRUD methods, including tests
#### Status: TODO
Category entity:
- id  (required)
- name  (required)
- parentCategory: Category
- subCategories: Set<Category>

#### 

