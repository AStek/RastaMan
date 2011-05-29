select to_char(begin_time, 'YYYY-MM-DD') as VC2_DATE from journal
/
select to_date(to_char(begin_time, 'YYYY-MM-DD'), 'YYYY-MM-DD') as D_DATE from journal
/
select to_char(begin_time, 'HH24:MI:SS') as VC2_TIME from journal
/