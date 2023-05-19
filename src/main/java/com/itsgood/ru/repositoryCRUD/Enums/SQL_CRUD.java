package com.itsgood.ru.repositoryCRUD.Enums;

public enum SQL_CRUD {

//CUSTOMER
//+-------------+------------------+------+-----+---------+----------------+
//| Field       | Type             | Null | Key | Default | Extra          |
//+-------------+------------------+------+-----+---------+----------------+
//| id          | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
//| firstname   | char(30)         | NO   |     | NULL    |                |
//| lastname    | char(30)         | NO   |     | NULL    |                |
//| username    | char(30)         | NO   | PRI | NULL    |                |
//| mail        | char(30)         | NO   | PRI | NULL    |                |
//| password    | char(30)         | NO   | PRI | NULL    |                |
//| phone       | bigint(16)       | NO   | PRI | NULL    |                |
//| birthday    | date             | NO   |     | NULL    |                |
//| gender      | char(10)         | NO   |     | NULL    |                |
//| create_time | datetime         | NO   |     | NULL    |                |
//| update_time | datetime         | YES  |     | NULL    |                |
//+-------------+------------------+------+-----+---------+----------------+
    INSERT_CUSTOMER("INSERT INTO Customer (firstname, lastname, username, mail, password, phone, birthday, " +
            "gender, create_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"),
    UPDATE_CUSTOMER_ON_ID("UPDATE Customer SET firstname = ?, lastname = ?, username = ?, mail = ?, " +
            "password = ?, phone = ?, birthday = ?, gender = ?, update_time = ? WHERE id = ?;"),
    DELETE_CUSTOMER_ON_ID("DELETE FROM Customer WHERE id = ?;"),
    SELECT_ALL_CUSTOMER("SELECT * FROM Customer;"),
    SELECT_CUSTOMER_ON_ID("SELECT * FROM Customer WHERE id = ?;"),
    SELECT_CUSTOMER_ON_USERNAME_MAIL("SELECT * FROM Customer WHERE username = ? AND mail = ?;"),
    SELECT_CUSTOMER_ON_USERNAME("SELECT * FROM Customer WHERE username = ?;"),
    SELECT_CUSTOMER_ON_MAIL("SELECT * FROM Customer WHERE mail = ?;"),
    SELECT_CUSTOMER_ON_MAX_ID("SELECT * FROM Customer WHERE id = (SELECT MAX(id) FROM Customer);"),
    SELECT_CUSTOMER_ON_MIN_ID("SELECT * FROM Customer WHERE id = (SELECT MIN(id) FROM Customer);"),

//ADDRESS
//+-------------+-------------------+------+-----+---------+----------------+
//| Field       | Type              | Null | Key | Default | Extra          |
//+-------------+-------------------+------+-----+---------+----------------+
//| id          | int(10) unsigned  | NO   | PRI | NULL    | auto_increment |
//| customer_id | int(10) unsigned  | NO   | PRI | NULL    |                |
//| code        | enum('REG','DEL') | NO   | PRI | NULL    |                |
//| country     | char(30)          | NO   |     | NULL    |                |
//| region      | char(30)          | NO   |     |         |                |
//| city        | char(30)          | NO   |     | NULL    |                |
//| street      | char(30)          | NO   |     | NULL    |                |
//| house       | int(10)           | NO   |     | NULL    |                |
//| frame       | char(30)          | NO   |     |         |                |
//| apartment   | int(10)           | YES  |     | NULL    |                |
//+-------------+-------------------+------+-----+---------+----------------+
    INSERT_ADDRESS("INSERT INTO Address (customer_id, code, country, region, city, street, house, frame, apartment) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"),
    INSERT_ADDRESS_ON_USERNAME_MAIL("INSERT INTO Address (customer_id, code, country, region, city, street, house, frame, apartment) " +
            "VALUES ((SELECT id FROM Customer WHERE username = ? AND mail = ?), ?, ?, ?, ?, ?, ?, ?, ? );"),
    SELECT_ADDRESS_REG_ON_USERNAME_MAIL("SELECT * FROM Address WHERE customer_id = (SELECT id FROM Customer WHERE username = ? AND mail = ?) AND code = 'Reg';"),
    SELECT_ADDRESS_DEL_ON_USERNAME_MAIL("SELECT * FROM Address WHERE customer_id = (SELECT id FROM Customer WHERE username = ? AND mail = ?) AND code = 'Del';"),
    SELECT_ADDRESS_ON_MAX_ID("SELECT * FROM Address WHERE id = (SELECT MAX(id) FROM Address);"),
    SELECT_ADDRESS_ON_MIN_ID("SELECT * FROM Address WHERE id = (SELECT MIN(id) FROM Address);"),
    SELECT_ALL_ADDRESS("SELECT * FROM Address;"),
    SELECT_ALL_ADDRESS_ON_USERNAME_MAIL("SELECT * FROM Address WHERE customer_id = (SELECT id FROM Customer WHERE username = ? AND mail = ?);"),
    SELECT_ADDRESS_ON_ID("SELECT * FROM Address WHERE address_id = ?;"),
    UPDATE_ADDRESS_ON_ID("UPDATE Address SET country = ?, region = ?, city = ?, street = ?, house = ?, frame = ?, apartment = ? WHERE id = ?;"),
    DELETE_ADDRESS_ON_ID("DELETE FROM Address WHERE Address_id = ?;"),

//ROLE
//+-------------+----------------------------------------------------------------------+------+-----+---------+----------------+
//| Field       | Type                                                                 | Null | Key | Default | Extra          |
//+-------------+----------------------------------------------------------------------+------+-----+---------+----------------+
//| id          | int(10) unsigned                                                     | NO   | PRI | NULL    | auto_increment |
//| role        | enum('ROLE_administrator','ROLE_moderator','ROLE_user','ROLE_guest') | NO   | PRI | NULL    |                |
//| customer_id | int(10) unsigned                                                     | NO   | PRI | NULL    |                |
//| create_time | datetime                                                             | NO   |     | NULL    |                |
//| update_time | datetime                                                             | YES  |     | NULL    |                |
//| validity    | datetime                                                             | NO   |     | NULL    |                |
//+-------------+----------------------------------------------------------------------+------+-----+---------+----------------+

