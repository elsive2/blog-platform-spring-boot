CREATE TABLE posts (
	id int8 GENERATED ALWAYS AS IDENTITY NOT NULL,
	title varchar NOT NULL,
	description varchar NOT NULL,
	"content" text NOT NULL,
	CONSTRAINT posts_pk PRIMARY KEY (id)
);
