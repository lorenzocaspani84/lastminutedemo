
INSERT INTO category (category_id, name) VALUES
(1, 'Input1'),
(2, 'Input2'),
(3, 'Input3');

INSERT INTO goods (goods_id, duty, exempt, name, price, quantity, category_id) VALUES
(1, false, true, 'book', '12.49', '1', '1'),
(2, false, false, 'music CD', '14.99', '1', '1'),
(3, false, true, 'chocolate bar', '0.85', '1', '1');


INSERT INTO goods (goods_id, duty, exempt, name, price, quantity, category_id) VALUES
(4, true, true, 'imported box of chocolates', '10.00', '1', '2'),
(5, true, false, 'imported bottle of perfume', '47.50', '1', '2');


INSERT INTO goods (goods_id, duty, exempt, name, price, quantity, category_id) VALUES
(6, true, false, 'imported bottle of perfume', '27.99', '1', '3'),
(7, false, false, 'bottle of perfume', '18.99', '1', '3') ,
(8, false, true, 'packet of headache pills', '9.75', '1', '3'),
(9, true, true, 'imported box of chocolates', '11.25', '1', '3');