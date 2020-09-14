BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS "cursos" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"curso"	VARCHAR(100) NOT NULL DEFAULT 'N/A',
	"identificador"	VARCHAR(10) NOT NULL DEFAULT 'N/A',
	"horas"	INTEGER NOT NULL DEFAULT 0,
	"idProfesor"	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS "profesores" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"nombre"	VARCHAR(50) NOT NULL,
	"apellidos"	VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS "alumnos" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"nombre"	VARCHAR(50) NOT NULL DEFAULT 'N/A',
	"apellidos"	VARCHAR(100) NOT NULL DEFAULT 'N/A'
);
CREATE TABLE IF NOT EXISTS "alumnosCurso" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"idAlumno"	INTEGER NOT NULL,
	"idCurso"	INTEGER NOT NULL,
	FOREIGN KEY("idAlumno") REFERENCES "alumnos"("id"),
	FOREIGN KEY("idCurso") REFERENCES "cursos"("id")
);
INSERT INTO "cursos" VALUES (1,'Microsoft Office 2016','I001',50,1);
INSERT INTO "cursos" VALUES (2,'Experto en Desarrollo de Aplicaciones WEB y Bases de Datos','I002',630,2);
INSERT INTO "cursos" VALUES (3,'Desarrollo Avanzado con JAVA/JEE','I003',510,3);
INSERT INTO "profesores" VALUES (1,'Alain','Moles');
INSERT INTO "profesores" VALUES (2,'Ander','Uraga');
INSERT INTO "profesores" VALUES (3,'Pepito','Piscinas');
INSERT INTO "alumnos" VALUES (1,'Elier','Otero');
INSERT INTO "alumnos" VALUES (2,'Beatriz','Martinez');
INSERT INTO "alumnos" VALUES (3,'Asier','Mintegui');
INSERT INTO "alumnos" VALUES (4,'Lander','Bilbao');
INSERT INTO "alumnosCurso" VALUES (1,1,1);
INSERT INTO "alumnosCurso" VALUES (2,1,2);
INSERT INTO "alumnosCurso" VALUES (3,2,1);
INSERT INTO "alumnosCurso" VALUES (4,2,3);
INSERT INTO "alumnosCurso" VALUES (5,3,2);
INSERT INTO "alumnosCurso" VALUES (6,3,3);
INSERT INTO "alumnosCurso" VALUES (7,4,1);
INSERT INTO "alumnosCurso" VALUES (8,4,3);

COMMIT;
