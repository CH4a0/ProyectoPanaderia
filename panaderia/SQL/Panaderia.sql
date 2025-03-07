-- Tabla categoria (padre)
create table categoria (
    id int primary key,
    tipo varchar(50) unique,  -- <- ahora es UNIQUE
    descripcion varchar(100)
);


-- Tabla productos (hija)
create table productos (
    id int primary key,
    nombre varchar(50),
    categoria VARCHAR(50),
    descripcion varchar(100),
    precio decimal(10,2) not null,
    foreign key (categoria) references categoria(tipo)
);
INSERT INTO categoria (id, tipo, descripcion) VALUES
(1, 'Dulce', 'Productos de sabor dulce'),
(2, 'Salado', 'Productos de sabor salado'),
(3, 'Mixto', 'Productos que combinan dulce y salado'),
(4, 'Pan', 'Todo tipo de panes');


 -- Productos Dulces (20)
INSERT INTO productos (id, nombre, categoria, descripcion, precio) VALUES
(1, 'Concha', 'Dulce', 'Pan dulce con costra de azúcar', 10.00),
(2, 'Oreja', 'Dulce', 'Hojaldre dulce con azúcar', 8.50),
(3, 'Cuernito de Chocolate', 'Dulce', 'Croissant relleno de chocolate', 12.00),
(4, 'Galleta de Avena', 'Dulce', 'Galleta dulce con avena', 6.00),
(5, 'Pan de Muerto', 'Dulce', 'Pan dulce tradicional', 15.00),
(6, 'Dona Glaseada', 'Dulce', 'Dona cubierta de azúcar glaseada', 12.00),
(7, 'Empanada de Cajeta', 'Dulce', 'Empanada rellena de cajeta', 14.00),
(8, 'Churro', 'Dulce', 'Churro espolvoreado con azúcar y canela', 7.00),
(9, 'Rosca de Reyes', 'Dulce', 'Rosca dulce tradicional', 150.00),
(10, 'Tartaleta de Frutas', 'Dulce', 'Base de galleta con crema pastelera y frutas', 30.00),
(11, 'Panqué de Nuez', 'Dulce', 'Panqué dulce con nuez', 25.00),
(12, 'Pay de Manzana', 'Dulce', 'Pay con relleno de manzana', 28.00),
(13, 'Beso Azucarado', 'Dulce', 'Pan relleno de mermelada', 9.00),
(14, 'Cocol', 'Dulce', 'Pan con anís y piloncillo', 10.00),
(15, 'Cuernito de Azúcar', 'Dulce', 'Croissant con azúcar espolvoreada', 11.00),
(16, 'Pastelito Individual', 'Dulce', 'Mini pastel de vainilla', 15.00),
(17, 'Galleta Chispas', 'Dulce', 'Galleta con chispas de chocolate', 6.50),
(18, 'Mantecadas', 'Dulce', 'Pan suave y esponjoso', 10.00),
(19, 'Volován de Crema', 'Dulce', 'Hojaldre relleno de crema pastelera', 12.00),
(20, 'Brazo Gitano', 'Dulce', 'Bizcocho relleno de crema', 35.00);

-- Productos Salados (20)
INSERT INTO productos (id, nombre, categoria, descripcion, precio) VALUES
(21, 'Baguette de Jamón', 'Salado', 'Baguette con jamón y queso', 35.00),
(22, 'Panini de Pollo', 'Salado', 'Panini relleno de pollo y queso', 45.00),
(23, 'Cuernito de Jamón y Queso', 'Salado', 'Croissant relleno', 30.00),
(24, 'Empanada de Atún', 'Salado', 'Empanada rellena de atún', 18.00),
(25, 'Bolillo con Chorizo', 'Salado', 'Bolillo relleno', 22.00),
(26, 'Tarta de Espinacas', 'Salado', 'Tarta salada', 28.00),
(27, 'Pizza Individual', 'Salado', 'Mini pizza artesanal', 40.00),
(28, 'Pan de Ajo', 'Salado', 'Pan saborizado con ajo y mantequilla', 15.00),
(29, 'Volován de Jamón', 'Salado', 'Hojaldre relleno de jamón', 20.00),
(30, 'Galleta de Queso', 'Salado', 'Galleta salada de queso', 12.00),
(31, 'Bollo Relleno de Queso', 'Salado', 'Pan relleno de queso', 18.00),
(32, 'Mollete', 'Salado', 'Bolillo con frijoles y queso gratinado', 25.00),
(33, 'Cuernito de Salami', 'Salado', 'Croissant relleno de salami', 32.00),
(34, 'Rollito de Salchicha', 'Salado', 'Pan relleno de salchicha', 20.00),
(35, 'Focaccia', 'Salado', 'Pan plano con hierbas y aceite', 28.00),
(36, 'Pambazo', 'Salado', 'Pan relleno de papa y chorizo', 30.00),
(37, 'Tartaleta de Queso', 'Salado', 'Tartaleta salada', 27.00),
(38, 'Empanada de Espinaca', 'Salado', 'Empanada rellena', 17.00),
(39, 'Crostini', 'Salado', 'Pan tostado con topping', 14.00),
(40, 'Brioche Salado', 'Salado', 'Brioche relleno de queso', 30.00);

-- Productos Mixtos (5)
INSERT INTO productos (id, nombre, categoria, descripcion, precio) VALUES
(41, 'Pretzel con Chocolate', 'Mixto', 'Pretzel salado cubierto de chocolate', 15.00),
(42, 'Panini con Mermelada y Queso', 'Mixto', 'Panini relleno dulce y salado', 38.00),
(43, 'Empanada de Queso con Cajeta', 'Mixto', 'Empanada con mezcla dulce y salada', 22.00),
(44, 'Baguette de Jamón y Miel', 'Mixto', 'Combinación de jamón y miel', 42.00),
(45, 'Cuernito de Queso Crema y Fresas', 'Mixto', 'Croissant relleno con mezcla dulce y salada', 35.00);

-- Productos de Pan (9)
INSERT INTO productos (id, nombre, categoria, descripcion, precio) VALUES
 (46,'Barra pan','Pan', 'Barra de pan moderna', 1.50),
 (47,'Pan integral','Pan', 'Pan integral de trigo', 2.00),
 (48,'Barra de pan tradicional','Pan','Barra de pan al estilo tradicional(solo forma)', 1.50),
 (49,'Pan de molde','Pan','Pan de molde de trigo', 1.50),
 (50,'Pan de molde integral','Pan','Pan de molde integral de trigo', 2.00),
 (51,'Pan de picos','Pan','Pan de picos extra crujiente y con un toque de sal',1.50),
 (52,'Pan especial la casa','Pan','Pan especial que solo hace nuestra panaderia',1.20),
 (53, 'Pan de espelta', 'Pan', 'Pan de espelta hecho al 100% con harina de espelta', 2.50),
 (54, 'Pan con nueces', 'Pan', 'Pan integral con un añadido de nueces', 2.00),
 (55, 'Pan de molde de centeno', 'Pan', 'Pan de molde de centeno', 1.50);

 




