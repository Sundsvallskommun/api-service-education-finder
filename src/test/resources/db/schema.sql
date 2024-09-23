drop table if exists api_vuxenutb.KurserVuxenutbildning;
drop table if exists api_vuxenutb.vAmnessok;
drop schema if exists api_vuxenutb;

create schema api_vuxenutb;

create table api_vuxenutb.KurserVuxenutbildning
(
    KurserVuxenutbildningId bigint,
	kurstillfalleId bigint not null,
	Utbildningsanordnare varchar(4096) COLLATE Finnish_Swedish_CI_AS,
	UrlAnordnare varchar(4096) COLLATE Finnish_Swedish_CI_AS,
	Kurskod varchar(4096) COLLATE Finnish_Swedish_CI_AS,
	Namn varchar(4096) COLLATE Finnish_Swedish_CI_AS,
	Niva varchar(4096) COLLATE Finnish_Swedish_CI_AS,
	UrlKurs varchar(4096) COLLATE Finnish_Swedish_CI_AS,
	Poang decimal(8,1),
	RekommenderadInformation varchar(8000) COLLATE Finnish_Swedish_CI_AS,
	KurstillfalleStartdatum smalldatetime,
	KurstillfalleSlutdatum smalldatetime,
	Omfattning decimal(8,1),
	ForstaAnsokningsdag datetime2,
	SistaAnsokningsdag datetime2,
	Studieort varchar(4096) COLLATE Finnish_Swedish_CI_AS,
	Amneskod varchar(50) COLLATE Finnish_Swedish_CI_AS,
	AntalPlatser int
);

create table api_vuxenutb.vAmnessok
(
    AmnessokID bigint not null,
    AmnesId int not null,
    Amne varchar(4096) COLLATE Finnish_Swedish_CI_AS,
    AmneEng varchar(4096) COLLATE Finnish_Swedish_CI_AS,
    Amneskyp varchar(4096) COLLATE Finnish_Swedish_CI_AS,
    Amneskod varchar(4096) COLLATE Finnish_Swedish_CI_AS,
    OmradeId int not null,
    Omrade varchar(4096) COLLATE Finnish_Swedish_CI_AS,
    OmradeEng varchar(4096) COLLATE Finnish_Swedish_CI_AS,
    InriktningId int not null,
    Inriktning varchar(4096) COLLATE Finnish_Swedish_CI_AS,
    InriktningEng varchar(4096) COLLATE Finnish_Swedish_CI_AS
);
