Hibernate:
select
    orghistory0_.org_cd as col_0_0_,
    orghistory0_.org_nm as col_1_0_
from
    tbsys2001h orghistory0_
    inner join tbsys2001m org1_ on (orghistory0_.org_cd = org1_.org_cd)
where
    (orghistory0_.org_cd is not null)
    and (
        org1_.org_cd = ?
        or org1_.org_nm like ? escape '!'
    )