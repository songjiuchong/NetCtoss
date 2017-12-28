select count(*) from ACCOUNT a where 1=1 and STATUS=-1
select ai.ID,ri.NAME from ADMIN_INFO ai inner join ADMIN_ROLE ar on ai.ID = ar.ADMIN_ID 
inner join ROLE ri on ri.ID = ar.ROLE_ID 
inner join ROLE_PRIVILEGE rp on rp.ROLE_ID = ri.ID 
where 1=1 