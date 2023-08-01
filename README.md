
# Shopping cart

---

### Environment set up

 java version   java 11.0.12

1. go into folder shopping-cart/src/main/java/com/shopping/cart/
2. run ShoppingCartApplication locally.


###  Postman collection

I uploaded postman collection to postman folder.
* Import json file to postman. It has all APIs showed below.

###  API
1. check storage

   http://localhost:8443/shopping-cart/storage/{itemId}
2. get item detail

   http://localhost:8443/shopping-cart/item/{itemId}
3. create or modify items in shopping cart.

   http://localhost:8443/shopping-cart/cart?userId=8001&cartId=221921&itemId=1001&number=1
   #### Note:
* cartId is empty, this api creates a new cart.
* provide cartId, this api can add/delete items amount
4. get all items in the cart

   http://localhost:8443/shopping-cart/cart?userId=8001&cartId=221921
5. clear all items in the cart

   http://localhost:8443/shopping-cart/cart/clear?userId=8001&cartId=497074
6. place order

   http://localhost:8443/shopping-cart/order/place?userId=8001&cartId=221921&cardNo=123456&expireDate=2028/08/08&cvc=888
7. get order details

   http://localhost:8443/shopping-cart/order/{orderId}
8. get all items

   http://localhost:8443/shopping-cart/items
9. get all users

   http://localhost:8443/shopping-cart/users
10. get all orders

   http://localhost:8443/shopping-cart/orders

---

### Running
We can call these api to modify database

---

### Database data validation
We can connect to MySQL to get db details or run above apis to check data.
* url: jdbc:mysql://rm-uf6412jm3hb7ra8i4io.mysql.rds.aliyuncs.com/ecdb
* username: root
* password: Pass123@

#### Data validation:

* Item details  ```select * from item```
* order table  ```select * from order_data```
* storage table  ```select * from storage```
* user table  ```select * from user```
* user_cart table  ```select * from user_cart```

---

### Concurrent
There are couple options to handle concurrent scenario.
* This time I use CAS, add version key in table. when place order and update storage will check version. It will handle concurrent scenario.

* use Redis to create distribute lock to lock the storage item when try to place order.

---

### Improvement
* Split table and id key generate by using sparkflake algorithm.
* Use redis to add distribute lock and read/write through redis.
* Exception handling and logging.
* Rollback solution and validation
* JUnit, Integration Test and Automation test

---
