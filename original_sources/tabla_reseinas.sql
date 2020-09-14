CREATE TABLE IF NOT EXISTS "reseinas" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"reseina"	Text,
	"idAlumno"	INTEGER NOT NULL,
	"idCurso"	INTEGER NOT NULL,
	FOREIGN KEY("idCurso") REFERENCES "cursos"("id"),
	FOREIGN KEY("idAlumno") REFERENCES "alumnos"("id")
);