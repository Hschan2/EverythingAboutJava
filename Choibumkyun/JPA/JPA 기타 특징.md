# JPA, 기타 특징

## AttributeConverter
<b>AttributeConverter</b>는 매핑을 지원하지 않는 자바 타입과 데이터 베이스 타입 간 변환 처리를 담당한다. 예를 들어 Boolean 타입과 Char(1) 타입 간 변환처럼 말이다.   

```
public class BooleanYesNoConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return Objects.equals(Boolean.TRUE, attribute) ? "Yes" : 'No";
    }

// 데이터 베이스 속성 값을 엔티티 값으로 변환
    @Override
    public String convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData) ? true : false;
    }

}
```
```
@Entity
@Table(name="notice")
public class Notice {

    @Id
    @GeneratedValue(strategy="GenerationType.IDENTITY)
    @Column(name="notice_id")
    private Long id;

    private String title;
    private String content;

    @Column(name="open_yn")
    @Convert(converter=BooleanYesNoConverter.class) // 변환할 인스턴스
    private boolean opened;
}
```

## @Formula
<b>Formula</b>는 SQL을 이용한 속성 매핑을 말하며 하이버네이트에서 제공한다. 조회에서만 매핑을 처리하며 (Insert, Update는 매핑 대상이 아니다.) 하이버네이트 제공 기능이 있다. (org.hibernate.annotations.Formula) 그리고 주로 데이터 베이스 함수 호출, 서브 쿼리 결과를 매핑한다.   

```
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy="GenerationType.IDENTITY)
    @Column(name="notice_id")
    private Long id;

    ...

    @Column(name="cat")
    private String categoryCode;

    @Formula("(SELECT c.name FROM category c WHERE c.cat_id = cat)")
    private String categoryName;
}
```

### 수정 쿼리의 Column
수정 쿼리는 기본적으로 모든 Column을 포함한다.   

```
@Entity
@Table(name="notice")
public class Notice {

    ...

    @Column(name="open_yn")
    @Convert(converter=BooleanYesNoConverter.class) // 변환
    private boolean opened;

    ...

    public void open() {
        this.opened = true;
    }
}
```
```
Notice notice = em.find(Notice.class, id);
notice.open();
```

위 같은 경우에 실행할 경우, 변화가 일어날 경우 모든 것에 업데이트가 진행된다. 그러나 변환된 정보만 업데이트를 하고 싶은 경우가 존재하는데 이럴 경우에 DynamicUpdate를 사용하게 된다.   

## @DynamicUpdate / @DynamicInsert
<b>@DynamicUpdate</b>는 변경된 컬럼만 Update 쿼리에 포함되게 하는 것이다. 그리고 <b>@DynamicInsert</b>는 Null이 아닌 컬럼만 Insert 쿼리에 포함되게 한다. 주의할 것은 기본값을 사용할 수가 있다. 그래서 Null을 지정해야 할 경우 사용하지 않는 것이 좋다.   

```
@Entity
@Table(name="notice")
@DynamicUpdate
public class Notice {

    ...

    @Column(name="open_yn")
    @Convert(converter=BooleanYesNoConverter.class) // 변환
    private boolean opened;

    ...

    public void open() {
        this.opened = true;
    }
}
```
```
Notice notice = em.find(Notice.class, id);
notice.open();

=> UPDATE notice SET open_yn = ? WHERE notice_id = ?
```

## @Immutable
<b>@Immutable</b>는 변경 추적 대상에서 해당 엔티티를 제외 처리하는 것을 말한다. 변경 추첮ㄱ을 위한 메모리 사용이 감소되고 주로 조회 목적으로만 사용하는 엔티티 매핑에 사용한다.   

참고할 것은 @Immutable이 적용된 엔티티도 저장이 된다. 코드 수준에서 persist() 하지 않도록 주의해야 한다.   

```
@Entity
@Table(name="notice")
@Immutable
public class NoticeReadonly {

    @Id
    @Column(name="notice_id")
    private Long id;

    private String title;
}
```

## @Subselect
<b>@Subselect</b>는 Select 결과를 엔티티로 매핑하는 것을 말한다. 수정 대상이 아니므로 @Immutable과 함께 사용한다.   


```
@Subselect(
    """
    SELECT a.article_id, a.title, w.name as writer, a.written_at FROM article a LEFT JOIN writer w ON a.writer_id = w.id
    """
)
@Entity
@Immutable
public class ArticleListView {

    @Id
    @Column(name="article_id")
    private Long id;

    private String title;
    private String writer;

    @Column(name="written_at")
    private LocalDateTime writtenAt;
}
```
```
조회 코드

TypedQuery<ArticleListView> query = em.createQuery(
    "SELECT a FROM articleListView a " +
    "WHERE a.writtenAt >= :since " +
    "ORDER BY a.id DESC",
    ArticleListView.class
);

query.setParameter("since", LocalDateTime.now().minusDays(7));
List<ArticleListView> list = query.getResultList();
```

## 그 외 기타
* 상속 매핑
* 네이티브쿼리
* 하이버네이트 Annotation
    * @CreationTimestamp
    * @UpdateTimestamp   

[JPA의 기타 특징](https://www.youtube.com/watch?v=deJnCTkjLyU)