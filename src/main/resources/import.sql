USE ProjectPOO

INSERT INTO Usuario VALUES ('admin',		   'Administrador',  'admin',		 'Desbloqueado','Ativo'   )
INSERT INTO Usuario VALUES ('PEDRO',		   'Administrador',  '123456',		 'Desbloqueado','Ativo'   )
INSERT INTO Usuario VALUES ('NICOLLY',		   'Administrador',  '12221',		 'Desbloqueado','Ativo'   )   
INSERT INTO Usuario VALUES ('JOAO',			   'Administrador',  '654321',		 'Desbloqueado','Inativo')   
INSERT INTO Usuario VALUES ('Leonardo',		   'Administrador',  '232323',		 'Desbloqueado','Inativo')   
INSERT INTO Usuario VALUES ('Marcos',		   'Regular',		 '100000',		 'Bloqueado'   ,'Ativo'   )   
INSERT INTO Usuario VALUES ('Rhavel',		   'Regular',		 'EuSouOCara',   'Desbloqueado','Inativo')   
INSERT INTO Usuario VALUES ('UsuarioEstranho', 'Regular',		 '123456',       'Bloqueado'   ,'Inativo')   
INSERT INTO Usuario VALUES ('testeDeleta',	   'Regular',		 'EuSouOCara',   'Desbloqueado','Inativo')   
INSERT INTO Usuario VALUES ('Amanda',		   'Regular',		 '232323',       'Bloqueado'   ,'Ativo'   )
INSERT INTO Usuario VALUES ('Tadeu',		   'Administrador',  'semcabelo',    'Bloqueado'   ,'Inativo')
INSERT INTO Usuario VALUES ('Sergio',		   'Regular',		 'Hospital@13',  'Bloqueado'   ,'Ativo'   )   
INSERT INTO Usuario VALUES ('Jefferson',	   'Regular',		 'testesenha',   'Bloqueado'   ,'Inativo')
INSERT INTO Usuario VALUES ('antonioStrike',  'Administrador',  'admin',		 'Desbloqueado','Ativo'   )
-------------------------------------------------------------------------------  	  
INSERT INTO Pessoa VALUES (1,	'admin@gmail.com',				'admin',		'81996913207', 'admin')	  
INSERT INTO Pessoa VALUES (2,	'Pedro@gmail.com',				'PEDRO',		'81996913207', 'PEDRO')	  
INSERT INTO Pessoa VALUES (3,	'NICOLLY@gmail.com',			'NICOLLY',		'81990434394', 'NICOLLY')		     
INSERT INTO Pessoa VALUES (4,	'JOAO@gmail.com',				'JOAO',			'81990434394', 'JOAO')		     
INSERT INTO Pessoa VALUES (5,	'Leonardo@gmail.com',			'Leonardo',		'81990434394', 'Leonardo')		     
INSERT INTO Pessoa VALUES (6,	'Marcos@gmail.com',				'Marcos',		'81990434394', 'Marcos')		  
INSERT INTO Pessoa VALUES (7,	'Rhavel@gmail.com',				'Rhavel',		'81992107349', 'Rhavel')		     
INSERT INTO Pessoa VALUES (8,	'UsuarioEstranho@gmail.com',	'semnome',		'81992107349', 'UsuarioEstranho')
INSERT INTO Pessoa VALUES (9,	'testeDeleta@gmail.com',	    'seila',		'81992107349', 'testeDeleta')	     
INSERT INTO Pessoa VALUES (10,	'Amanda@gmail.com',				'Amanda',		'81992107349', 'Amanda')		  
INSERT INTO Pessoa VALUES (11,	'Tadeu@gmail.com',				'Tadeu',		'81992107349', 'Tadeu')		  
INSERT INTO Pessoa VALUES (12,	'Sergio@gmail.com',				'Sergio',		'81992107349', 'Sergio')		  
INSERT INTO Pessoa VALUES (13,	'Jefferson@gmail.com',			'Jefferson',	'81992107349', 'Jefferson')
INSERT INTO Pessoa VALUES (14,	'antonio.strike@vingadores.com','admin',		'8196912323' , 'antonioStrike')	
------------------------------------------------------------------------------- 
UPDATE dbo.hibernate_sequence SET next_val = 15