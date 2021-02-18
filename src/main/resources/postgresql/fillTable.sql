insert into test(test_name, geog)
select n as test_name, ST_SetSRID( ST_Point(random() * 10, random() * 10), 4326)::geography as geog
from unnest(ARRAY['test0',
    'test1',
    'test2',
    'test3',
    'test4',
    'test5',
    'test6',
    'test7',
    'test8',
    'test9']) n
on conflict do nothing;
