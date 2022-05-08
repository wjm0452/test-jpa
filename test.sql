select
    smlclascd0_.lrgclas_cd as lrgclas_cd1_5_0_,
    smlclascd0_.smlclas_cd as smlclas_cd2_5_0_,
    smlclascd0_.lst_upd_dtm as lst_upd_dtm3_5_0_,
    smlclascd0_.lst_upd_id as lst_upd_id4_5_0_,
    smlclascd0_.lst_upd_pgm_id as lst_upd_pgm_id5_5_0_,
    smlclascd0_.reg_dtm as reg_dtm6_5_0_,
    smlclascd0_.reg_id as reg_id7_5_0_,
    smlclascd0_.reg_pgm_id as reg_pgm_id8_5_0_,
    smlclascd0_.memo as memo9_5_0_,
    smlclascd0_.smlclas_cd_nm as smlclas_cd_nm10_5_0_,
    smlclascd0_.srt_seq as srt_seq11_5_0_,
    smlclascd0_.use_yn as use_yn12_5_0_
from
    tbsysz004c smlclascd0_
where
    smlclascd0_.lrgclas_cd = ?
    and smlclascd0_.smlclas_cd = ?