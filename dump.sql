--------------------------------------------------------
--  DDL for Table PROCESSO
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."PROCESSO" 
   (	"NUMERO" VARCHAR2(24 CHAR), 
	"DATA_CADASTRO" TIMESTAMP (6), 
	"QUANTIDADE_PARTES" NUMBER(10,0), 
	"SEGREDO_JUSTICA" NUMBER(1,0), 
	"SITUACAO" VARCHAR2(255 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Procedure GETRELATORIOPROCESSOS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SYSTEM"."GETRELATORIOPROCESSOS" 
(p_publicos OUT NUMBER,
 p_privados OUT NUMBER,
 p_em_andamento OUT NUMBER,
 p_julgados OUT NUMBER,
 p_arquivados OUT NUMBER)
AS
BEGIN
    SELECT count(segredo_justica) as publicos
    INTO p_publicos
    FROM processo WHERE segredo_justica = 0;

    SELECT count(segredo_justica) as privados
    INTO p_privados
    FROM processo WHERE segredo_justica = 1;

    SELECT count(situacao) as em_andamento
    INTO p_em_andamento
    FROM processo WHERE situacao = 'EM_ANDAMENTO';

    SELECT count(situacao) as julgados 
    INTO p_julgados
    FROM processo WHERE situacao = 'JULGADO';

    SELECT count(situacao) as arquivados 
    INTO p_arquivados
    FROM processo WHERE situacao = 'ARQUIVADO';

END getRelatorioProcessos;

/
--------------------------------------------------------
--  Constraints for Table PROCESSO
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PROCESSO" ADD PRIMARY KEY ("NUMERO") ENABLE;
  ALTER TABLE "SYSTEM"."PROCESSO" MODIFY ("SEGREDO_JUSTICA" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."PROCESSO" MODIFY ("QUANTIDADE_PARTES" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."PROCESSO" MODIFY ("NUMERO" NOT NULL ENABLE);
