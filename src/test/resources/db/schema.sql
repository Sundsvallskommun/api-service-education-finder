drop table if exists api_vuxenutb.KurserVuxenutbildning;
drop schema if exists api_vuxenutb;

create schema api_vuxenutb;

create table api_vuxenutb.KurserVuxenutbildning
(
	KurserVuxenutbildning_id bigint,
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
