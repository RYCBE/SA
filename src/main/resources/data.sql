INSERT INTO TBL_PRODUCTS (prd_category, prd_name, prd_price, prd_image)
SELECT * FROM CSVREAD('classpath:/data.csv', NULL, 'charset=UTF-8');