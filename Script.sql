create schema escola;

create table escola.professor (
	matricula SERIAL not null, 
	nome varchar(255), 
	dataNasc date,
	sexo bpchar(1),
    contato varchar(50),
    email varchar(150),
    disciplina varchar(150),
    primary key (matricula)
);
    
create table escola.curso ( // curso_id_seq
	id SERIAL not null,
	nome varchar(255), 
	descricao TEXT, 
	cargahoraria int, 
	qtdalunos int, 
	datainicio date, 
	numsala int,
	professor int,
	primary key (id),
	foreign key (professor) references escola.professor(matricula)
);	
	
create table escola.aluno (
	matricula SERIAL not null, 
	nome varchar(255), 
	dataNasc date,
	sexo bpchar(1),
    contato varchar(50),
    email varchar(150),
    primary key (matricula)
);

create table escola.turma ( // turma_id_seq
	id SERIAL not null,
	nome varchar(255), 
	cargahoraria int, 
	professor int,
	aluno int,
	curso int,
	numsala int,
	datainicio date,
	datatermino date,
	primary key (id),
	foreign key (professor) references escola.professor(matricula),
	foreign key (aluno) references escola.aluno(matricula),
	foreign key (curso) references escola.curso(id)
);

