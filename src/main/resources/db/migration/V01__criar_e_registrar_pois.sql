
CREATE TABLE tb_poi (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
    coordinateX BIGINT(20) NOT NULL,
    coordinateY BIGINT(20) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_poi (name, coordinateX, coordinateY) 
               values ('Lanchonete', 27, 12);

INSERT INTO tb_poi (name, coordinateX, coordinateY) 
               values ('Posto', 31, 18);

INSERT INTO tb_poi (name, coordinateX, coordinateY) 
               values ('Joalheria', 15, 12);

INSERT INTO tb_poi (name, coordinateX, coordinateY) 
               values ('Floricultura', 19, 21);

INSERT INTO tb_poi (name, coordinateX, coordinateY) 
               values ('Pub', 12, 8);

INSERT INTO tb_poi (name, coordinateX, coordinateY) 
               values ('Supermercado', 23, 6);

INSERT INTO tb_poi (name, coordinateX, coordinateY) 
               values ('Churrascaria', 28, 2);


