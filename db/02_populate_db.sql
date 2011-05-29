INSERT INTO   "ROLE" VALUES ( 1, 'Администратор'	, 0)
/
INSERT INTO   "ROLE" VALUES ( 2, 'Архитектор'		, 3)
/
INSERT INTO   "ROLE" VALUES ( 3, 'Ведущий программист'	, 2)
/
INSERT INTO   "ROLE" VALUES ( 4, 'Ведущий дизайнер'	, 2)
/
INSERT INTO   "ROLE" VALUES ( 5, 'Программист'		, 1)
/
INSERT INTO   "ROLE" VALUES ( 6, 'Дизайнер'		, 1)
/
INSERT INTO   "ROLE" VALUES ( 7, 'Менеджер'		, 3)
/
INSERT INTO   "ACCOUNT" VALUES ( 1,
				 'Василий Пупкин',
				 'vasilipup',
				 '098qwerty',
				 1,
				 'vasilipup@mail.non'	)
/
INSERT INTO   "ACCOUNT" VALUES ( 2,
				 'Владимир Крамаренко',
				 'vlad_kramar',
				 'qE4Sfs4',
				 2,
				 'vlad_kramar@mail.non'	)
/
INSERT INTO   "ACCOUNT" VALUES ( 3,
				 'Николай Морозов',
				 'moroz',
				 'ydFhj3S73d',
				 7,
				 'moroz@mail.non'	)
/
INSERT INTO   "ACCOUNT" VALUES ( 4,
				 'Андрей Ермолаев',
				 'ermol',
				 'jf76hjj4Sf',
				 3,
				 'ermol@mail.non'	)
/
INSERT INTO   "ACCOUNT" VALUES ( 5,
				 'Пётр Васисуалиев',
				 'p_vasisualiev',
				 '78Djd3Dhs',
				 4,
				 'p_vasisualiev@mail.non')
/
INSERT INTO   "ACCOUNT" VALUES ( 6,
				 'Виктор Побережный',
				 'bereg',
				 'yDybebf73',
				 5,
				 'bereg@mail.non'	)
/
INSERT INTO   "ACCOUNT" VALUES ( 7,
				 'Сергей Воронов',
				 'voron',
				 'Jdsgh7r63S',
				 6,
				 'voron@mail.non'	)
/
INSERT INTO   "ACCOUNT" VALUES ( 8,
				 'Валерий Никифоров',
				 'nickifor',
				 'dj82djhfFF',
				 7,
				 'nickifor@mail.non'	)
/
INSERT INTO   "RESOURCES" VALUES ( 1, 'Кухня'			)
/
INSERT INTO   "RESOURCES" VALUES ( 2, 'Комната для митингов'	)
/
INSERT INTO   "RESOURCES" VALUES ( 3, 'Аудитория №1'		)
/
INSERT INTO   "RESOURCES" VALUES ( 4, 'Аудитория №2'		)
/
INSERT INTO   "RESOURCES" VALUES ( 5, 'Аудитория №3'		)
/
INSERT INTO   "JOURNAL" VALUES ( 1,
				 timestamp'2011-05-20 09:00:00',
				 timestamp'2011-05-20 12:00:00',
				 1,
				 2				)
/
INSERT INTO   "RES_ROLE" VALUES ( 1,	1, 1 )
/
INSERT INTO   "RES_ROLE" VALUES ( 2,	1, 2 )
/
INSERT INTO   "RES_ROLE" VALUES ( 3,	1, 3 )
/
INSERT INTO   "RES_ROLE" VALUES ( 4,	1, 4 )
/
INSERT INTO   "RES_ROLE" VALUES ( 5,	1, 5 )
/
INSERT INTO   "RES_ROLE" VALUES ( 6,	1, 6 )
/
INSERT INTO   "RES_ROLE" VALUES ( 7,	1, 7 )
/
INSERT INTO   "RES_ROLE" VALUES ( 8,	2, 2 )
/
INSERT INTO   "RES_ROLE" VALUES ( 9,	2, 3 )
/
INSERT INTO   "RES_ROLE" VALUES ( 10,	2, 4 )
/
INSERT INTO   "RES_ROLE" VALUES ( 11,	2, 7 )
/
INSERT INTO   "RES_ROLE" VALUES ( 12,	3, 2 )
/
INSERT INTO   "RES_ROLE" VALUES ( 13,	3, 3 )
/
INSERT INTO   "RES_ROLE" VALUES ( 14,	3, 4 )
/
INSERT INTO   "RES_ROLE" VALUES ( 15,	3, 7 )
/
INSERT INTO   "RES_ROLE" VALUES ( 16,	4, 2 )
/
INSERT INTO   "RES_ROLE" VALUES ( 17,	4, 3 )
/
INSERT INTO   "RES_ROLE" VALUES ( 18,	4, 4 )
/
INSERT INTO   "RES_ROLE" VALUES ( 19,	4, 5 )
/
INSERT INTO   "RES_ROLE" VALUES ( 20,	4, 6 )
/
INSERT INTO   "RES_ROLE" VALUES ( 21,	4, 7 )
/
INSERT INTO   "RES_ROLE" VALUES ( 22,	5, 2 )
/
INSERT INTO   "RES_ROLE" VALUES ( 23,	5, 3 )
/
INSERT INTO   "RES_ROLE" VALUES ( 24,	5, 4 )
/
INSERT INTO   "RES_ROLE" VALUES ( 25,	5, 5 )
/
INSERT INTO   "RES_ROLE" VALUES ( 26,	5, 6 )
/
INSERT INTO   "RES_ROLE" VALUES ( 27,	5, 7 )
/
COMMIT
/