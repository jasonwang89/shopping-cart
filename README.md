# shopping cart

### Environment set up

 java version   java 11.0.12

1. go into folder shopping-cart/src/main/java/com/shopping/cart/
2. run ShoppingCartApplication locally.


###  API
1. check storage
   http://localhost:8443/shopping-cart/storage/{itemId}
2. get item detail
   http://localhost:8443/shopping-cart/item/{itemId}
3. create or modify items in shopping cart.
   http://localhost:8443/shopping-cart/cart?userId=8001&cartId=221921&itemId=1001&number=1
   #### Note:
   cartId is empty, this api creates a new cart.
  provide cartId, this api can add/delete items amount
4. get all items in the cart
   http://localhost:8443/shopping-cart/cart?userId=8001&cartId=221921
5. clear all items in the cart
   http://localhost:8443/shopping-cart/cart/clear?userId=8001&cartId=497074
6. place order
   http://localhost:8443/shopping-cart/order/place?userId=8001&cartId=221921&cardNo=123456&expireDate=2028/08/08&cvc=888
7. get order details
   http://localhost:8443/shopping-cart/order/{orderId}

### Running
We can call these api to modify database

### Database data validation
We can connect to MySQL to get db details or run above apis to check data.
url: jdbc:mysql://rm-uf6412jm3hb7ra8i4io.mysql.rds.aliyuncs.com/ecdb
username: root
password: Pass123@

