# JPA, 값 콜렉션 List

## 단순 값을 List로 보관하는 모델
```
question
id varchar(10)              Question
text varchar(20)            -id: String
    |                   =>  -text: String
    |                       -choices: List<String>
question_choice
question_id varchar(10)
idx integer(10)
text varchar(20)
```

```
@Entity
@Table(name="question")
public class Question {

    @Id
    private String id;

    private String text;

    @ElementCollection
    @CollectionTable(
        name="question_choice", // Join에 사용할 테이블
        joinColumns=@JoinColumn(name="question_id") // Join Key
    )

    @OrderColumn(name="idx") // Index 값을 저장한 컬럼 지정
    @Column(name="text") // 매핑할 데이터 (실제 값을 가지고 있는 값)
    private List<String> choices
}
```

### 저장
```
Question q = new Question(id, "질문", List.of("보기1", "보기2"));
em.persist(q);
```

```
INSERT INTO question (text, id) values (?, ?)
INSERT INTO question_choice (question_id, idx, text) values (?, ?, ?)
INSERT INTO question_choice (question_id, idx, text) values (?, ?, ?)
```

값 콜렉션 Set에서와 같이 List에 넣은 값의 개수만큼 값을 저장한다. 그리고 Index 값은 0부터 시작한다.   

### 조회
* Lazy 방법
* Eager 방법

```
SELECT q1_0.id, q1_0.text FROM question q1_0 WHERE q1_0.id=?
```

```
SELECT c1_0.question_id, c1_0.idx, c1_0.text FROM question_choice c1_0 WHERE c1_0.question_id=?
```

```
Question q = em.find(Question.class, id);
logger.info("보기 개수: {}", q.getChoices().size());

|
|   @ElementCollection(fetch=FetchType.EAGER)
|

SELECT q1_0.id, c1_0.question_id, c1_0.idx, c1_0.text, q1_0.text FROM question q1_0 LEFT JOIN question_choice c1_0 on q1_0.id=c1_0.question_id WHERE q1_0.id=?
```

### List 수정, 새로 할당

```
Question q = em.find(Question.class, id);
q.setChoices(List.of("답1", "답2"));
```

```
public void setChoices(List<String> choices) {
    this.choices = choices;
}
```

```
DELETE FROM question_choice WHERE question_id=?
INSERT INTO question_choice (question_id, idx, text) values (?, ?, ?)
INSERT INTO question_choice (question_id, idx, text) values (?, ?, ?)
```

Set과 마찬가지로 기존 데이터를 삭제하고 새로 값을 할당하여 저장한다.   

### Entity 삭제
```
Question q = em.find(Question.class, id);
em.remove(q);
```

```
DELETE FROM question_choice WHERE question_id=?
DELETE FROM question WHERE id=?
```

Set과 마찬가지로 remove로 코드를 진행하여 삭제가 가능하다.   

## @Embeddable 타입 List
값 콜렉션 Set과 다르지 않다.   

```
question
id varchar(10)
text varchar(20)
    |
    |
question_choice
question_id varchar(10)
idx integer(10)
text varchar(20)
input tinyint(1)

<=>

Question
-id: String
-text: String
-choices: List<Choice>
    |
    |
Choice
-text: String
-input: Boolean
```

이를 코드로 매핑 설정할 때는 다음과 같다.   

```
@Entity
@Table(name="question")
public class Question2 {

    @Id
    private String id;

    private String text;

    @ElementCollection
    @CollectionTable(
        name="question_choice", // Join에 사용할 테이블
        joinColumns=@JoinColumn(name="question_id") // Join Key
    )

    @OrderColumn(name="idx")
    private List<Choice> choices;
}
```

```
@Embeddable
public class Choice {

    private String text;
    private String input;
}
```

## 정리
콜렉션 테이블을 이용한 값 List 매핑은 ```@ElementCollection```, ```@CollectionTable```, ```@OrderColumn(Index 값 저장할 Annotation)```으로 단순하게 사용이 가능하다.   

[값 콜렉션 List](https://www.youtube.com/watch?v=Wq4B5RpIeAY)