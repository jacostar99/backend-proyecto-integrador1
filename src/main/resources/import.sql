INSERT INTO regiones (id,nombre) VALUES (1,"Medellin")
INSERT INTO regiones (id,nombre) VALUES (2,"Cali")
INSERT INTO regiones (id,nombre) VALUES (3,"Cartagena")
INSERT INTO regiones (id,nombre) VALUES (4,"Bogota")



INSERT INTO operarios (username,password,region_id,nombre,apellido,estado,fecha_Creacion,hora_Fin_Turno) VALUES ('cristian','$2a$10$5OMzyYNqewnGhmHN8m01sOv8MmIGSIN/j52w.YSn5pFLMdgAOUlEe',1, 'Cristian','Suarez','08:00:00','2020-03-25','16:00:00'); 
INSERT INTO operarios (username,password,region_id,nombre,apellido,estado,fecha_Creacion,hora_Fin_Turno) VALUES ('hectorm','$2a$10$ty0HxpujvctwANSWXCjRUOH1aLO6fRKwXZ1UKOthwHMiZJivEgDGK',2,'Hector','Morales','08:00:00','2010-04-22','16:00:00'); 
INSERT INTO operarios (username,password,region_id,nombre,apellido,estado,fecha_Creacion,hora_Fin_Turno) VALUES ('davido','$2a$10$PhQpR1putzC16eHbQa/.LOaM6GS7HgYnfKovMX5rXuYukb.ZI/LFW',3, 'David','Ocampo','12:00:00','2019-05-12','16:00:00'); 
INSERT INTO operarios (username,password,region_id,nombre,apellido,estado,fecha_Creacion,hora_Fin_Turno) VALUES ('diegor','$2a$10$ty0HxpujvctwANSWXCjRUOH1aLO6fRKwXZ1UKOthwHMiZJivEgDGK',4, 'Diego','Restrepo','08:00:00','2018-06-13','16:00:00'); 
INSERT INTO operarios (username,password,region_id,nombre,apellido,estado,fecha_Creacion,hora_Fin_Turno) VALUES ('javierc','$2a$10$YcW822G9NpgzBZ9q/MDmjO0bZbpVyZY.fdhnS9q3atBeGB0BPJ7J2',2, 'Javier','Ceballos','16:00:00','2020-07-15','08:00:00'); 
INSERT INTO operarios (username,password,region_id,nombre,apellido,estado,fecha_Creacion,hora_Fin_Turno) VALUES ('fernandom','$2a$10$4SMdjsNSCWHo7R8BFhgjke/dwsR0qO4bQGHZqzybQ/OOaxKcBuZ7G',1, 'Fernando','Martinez','12:00:00','2016-11-09','16:00:00'); 
 
	
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Amortiguador',380000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Filtro de aire',24000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Neumaticos',534000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Actuadores de frenos',650000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Lubricante', 90000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Catalizador',240000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Iluminacion',45000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Destornillador',12000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Aceites',24000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Amoladora',34000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Bomba de engrase',31000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Cargador de baterias',20000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Compresor de aire',20000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Limpiador general',18000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Caja de tornillos',22000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Mostrador',620000,NOW());
INSERT INTO insumos (nombre,precio,create_at) VALUES ('Vidrio',45000,NOW());








INSERT INTO mantenimientos(descripcion,direccion,fecha_asignada,hora_aproximada_duracion,hora_inicio,prioridad,tipo,operario_id,region_id) VALUES('Mantenimiento a maquinas','Calle 9#34-34',NOW(),'2 horas','12:00:00','Urgente','Correctivo',1,1);
INSERT INTO mantenimientosc(descripcion,empresa,direccion,fecha_asignada,hora_aproximada_duracion,hora_inicio,prioridad,tipo,regionc_id) VALUES('Mantenimiento a camion', 'Empresa de limpieza Limpia Brisas S.A.S' ,NOW(),NOW(),'12:00:00',NOW(),'Urgente camion','Correctivo camion',1);

INSERT INTO mantenimientos_items(cantidad,mantenimiento_id,insumo_id) VALUES (1,1,1);
INSERT INTO mantenimientos_items(cantidad,mantenimiento_id,insumo_id) VALUES (2,1,4);
INSERT INTO mantenimientos_items(cantidad,mantenimiento_id,insumo_id) VALUES (1,1,1);
INSERT INTO mantenimientos_items(cantidad,mantenimiento_id,insumo_id) VALUES (1,1,1);

INSERT INTO mantenimientos_items(cantidad,mantenimientoc_id,insumo_id) VALUES (5,1,4);
INSERT INTO mantenimientos_items(cantidad,mantenimientoc_id,insumo_id) VALUES (1,1,1);
INSERT INTO mantenimientos_items(cantidad,mantenimientoc_id,insumo_id) VALUES (1,1,1);


INSERT INTO reportes (ciudad,asunto,descripcion,fecha_de_reporte,prioridad,sucursal) VALUES('Cali','Daño en planta','Planta dañada' ,NOW() ,'Urgente','Ciasto company Unicentro')



INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');


INSERT INTO operarios_roles (operario_id,role_id) VALUES (1,2);
INSERT INTO operarios_roles (operario_id,role_id) VALUES (2,1);
INSERT INTO operarios_roles (operario_id,role_id) VALUES (3,1);
INSERT INTO operarios_roles (operario_id,role_id) VALUES (4,1);
INSERT INTO operarios_roles (operario_id,role_id) VALUES (5,1);
INSERT INTO operarios_roles (operario_id,role_id) VALUES (6,1);











