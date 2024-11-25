# products-management
Mobile client application developed in Kotlin with server developed in Node.js.

To run the server, execute:
```
npm install
npm start
```

Mobile client application for products management. A store uses a mobile application to manage the products offered. The storekeeper manages the products and the customers can query the stocks.

## The following details are stored on the server side:
- Id - Product identifier. An integer value greater than zero.
- Name - Product name. A string value.
- Type - A string representing the product type.
- Quantity - An integer that represents the quantity in the warehouse.
- Price - An integer that represents the price of the product.
- Discount - An integer (0-100) that represents the discount applied to the product.
- Status - A Boolean value that specifies whether the discount is applied.

## The application offers the following features:

● Warehouseman section (separate screen).
1. Register the product. Using the POST /product call specifying all the attributes of a product. Available online and offline.
2. View products, in a list. Using the GET /products call, the storekeeper will access all products. The elements in the list show: the id, name, type, price, and quantity of a product. If the application is offline, and we don't have it products in the local database, a corresponding message and a button will be displayed to retry the call. Once the first call succeeds, only the products from offline/local database.

● Client section (separate screen) - only available online.
1. List of the cheapest products. Using the same GET /products call, the application will determine and display only the cheapest product for each type.
