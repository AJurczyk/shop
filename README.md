# Shopping platform

## About the app:
App that provides REST API for calculating a price for a given product and its amount.<br>

Products in the system are identified by UUID. There is the possibility of applying discounts based on two policies:
* amount based - the more pieces of the product are ordered, the bigger the discount is.
* percentage based. <br>
Policies are configurable.

## Assumptions
* Discounts are not additive - that is if more than one discount is applicable for a given configuration product/quantity then the most valuable discount is applied
* Amount based discount - each next product is cheaper by a given %. Example: Buy 3 products, 1st costs 100% price, 2nd costs 90%, 3rd costs 80% etc. There is a limit to be set on how many products can be discounted. After reaching the limit, next products costs 100%.

### How to run
./mvnw clean install<br>
docker-compose up

### Postman collection
There is a postman collection attached ("shop.postman_collection.json") which can be imported to postman directly. 

### Data Loader
There are some items loaded at the start:
#### PRODUCTS
* apple with UUID: 00000000-0000-0000-0000-000000000001
* pear with UUID: 00000000-0000-0000-0000-000000000002
* orange with UUID: 00000000-0000-0000-0000-000000000003
* table with UUID: 00000000-0000-0000-0000-000000000004
* tv with UUID: 00000000-0000-0000-0000-000000000005
* keyboard with UUID: 00000000-0000-0000-0000-000000000006
* window with UUID: 00000000-0000-0000-0000-000000000007
* wallet with UUID: 00000000-0000-0000-0000-000000000008
* mug with UUID: 00000000-0000-0000-0000-000000000009
* plate with UUID: 00000000-0000-0000-0000-000000000010
#### DISCOUNTS
* Discount 10% on pears (pear id: "00000000-0000-0000-0000-000000000002")
