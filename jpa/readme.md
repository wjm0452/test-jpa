
# 작성 가이드
## 개요
jpa를 처음 접하는 사용자를 위해 작성법을 기술하였습니다.

## ORM(Object-relational mapping)
객체와 관계 데이터베이스 사이를 매핑

## JPA(Java Persistence API)
어플리케이션과 JDBC사이에서 동작하여 사용자가 JPA 사용시 JPA내부에서 JDBC API를 사용하여 SQL 호출 및 DB와 통신

## 작성 가이드
### Entity 작성
데이터베이스상의 테이블과 매핑될 클래스
```java
@Entity
@Table(name = "TBSYSZ003C")
public class Lrgclas {
    @Id
    private String lrgclasCd;
    private String lrgclasCdNm;

    private String cdDv;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    @OneToMany(mappedBy = "lrgclas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Smlclas> smlclass;
}
```
| property | description |
| -------- | ----------- |
| @Entity  |   |
| @Table |    |


### Repository

### 조회조건 사용하기
Example.of
ExmapleMatcher

### Join

#### 단방향 Join

#### 양방향 Join

#### fetchType

#### fetch join

### dirty checking

#### criteria



