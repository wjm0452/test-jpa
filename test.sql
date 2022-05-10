select
    board0_.blbd_id as blbd_id1_0_,
    board0_.lst_upd_dtm as lst_upd_dtm2_0_,
    board0_.lst_upd_id as lst_upd_id3_0_,
    board0_.lst_upd_pgm_id as lst_upd_pgm_id4_0_,
    board0_.reg_dtm as reg_dtm5_0_,
    board0_.reg_id as reg_id6_0_,
    board0_.reg_pgm_id as reg_pgm_id7_0_,
    board0_.all_org_rlse_yn as all_org_rlse_yn8_0_,
    board0_.blbd_desc as blbd_desc9_0_,
    board0_.blbd_dvcd as blbd_dvcd10_0_,
    board0_.blbd_nm as blbd_nm11_0_,
    board0_.blbd_rlse_yn as blbd_rlse_yn12_0_,
    board0_.blbd_use_yn as blbd_use_yn13_0_,
    board0_.blthg_alrm_use_yn as blthg_alrm_use_yn14_0_,
    board0_.clsd_disp_yn as clsd_disp_yn15_0_,
    board0_.pfc_use_yn as pfc_use_yn16_0_,
    board0_.reply_pmss_yn as reply_pmss_yn17_0_,
    board0_.srt_seq as srt_seq18_0_
from
    tbcom2010m board0_
    left outer join tbcom2011m boardorgs1_ on board0_.blbd_id = boardorgs1_.blbd_id
    left outer join tbcom2014m boaredhead2_ on board0_.blbd_id = boaredhead2_.blbd_id
    left outer join tbcom2013m boardadmin3_ on board0_.blbd_id = boardadmin3_.blbd_id