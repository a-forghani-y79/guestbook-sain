create index IX_57276527 on GB_GuestBook (groupId);
create index IX_12E2CE17 on GB_GuestBook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_3E25EE59 on GB_GuestBook (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2D55F2DD on GB_GuestBookEntry (groupId, guestbookId);
create index IX_A229CFCF on GB_GuestBookEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2031FE11 on GB_GuestBookEntry (uuid_[$COLUMN_LENGTH:75$], groupId);