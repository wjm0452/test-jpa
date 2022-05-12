
# 작성 가이드
## 개요
jpa 작성법을 간단하게 기술하였습니다.

## ORM(Object-relational mapping)
객체와 관계 데이터베이스 사이를 매핑

## JPA(Java Persistence API)
어플리케이션과 JDBC사이에서 동작하여 사용자가 JPA 사용시 JPA내부에서 JDBC API를 사용하여 SQL 호출 및 DB와 통신

## 1. Entity 작성
데이터베이스상의 테이블과 매핑될 클래스로 아래와 같이 작성 합니다.
```java
@Entity
@Table(name = "TBSYSZ003C")
public class Lrgclas extends BaseEntity  {
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
| property | description                                                              |
| -------- | ------------------------------------------------------------------------ |
| @Entity  | 데이터베이스의 테이블과 매핑                                             |
| @Table   | 매핑할 테이블 이름 지정 하며 생략 가능하다.(생략시 엔티티 이름으로 매핑) |
| @Id      | PK를 지정                                                                |

복합키 일경우 아래와 같이 작성 합니다.
 ```java
@Entity
@Table(name = "TBSYSZ004C")
@IdClass(Smlclas.PK.class) // 필수
public class Smlclas extends BaseEntity {

    @Id
    private String lrgclasCd;

    @Id
    private String smlclasCd;

    private String smlclasCdNm;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    @MapsId("lrgclasCd")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lrgclasCd")
    private Lrgclas lrgclas;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class PK implements Serializable {
        private String lrgclasCd;
        private String smlclasCd;
    }
}
 ```

## 2. Repository
### 2.1 JpaRepository
spring data에서 미리 정의 해둔 JpaRepository를 상속합니다.
> JpaRepository: spring data에서 정의해둔 인터페이스(간단한 CRUD이면 상속만으로 충분합니다.)
```java
@Repository
public interface SmallCodeRepository extends JpaRepository<Smlclas, Smlclas.PK> {
    
}
```

### 2.2. 사용자 정의 함수
메소드 작성시 메소드 이름을 파싱하여 사용할 수 있도록 지원합니다.
```java
@Repository
public interface SmallCodeRepository extends JpaRepository<Smlclas, Smlclas.PK> {
    List<Smlclas> findAllByLrgclasCd(String lrgclasCd);
    Optional<Smlclas> findByLrgclasCdAndSmlclasCd(String lrgclasCd, String smlclasCd);
}
```
> 참고 https://docs.spring.io/spring-data/jpa/docs/1.10.1.RELEASE/reference/html/#jpa.sample-app.finders.strategies

### 2.3. repository 사용
findAll, findById, save 등은 미리 정의 되어 있습니다.
```java
@Test
void findById() {
    List<Smlclas> codes = smallCodeRepository.findAll();
    Smlclas code = smallCodeRepository.findById(new Smlclas.PK("WWW01", "WWW01_S_01")).orElseGet(null);

    assertThat(codes).isNotNull().size().isGreaterThan(0);
    assertThat(code).isNotNull();
}
```

## 3. 조회조건 사용하기
Dynamic Query 사용시 의 조회조건 처리 방법입니다.
### 3.1. Example.of
설정된 필드의 값이 널이면 조건에서 무시되며 일치되는 값으로만 설정이 됩니다.
```java
@Test
    void exampleOf() {
        Smlclas smclasCondition = new Smlclas();
        smclasCondition.setLrgclasCd("WWW01"); // lrgclasCd만 조회조건으로 설정됨.

        List<Smlclas> codes = smallCodeRepository.findAll(Example.of(smclasCondition));
        assertThat(codes).isNotNull().size().isGreaterThan(0);
    }
```
아래와 같이 쿼리가 구성되어 수행된다.
```sql
select
    ...
from
    tbsysz004c smlclas0_
where
    smlclas0_.lrgclas_cd = ?
```
### 4.1. ExmapleMatcher
조회조건을 일치되는 값이 아닌 조건을 사용하는경우 아래와 같이 사용한다.
```java
@Test
    void exampleMatcher() {

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("lrgclasCd", GenericPropertyMatchers.ignoreCase())
                .withMatcher("smlclasCd", GenericPropertyMatchers.ignoreCase())
                .withMatcher("smlclasCdNm", GenericPropertyMatchers.contains());

        Smlclas smclasCondition = new Smlclas();
        smclasCondition.setSmlclasCdNm("WWW");

        List<Smlclas> codes = smallCodeRepository.findAll(Example.of(smclasCondition, exampleMatcher));
        assertThat(codes).isNotNull().size().isGreaterThan(0);
    }
```
아래와 같이 쿼리가 구성되어 수행된다.
```sql
select
    ...
from
    tbsysz004c smlclas0_
where
    smlclas0_.smlclas_cd_nm like ? escape ?
```

## 4. 연관관계 설정(Join)
Entity간 연관관계를 어떻게 지정할지 여부에 따라 아래 연관관계 방법을 선택합니다.

| Join   | Description                                                                     |
| ------ | ------------------------------------------------------------------------------- |
| 단방향 | 한쪽 Entity에서만 Join 대상을 지정, 지정한 Entity에서만 대상 Entity로 접근 가능 |
| 양방향 | 양쪽 모두 Entity에 Join 대상을 지정                                             |

### 4.1. 단방향 연관관계
한쪽 Entity에서만 관계를 설정하는 방법
```java
@Entity
@Table(name = "TBCOM2010M")
public class Board extends BaseEntity {
    @Id
    private String blbdId;
    private String blbdDvcd;
    private String blbdNm;
    private Integer srtSeq;
    private String blbdDesc;
    private String blbdUseYn;
    private String allOrgRlseYn;
    private String blbdRlseYn;
    private String blthgAlrmUseYn;;
    private String pfcUseYn;
    private String clsdDispYn;
    private String replyPmssYn;

