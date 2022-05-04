select
    for collection com.jhelper.jserve.web.entity.Org.users:
select
    users0_.org_cd as org_cd19_1_0_,
    users0_.usr_id as usr_id1_1_0_,
    users0_.usr_id as usr_id1_1_1_,
    users0_.eml_addr as eml_addr2_1_1_,
    users0_.gndr_dvcd as gndr_dvcd3_1_1_,
    users0_.lgin_try_tcnt as lgin_try_tcnt4_1_1_,
    users0_.lgin_yn as lgin_yn5_1_1_,
    users0_.lst_upd_dtm as lst_upd_dtm6_1_1_,
    users0_.lst_upd_id as lst_upd_id7_1_1_,
    users0_.lst_upd_pgm_id as lst_upd_pgm_id8_1_1_,
    users0_.mbph_no as mbph_no9_1_1_,
    users0_.org_cd as org_cd19_1_1_,
    users0_.poto as poto10_1_1_,
    users0_.pw as pw11_1_1_,
    users0_.pw_upd_dtm as pw_upd_dtm12_1_1_,
    users0_.reg_dtm as reg_dtm13_1_1_,
    users0_.reg_id as reg_id14_1_1_,
    users0_.reg_pgm_id as reg_pgm_id15_1_1_,
    users0_.use_yn as use_yn16_1_1_,
    users0_.usr_nm as usr_nm17_1_1_,
    users0_.usr_role_id as usr_role_id18_1_1_
from
    tbsys2002m users0_
where
    users0_.org_cd = ?