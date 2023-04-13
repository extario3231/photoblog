insert into blog_user (USERNAME, PASSWORD, PHONE_NUMBER, EMAIL, DESCRIPTION, USER_ROLES)
    select 'chris', 'chris', '75639562', 'chris@hkmu.edu.hk', 'I am Chris', array['USER']
    where not exists(select 1 from blog_user where id = 1);

insert into blog_user (USERNAME, PASSWORD, PHONE_NUMBER, EMAIL, DESCRIPTION, USER_ROLES)
    select 'sarah', 'sarah', '51236785', 'sarah@hkmu.edu.hk', 'I am Sarah', array['USER']
    where not exists(select 1 from blog_user where id = 2);

insert into blog_user (USERNAME, PASSWORD, PHONE_NUMBER, EMAIL, DESCRIPTION, USER_ROLES)
    select 'max', 'max', '64023198', 'max@hkmu.edu.hk', 'I am Max', array['USER', 'ADMIN']
    where not exists(select 1 from blog_user where id = 3);;