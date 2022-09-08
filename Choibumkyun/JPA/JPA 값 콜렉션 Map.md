# JPA, 값 콜렉션 Map

## 단순 값을 Map로 보관하는 모델
```
doc
id varchar(10)              
title varchar(100)
content varchar(4000)       Document
    |                       -id: String
    |                   =>  -title: String
    |                       -content: String
    |                       -props: Map<String, String>
doc_prop
doc_id varchar(10)
name varchar(100)
value varchar(100)
```

```
@Entity
@Table(name="question")
public class Question {

    @Id
    private String id;

    private String text;
    private String content;

    @ElementCollection
    @CollectionTable(
        name="doc_prop", // Join에 사용할 테이블
        joinColumns=@JoinColumn(name="doc_id") // Join Key
    )

    @MapKeyColumn(name="name") // Map의 Key에 지정할 컬럼
    @Column(name="value") // 매핑할 데이터 (실제 값을 가지고 있는 값)
    private Map<String, String> props = new HashMap<>();
}
```

### 저장
```
Map<String, String> props = new HashMap<>();
props.put("p1", "v1");
props.put("p2", "v2");
Document doc = new Document(id, "제목", "내용", props);
em.persist(doc);
```

```
INSERT INTO doc (content, title, id) values (?, ?, ?)
INSERT INTO doc_prop (doc_id, name, value) values (?, ?, ?)
INSERT INTO doc_prop (doc_id, name, value) values (?, ?, ?)
```

값 콜렉션 List에서와 같이 Map의 Put한 값의 개수만큼 값을 저장한다. 그리고 Index 값은 0부터 시작한다.   

### Map에 값 추가, 수정, 삭제
```
Question q = em.find(Question.class, id);
q.setChoices(LDocument doc = em.find(Document.class, id));
doc.setProp("p1", "v1new"); // 수정
doc.setProp("p10", "v10"); // 추가
doc.removeProp("p2"); // 삭제
```

```
public void setProp(String name, String value) {
    props.put(name, value);
}

public void removeProp(String name) {
    props.remove(name);
}
```

```
DELETE FROM doc_prop WHERE doc_id=? and name=?
UPDATE doc_prop SET value=? WHERE doc_id=? and name=?
INSERT INTO doc_prop (doc_id, name, value) values (?, ?, ?)
```

## @Embeddable 타입 Map
값 콜렉션 Set, List와 크게 다르지 않다.   

```
doc
id varchar(10)
title varchar(100)
content varchar(4000)
    |
    |
doc_prop
doc_id varchar(10)
name integer(100)
value varchar(100)
enabled tinyint(1)

<=>

Document
-id: String
-title: String
-content: String
-props: Map<String, String>
    |
    |
PropValue
-value: String
-enabled: Boolean
```

이를 코드로 매핑 설정할 때는 다음과 같다.   

```
@Entity
@Table(name="doc")
public class Document {

    @Id
    private String id;

    private String title;
    private String content;

    @ElementCollection
    @CollectionTable(
        name="doc_prop", // Join에 사용할 테이블
        joinColumns=@JoinColumn(name="doc_id") // Join Key
    )

    @MapKeyColumn(name="name")
    private Map<String, PropValue> props = new HashMap<>();
}
```

```
@Embeddable
@Access(AccessType.FIELD)
public class PropValue {

    private String value;
    private boolean enabled;
}
```

## 정리
콜렉션 테이블을 이용한 값 Map 매핑은 ```@ElementCollection```, ```@CollectionTable```, ```@MapKeyColumn(Map의 Key 값 저장할 Annotation)```으로 단순하게 사용이 가능하다.   

[값 콜렉션 List](https://www.youtube.com/watch?v=Wq4B5RpIeAY)