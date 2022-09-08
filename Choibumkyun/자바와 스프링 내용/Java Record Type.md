# Java, Record Type
Record 타입은 자바 16버전에서 추가가 되었다. 이는 <b>불변(Immutable)</b>으로 파라미터, 리턴 타입으로 사용한다.   

```
record FullName(String first, String last) {
    public String formatter() {
        return first + " " + last;
    }
}

FullName fullName = new FullName("f", "l");

fullName.first();
fullName.last();
```

## Record 타입을 Controller에서 사용
REST API - GET: 쿼리 파라미터 Record 타입으로 받는 것은 다음과 같다.   

```
public record MemberListRequest(
    String keyword,
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate since,
    Integer page,
    Integer size
) {}
```

```
@GetMapping("/api/members")
public ResponseEntity<?> members(MemberListRequest request) {
    log.info("request: {}". request);

    ...
}
```

REST API - POST: JSON 요청 Record 타입으로 받는 것은 다음과 같다.   

```
public record MemberCreateRequest(
    String name,
    String email,
    @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birth
) {}
```

```
@PostMapping("/api/members")
public ResponseEntity<?> newMember(MemberCreateRequest request) {
    log.info("request: {}". request);

    ...
}
```

REST API - JSON 응답에 Record 타입 사용하는 방법은 다음과 같다.   

```
public record MemberData(
    Long id,
    String name,
    String email
) {}
```

```
@GetMapping("/api/members")
public ResponseEntity<?> members(...) {
    List<MemberData> members = ;
    
    return ResponseEntity.ok(members);
}
```
## Record 타입을 Thymeleaf에서 사용하는 방법
Record 타입을 Thymeleaf에서 사용하는 방법은 다음과 같다. 사용할 때는 스프링부트와 Thymeleaf로 예를 들을 수 있다.   

```
public record MemberData(
    Long id,
    String name,
    String email
) {}
```

```
@GetMapping("/web/members")
public String members(..., Model model) {
    List<MemberData> members = ...;
    model.addAttribute("members", members);

    return "members/list";
}
```

```
<tr th:each = "m : ${members}>
    <td th:text = "${m.id}"></td>
    <td th:text = "${m.email}"></td>
    <td th:text = "${m.name}"></td>
</tr>
```

## MyBatis Parameter
Mapper Parameter 타입으로 사용이 가능하다.   

```
public record MemberSelectParam(
    String nameLike,
    int startRow,
    int size
) {}
```

```
@Mapper
public interface MemberMapper {
    List<MemberRecord> select(MemberSelectParam param);
}
```

```
<select id = "select" resultMap = "memberRecordMap">
    SELECT * FROM member m WHERE m.name LIKE #{nameLike} ORDER BY m.id DESC LIMIT #{startRow}, ${size}
</select>
```

## 정리
* Record 타입 사용이 가능하다.
    * 스프링 MVC Controller의 요청과 응답으로 사용이 가능하다.
        * JSON 변환도 가능하다.
    * Thymeleaf에서 사용이 가능하다.
    * MyBatis Mapper Parameter로 사용이 가능하다.
* 스프링 부트에서 Record 타입을 사용할 수 있는가? (스프링 MVC - JSON 요청과 응답 API, Thymeleaf 연동)
    * BeanPropertyRowMapper는 사용이 불가능하다.
    * MyBatis Mapper Return 타입으로 사용이 불가능하다.   

[자바 레코드 타입](https://www.youtube.com/watch?v=UzVMz9gMwgA)