    INSERT_ROLE("INSERT INTO Role (role, customer_id, create_time, validity) VALUES (?, ?, ?, ?);"),
    INSERT_ROLE_ON_USERNAME_MAIL("INSERT INTO Role (role, customer_id, create_time, validity) VALUES(?, (SELECT id FROM Customer WHERE username = ? AND mail = ?), ?, ?);"),
    SELECT_ALL_ROLE("SELECT * FROM Role;"),
    SELECT_LIST_ROLE_ON_CUSTOMER_ID("SELECT * FROM Role WHERE customer_id = ?;"),
    SELECT_ROLE_ON_ID("SELECT * FROM Role WHERE id = ?;"),
    SELECT_ROLE_ON_MAX_ID("SELECT * FROM Role WHERE id = (SELECT MAX(id) FROM Role);"),
    SELECT_ROLE_ON_MIN_ID("SELECT * FROM Payment WHERE id = (SELECT MIN(id) FROM Role);"),
    DELETE_ROLE_ON_ID("DELETE FROM Role WHERE id = ?;"),
    UPDATE_ROLE_VALIDITY_ON_ID("UPDATE Role SET role = ?, validity = ?, update_time = ? WHERE id = ?;"),


//PAYMENT
//+-------------+-------------------+------+-----+---------+----------------+
//| Field       | Type              | Null | Key | Default | Extra          |
//+-------------+-------------------+------+-----+---------+----------------+
//| id          | int(10) unsigned  | NO   | PRI | NULL    | auto_increment |
//| status      | enum('ACT','PAS') | NO   |     | NULL    |                |
//| customer_id | int(10) unsigned  | NO   | PRI | NULL    |                |
//| phone       | bigint(16)        | NO   | PRI | NULL    |                |
//| card        | bigint(16)        | NO   | PRI | NULL    |                |
//| validity    | datetime          | NO   |     | NULL    |                |
//+-------------+-------------------+------+-----+---------+----------------+
    INSERT_PAYMENT("INSERT INTO Payment (phone, status, customer_id, card, validity) VALUES (?, ?, ?, ?, ?);"),
    INSERT_PAYMENT_ON_USERNAME_MAIL("INSERT INTO Payment (phone, status, customer_id, card, validity) VALUES(?, ?, (SELECT id FROM Customer WHERE username = ? AND mail = ?), ?, ?);"),
    SELECT_ALL_PAYMENT("SELECT * FROM Payment;"),
    SELECT_LIST_PAYMENT_ON_USERNAME_MAIL("SELECT * FROM Payment WHERE customer_id = (SELECT id FROM Customer WHERE username = ? AND mail = ?);"),
    SELECT_PAYMENT_ON_ID("SELECT * FROM Payment WHERE id = ?;"),
    SELECT_ID_PAYMENT_ON_ACT_USERNAME_MAIL("SELECT id FROM Payment WHERE status = 'ACT' AND customer_id = (SELECT id FROM Customer WHERE username = ? AND mail = ?);"),
    SELECT_PAYMENT_ON_MAX_ID("SELECT * FROM Payment WHERE id = (SELECT MAX(id) FROM Payment);"),
    SELECT_PAYMENT_ON_MIN_ID("SELECT * FROM Payment WHERE id = (SELECT MIN(id) FROM Payment);"),
    DELETE_PAYMENT_ON_ID("DELETE FROM Payment WHERE id = ?;"),
    UPDATE_PAYMENT_PHONE_LASTNAME_ON_ID("UPDATE Payment SET status = ?, phone = ?, card = ?, validity = ? WHERE id = ?;"),

//CONTRACT
//+---------------+---------------------+------+-----+---------+----------------+
//| Field         | Type                | Null | Key | Default | Extra          |
//+---------------+---------------------+------+-----+---------+----------------+
//| id            | int(10) unsigned    | NO   | PRI | NULL    | auto_increment |
//| customer_id   | int(10) unsigned    | NO   | PRI | NULL    |                |
//| address_id    | int(10) unsigned    | NO   | PRI | NULL    |                |
//| payment_id    | int(10) unsigned    | YES  | UNI | NULL    |                |
//| sum_order     | int(10) unsigned    | YES  |     | NULL    |                |
//| payment_types | enum('cash','card') | NO   | PRI | NULL    |                |
//| create_time   | datetime            | NO   |     | NULL    |                |
//| update_time   | datetime            | YES  |     | NULL    |                |
//+---------------+---------------------+------+-----+---------+----------------+
    INSERT_CONTRACT_CARD("INSERT INTO Contract (customer_id, address_id, payment_id, payment_types, " +
            "create_time) VALUES (?, ?, ?, 'card', ?);"),
    INSERT_CONTRACT("INSERT INTO Contract (customer_id, address_id, payment_id, payment_types, " +
            "create_time) VALUES (?, ?, ?, ?, ?);"),
    INSERT_CONTRACT_CASH("INSERT INTO Contract (customer_id, address_id, payment_id, payment_types, " +
            "create_time) VALUES (?, ?, DEFAULT, 'cash', ?);"),
    UPDATE_CONTRACT_PAYMENT_TYPE_ON_ID("UPDATE Contract SET payment_types = ?, update_time = ? WHERE id = ?;"),
    DELETE_CONTRACT_ON_ID("DELETE FROM Contract WHERE id = ?;"),
    SELECT_CONTRACT_ON_ID("SELECT * FROM Contract WHERE id = ?;"),
    SELECT_CONTRACT_ON_MAX_ID("SELECT * FROM Contract WHERE id = (SELECT MAX(id) FROM Contract);"),
    SELECT_CONTRACT_ON_MIN_ID("SELECT * FROM Contract WHERE id = (SELECT MIN(id) FROM Contract);"),
    SELECT_ALL_CONTRACT_ON_USERNAME_MAIL("SELECT * FROM Contract WHERE Customer_id = (SELECT id FROM Customer WHERE username = ? AND mail = ?);"),
    SELECT_ALL_CONTRACT("SELECT * FROM Contract;"),
    SELECT_CONTRACT_ON_USERNAME_MAIL_CARD("SELECT Contract.id, Contract.customer_id, Contract.address_id, " +
            "Contract.payment_id, Contract.sum_order, Contract.payment_types, Contract.create_time, " +
            "Contract.update_time FROM Contract " +
            "INNER JOIN Customer ON Customer.id = Contract.customer_id " +
            "WHERE username = 'mr.Proper' AND mail = 'mainkuznetsov@gmail.com' AND payment_types = 'card';"),
    SELECT_CONTRACT_ON_USERNAME_MAIL_CASH("SELECT Contract.id, Contract.customer_id, Contract.address_id, " +
            "Contract.payment_id, Contract.sum_order, Contract.payment_types, Contract.create_time, " +
            "Contract.update_time FROM Contract " +
            "INNER JOIN Customer ON Customer.id = Contract.customer_id " +
            "WHERE username = ? AND mail = ? AND payment_types = 'cash';"),
    SELECT_CUSTOMER_ADDRESS_PAYMENT("SELECT Contract.id, Contract.customer_id, Contract.address_id, " +
            "Contract.payment_id, Contract.sum_order, Contract.payment_types, Contract.create_time, " +
            "Contract.update_time FROM Contract " +
            "INNER JOIN Customer ON Customer.id = Contract.customer_id " +
            "WHERE username = 'mr.Proper' AND mail = 'mainkuznetsov@gmail.com' AND payment_types = 'card';"),

//CATEGORY
//+-------------+------------------+------+-----+---------+----------------+
//| Field       | Type             | Null | Key | Default | Extra          |
//+-------------+------------------+------+-----+---------+----------------+
//| id          | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
//| title       | char(30)         | NO   | PRI | NULL    |                |
//| description | char(100)        | YES  |     | NULL    |                |
//+-------------+------------------+------+-----+---------+----------------+
    INSERT_CATEGORY("INSERT INTO Category (title, description) VALUES (?, ?);"),
    UPDATE_CATEGORY_ON_ID("UPDATE Category SET title = ?, description = ? WHERE id = ?;"),
    SELECT_ALL_CATEGORY("SELECT * FROM Category;"),
    SELECT_CATEGORY_ON_ID("SELECT * FROM Category WHERE id = ?;"),
    DELETE_CATEGORY_ON_ID("DELETE FROM Category WHERE id = ?;"),
    SELECT_CATEGORY_ON_TITLE("SELECT * FROM Category WHERE title = ?;"),
    SELECT_CATEGORY_ON_MAX_ID("SELECT * FROM Category WHERE id = (SELECT MAX(id) FROM Category);"),
    SELECT_CATEGORY_ON_MIN_ID("SELECT * FROM Category WHERE id = (SELECT MIN(id) FROM Category);"),

//ITEM
//+-------------+------------------------------+------+-----+---------+----------------+
//| Field       | Type                         | Null | Key | Default | Extra          |
//+-------------+------------------------------+------+-----+---------+----------------+
//| id          | int(10) unsigned             | NO   | PRI | NULL    | auto_increment |
//| title       | char(30)                     | NO   | PRI | NULL    |                |
//| price       | int(10)                      | NO   |     | NULL    |                |
//| firm        | char(100)                    | YES  |     | NULL    |                |
//| link        | char(255)                    | YES  | UNI | NULL    |                |
//| description | text                         | YES  |     | NULL    |                |
//| weight      | double(10,3)                 | NO   |     | NULL    |                |
//| volume      | enum('big','small','medium') | YES  |     | NULL    |                |
//| create_time | datetime                     | NO   |     | NULL    |                |
//| update_time | datetime                     | YES  |     | NULL    |                |
//| category_id | int(40) unsigned             | NO   | PRI | NULL    |                |
//+-------------+------------------------------+------+-----+---------+----------------+
    INSERT_ITEM("INSERT INTO Item (title, price, firm, link, description, weight, volume, create_time, category_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"),
    INSERT_ITEM_ON_CATEGORY_ID("INSERT INTO Item (title, price, firm, link, description, weight, volume, create_time, category_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, (SELECT id FROM Category WHERE title = ?);"),
    SELECT_ITEM_ON_ID("SELECT * FROM Item WHERE id = ?;"),
    SELECT_ALL_ITEM("SELECT * FROM Item;"),
    SELECT_ITEM_ON_MAX_ID("SELECT * FROM Item WHERE id = (SELECT MAX(id) FROM Item);"),
    SELECT_ITEM_ON_MIN_ID("SELECT * FROM Item WHERE id = (SELECT MIN(id) FROM Item);"),
    DELETE_ITEM_ON_ID("DELETE FROM Item WHERE id = ?;"),
    UPDATE_ITEM_ON_ID("UPDATE Item SET title = ?, price = ?, firm = ?, link = ?, description = ?, weight = ?, volume = ?," +
            "update_time = ?, category_id = ?;"),
    SELECT_ITEM_ON_TITLE_FIRM("SELECT * FROM Item WHERE title = ?, firm = ?;"),

//CONTRACT_ITEM
//+-------------+-------------------+------+-----+---------+----------------+
//| Field       | Type              | Null | Key | Default | Extra          |
//+-------------+-------------------+------+-----+---------+----------------+
//| id          | int(10) unsigned  | NO   | PRI | NULL    | auto_increment |
//| contract_id | int(100) unsigned | NO   | PRI | NULL    |                |
//| item_id     | int(10) unsigned  | NO   | PRI | NULL    |                |
//| quantity    | int(10) unsigned  | NO   |     | NULL    |                |
//| create_time | datetime          | NO   | PRI | NULL    |                |
//| update_time | datetime          | YES  |     | NULL    |                |
//+-------------+-------------------+------+-----+---------+----------------+
    INSERT_CONTRACT_ITEM("INSERT INTO Contract_item (contract_id, item_id, create_time) VALUES (?, ?, ?);"),
    SELECT_ALL_CONTRACT_ITEM("SELECT * FROM Contract_item;"),
    SELECT_CONTRACT_ITEM_ON_ID("SELECT * FROM Contract_item WHERE id = ?;"),
    SELECT_TITLE_PRICE_ADDRESS_ID_CONTRACT_ID_ON_USERNAME_MAIL("SELECT Item.title, Item.price, Contract.Address_id, Contract.id AS contract_id FROM Contract_Item " +
            "INNER JOIN Item ON Item.id = Contract_Item.item_id " +
            "INNER JOIN Contract ON Contract.id = contract_id " +
            "INNER JOIN Customer ON Customer.id = customer_id " +
            "WHERE username = ? AND mail = ?;"),
    SELECT_CONTRACT_ITEM_ON_CONTRACT_ID("SELECT * FROM Contract_item WHERE contract_id = ?;"),
    SELECT_CONTRACT_ITEM_ON_MAX_ID("SELECT * FROM Contract_item WHERE id = (SELECT MAX(id) FROM Contract_Item);"),
    SELECT_CONTRACT_ITEM_ON_MIN_ID("SELECT * FROM Contract_item WHERE id = (SELECT MIN(id) FROM Contract_Item);"),
    SELECT_CONTRACT_ITEM_FIELD_ON_USERNAME_MAIL("SELECT Contract_Item.id, Contract_Item.contract_id, Contract_Item.item_id, Contract_Item.create_time, Contract_Item.update_time " +
            "FROM Contract_Item " +
            "INNER JOIN Item ON Item.id = Contract_Item.item_id " +
            "INNER JOIN Contract ON Contract.id = contract_id " +
            "INNER JOIN Customer ON Customer.id = customer_id WHERE username = ? AND mail = ?;"),
    SELECT_ID_CONTRACT_ITEM_ON_USERNAME_MAIL_CARD("SELECT Item.id AS item_id, Contract.id AS contract_id FROM Contract_Item" +
        "INNER JOIN Item ON Item.id = Contract_Item.item_id" +
        "INNER JOIN Contract ON Contract.id = contract_id" +
        "INNER JOIN Customer ON Customer.id = customer_id" +
        "WHERE payment_types = 'card' AND username = ? AND mail = ?;"),
    SELECT_ID_CONTRACT_ITEM_ON_USERNAME_MAIL_CASH("SELECT Item.id AS item_id, Contract.id AS contract_id FROM Contract_Item" +
    "INNER JOIN Item ON Item.id = Contract_Item.item_id" +
    "INNER JOIN Contract ON Contract.id = contract_id" +
    "INNER JOIN Customer ON Customer.id = customer_id" +
    "WHERE payment_types = 'cash' AND username = ? AND mail = ?;"),
    SELECT_CONTRACT_ID_CONTRACT_ITEM_ON_USERNAME_MAIL("SELECT contract_id FROM Contract_Item " +
            "INNER JOIN Contract ON Contract.id = contract_id " +
            "INNER JOIN Customer ON Customer.id = customer_id " +
            "WHERE username = ? AND mail = ?;"),
    DELETE_CONTRACT_ITEM_ON_ID("DELETE FROM Contract_item WHERE id = ?;"),
    UPDATE_CONTRACT_ITEM_ON_ID("UPDATE Contract_item SET contract_id = ?, item_id = ?, update_time = ? WHERE id = ?;"),

//DELIVERY
//+------------------+------------------------------------------+------+-----+---------+----------------+
//| Field            | Type                                     | Null | Key | Default | Extra          |
//+------------------+------------------------------------------+------+-----+---------+----------------+
//| id               | int(10) unsigned                         | NO   | PRI | NULL    | auto_increment |
//| contract_Item_id | int(10) unsigned                         | NO   | PRI | NULL    |                |
//| address_id       | int(10) unsigned                         | NO   | PRI | NULL    |                |
//| firstname        | char(30)                                 | NO   | PRI | NULL    |                |
//| lastname         | char(30)                                 | NO   | PRI | NULL    |                |
//| phone            | bigint(16)                               | NO   | PRI | NULL    |                |
//| loading_time     | datetime                                 | NO   |     | NULL    |                |
//| shipment_time    | datetime                                 | NO   |     | NULL    |                |
//| stock_index      | bigint(16) unsigned                      | NO   | PRI | NULL    |                |
//| distance         | enum('pickup','city','postal','country') | NO   |     | NULL    |                |
//| price            | int(10) unsigned                         | NO   |     | NULL    |                |
//| validity         | datetime                                 | NO   |     | NULL    |                |
//+------------------+------------------------------------------+------+-----+---------+----------------+

