drop table if exists api_vuxenutb.KurserVuxenutbildning;
drop schema if exists api_vuxenutb;

create schema api_vuxenutb;

create table api_vuxenutb.KurserVuxenutbildning
(
	KurserVuxenutbildning_id bigint,
	kurstillfälle_id bigint not null,
	Utbildningsanordnare varchar(4096),
	UrlAnordnare varchar(4096),
	Kurskod varchar(4096),
	Namn varchar(4096),
	Niva varchar(4096),
	UrlKurs varchar(4096),
	Poang decimal(8,1),
	RekommenderadInformation varchar(8000),
	KurstillfalleStartdatum smalldatetime,
	KurstillfalleSlutdatum smalldatetime,
	Omfattning decimal(8,1),
	ForstaAnsokningsdag datetime2,
	SistaAnsokningsdag datetime2,
	Studieort varchar(4096),
	Amneskod varchar(50),
	AntalPlatser int
);