    @ToString.Exclude
    @MapsId("blbdId")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blbdId")
    private List<BoardOrg> boardOrgs; // 단방향으로 지정

    @ToString.Exclude
    @MapsId("blbdId")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blbdId")
    private List<BoardHeader> boaredHeaders;

    @ToString.Exclude
    @MapsId("blbdId")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blbdId")
    private List<BoardAdmin> boardAdmins;
}
```
@JoinColumn을 사용하여 연관 대상 Entity를 지정합니다.

### 4.2. 양방향 연관관계
상호 Entity 모두에 관계를 설정하는 방법
```java
@Entity
@Table(name = "TBSYSZ003C")
public class Lrgclas extends BaseEntity {
    @Id
    private String lrgclasCd;
    private String lrgclasCdNm;

    private String cdDv;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    @ToString.Exclude
    @OneToMany(mappedBy = "lrgclas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Smlclas> smlclass;
}

```
객체의 마스터가 되는쪽에서는 JoinColumn을 지정하지 않고 mappedBy만 지정합니다.

mappedBy는 하위 Entity의 인스턴스명을 지정합니다.

```java
@Entity
@Table(name = "TBSYSZ004C")
@IdClass(Smlclas.PK.class)
public class Smlclas extends BaseEntity {

    @Id
    private String lrgclasCd;

    @Id
    private String smlclasCd;

    private String smlclasCdNm;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    @MapsId("lrgclasCd")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lrgclasCd")
    private Lrgclas lrgclas;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class PK implements Serializable {
        private String lrgclasCd;
        private String smlclasCd;
    }
}
```
하위 Entity에서는 상위 Entity와의 JoinColumn명을 지정합니다.

### 4.3. Fetch Type
> Entity 조회시 연관관계가 설정된 Entity를 언제 가져올것인지를 설정합니다.
> fetchType에 따라 ***성능 이슈***가 발생할 수 있습니다.
> N + 1 문제 등...

연관관계가 설정된 Entity는 아래와 같습니다.
```java
@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "blbdId")
private List<BoardOrg> boardOrgs;
```

#### 4.3.1 FetchType.EAGER
 * 연관관계가 설정된 Entity를 즉시 조회합니다.
```java
@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinColumn(name = "blbdId")
private List<BoardOrg> boardOrgs;
```

#### 4.3.2 FetchType.LAZY
연관관계가 설정된 Entity의 getter메소드를 수행할때 Select 쿼리가 수행됩니다.
```java
@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "blbdId")
private List<BoardOrg> boardOrgs;
```

### 4.4 N + 1
> N + 1 Problem: 조회대상이 되는 Entity의 Row 수에 따라 연관관계가 설정된 Entity의 Select 쿼리가 수행됩니다.
> 이때 성능이슈 N + 1 문제가 발생되는 현상으로 아래와 같은 방법으로 해결을 해야 합니다.

FetchType.EAGER일때
* 단건 조회시 연관관계로 설정된 Entitiy에 Join되어 하나의 쿼리로 수행됨
```sql
select
    ...
from
    tbcom2010m board0_
    left outer join tbcom2013m boardadmin1_ on board0_.blbd_id = boardadmin1_.blbd_id
    left outer join tbcom2011m boardorgs2_ on board0_.blbd_id = boardorgs2_.blbd_id
    left outer join tbcom2014m boaredhead3_ on board0_.blbd_id = boaredhead3_.blbd_id
where
    board0_.blbd_id = ?
```
* 다건 조회시 연관관계로 설정된 Entity가 모두 각각의 쿼리로 수행됨
```sql
select ... from tbcom2010m board0_
select ... from tbcom2014m boaredhead0_ where boaredhead0_.blbd_id = ?
select ... from tbcom2011m boardorgs0_ where boardorgs0_.blbd_id = ?
select ... from tbcom2013m boardadmin0_ where boardadmin0_.blbd_id = ?
select ... from tbcom2014m boaredhead0_ where boaredhead0_.blbd_id = ?
select ... from tbcom2011m boardorgs0_ where boardorgs0_.blbd_id = ?
select ... from tbcom2013m boardadmin0_ where boardadmin0_.blbd_id = ?
```

***해결방안***
* JPQL Fetch Join
```java
// Repository에 JPQL로 작성
@Repository
public interface LargeCodeRepository extends JpaRepository<Lrgclas, String> {
    @Query("select l from Lrgclas l join fetch l.smlclass")
    List<Lrgclas> findAllWithSmlclass();
}
```
* EntityGraph
```java
@Repository
public interface LargeCodeRepository extends JpaRepository<Lrgclas, String> {
    @EntityGraph(attributePaths = "smlclass")
    List<Lrgclas> findWithSmlclassByLrgclasCd(String id);
}
```

### 5. Dirty checking
영속화된 데이터를 save하지 않아도 update 실행

### 6. Querydsl
JpaRepository에 있는 기능으로 쿼리 작성 불가시 사용함

사용자정의 Repository를 작성하기 위해 Repository, RepositoryCustom, RepositoryImpl 구현이 필요하다.
> OrgHistoryRepository 참고