    INSERT_DELIVERY("INSERT INTO Delivery (contract_Item_id, address_id, firstname, lastname, phone, loading_time," +
                            " stock_index, distance, price, validity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"),
    SELECT_DELIVERY_ON_ID("SELECT * FROM Delivery WHERE id = ?;"),
    SELECT_ALL_DELIVERY("SELECT * FROM Delivery;"),
    SELECT_DELIVERY_ON_MAX_ID("SELECT * FROM Delivery WHERE id = (SELECT MAX(id) FROM Delivery);"),
    SELECT_DELIVERY_ON_MIN_ID("SELECT * FROM Delivery WHERE id = (SELECT MIN(id) FROM Delivery);"),
    SELECT_ID_CONTRACT_ITEM_ADDRESS_ON_USERNAME_MAIL("SELECT Contract_Item.id, Contract.Address_id FROM Contract_Item " +
                                                                  "INNER JOIN Contract ON Contract.id = contract_id " +
                                                                  "INNER JOIN Customer ON Customer.id = customer_id " +
                                                                  "WHERE username = ? AND mail = ?;"),
    //нужен адрес доставки а в contract указан адрес регистрации нужно delivery соеденить с contract_id и доставать адрес по id и Del, через контракт доставать список товаров через
    //линковочную таблицу
    UPDATE_DELIVERY_ON_ID("UPDATE Delivery SET contract_Item_id = ?, address_id = ?, firstname = ?, lastname = ?, phone = ?, shipment_time = ?, stock_index = ?, distance = ?, price = ?, validity = ? WHERE id = ?;"),
    DELETE_DELIVERY_ON_ID("DELETE FROM Delivery WHERE id = ?;");

    private String CRUD;

    SQL_CRUD(String CRUD) {
        this.CRUD = CRUD;
    }

    public String getCRUD() {
        return CRUD;
    }
}
