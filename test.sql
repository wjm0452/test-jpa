select
    board0_.blbd_id as blbd_id1_0_1_,
    board0_.lst_upd_dtm as lst_upd_dtm2_0_1_,
    board0_.lst_upd_id as lst_upd_id3_0_1_,
    board0_.lst_upd_pgm_id as lst_upd_pgm_id4_0_1_,
    board0_.reg_dtm as reg_dtm5_0_1_,
    board0_.reg_id as reg_id6_0_1_,
    board0_.reg_pgm_id as reg_pgm_id7_0_1_,
    board0_.all_org_rlse_yn as all_org_rlse_yn8_0_1_,
    board0_.blbd_desc as blbd_desc9_0_1_,
    board0_.blbd_dvcd as blbd_dvcd10_0_1_,
    board0_.blbd_nm as blbd_nm11_0_1_,
    board0_.blbd_rlse_yn as blbd_rlse_yn12_0_1_,
    board0_.blbd_use_yn as blbd_use_yn13_0_1_,
    board0_.blthg_alrm_use_yn as blthg_alrm_use_yn14_0_1_,
    board0_.clsd_disp_yn as clsd_disp_yn15_0_1_,
    board0_.pfc_use_yn as pfc_use_yn16_0_1_,
    board0_.reply_pmss_yn as reply_pmss_yn17_0_1_,
    board0_.srt_seq as srt_seq18_0_1_,
    boardadmin1_.blbd_id as blbd_id1_3_3_,
    boardadmin1_.usr_id as usr_id2_3_3_,
    boardadmin1_.blbd_id as blbd_id1_3_0_,
    boardadmin1_.usr_id as usr_id2_3_0_,
    boardadmin1_.use_yn as use_yn3_3_0_
from
    tbcom2010m board0_
    left outer join tbcom2013m boardadmin1_ on board0_.blbd_id = boardadmin1_.blbd_id
where
    board0_.blbd_id = ?