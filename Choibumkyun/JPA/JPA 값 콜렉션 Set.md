# JPA, 값 콜렉션 Set

## 단순 값을 Set으로 보관하는 모델
```
role
id varchar(10)              Role
name varchar(20)            -id: String
    |                   =>  -name: String
    |                       -permission: Set<String>
role_perm
role_id varchar(10)
perm varchar(20)
```

```
@Entity
@Table(name="role")
public class Role {

    @Id
    private String id;

    private String name;

    @ElementCollection
    @CollectionTable(
        name="role_perm", // Join에 사용할 테이블
        joinColumns=@JoinColumn(name="role_id") // Join Key
    )

    @Column(name="perm") // 매핑할 데이터 (실제 값을 가지고 있는 값)
    private Set<String> permissions = new HashSet<>();
}
```

### 저장을 할 경우,

```
Role role = new Role(roleId, "관리자", Set.of("F1", "F2"));
em.persist(role);

=>

INSERT INTO role (name, id) values (?, ?)
INSERT INTO role_perm (role_id, perm) values (?, ?)
INSERT INTO role_perm (role_id, perm) values (?, ?)
```

Set의 입력 개수만큼 저장이 이루어진다.   

### 조회 (Lazy 방법 = 연관된 테이블을 나중에 읽어온다는 것)
Lazy 방법을 사용하면 Role을 조회하고 나중에 Role_perm이 필요할 때 조회하겠다는 것이다.   

```
SELECT r1_0.id, r1_0.name FROM role r1_0 WHERE r1_0.id=?

<=

Role role = em.find(Role.class, roleId);

for (String perm : role.getPermissions()) {
    logger.info("perm: {}", perm);
}

=>

SELECT r1_0.role_id, r1_0.perm FROM role_perm r1_0 WHERE r1_0.roleId=?
```

### 조회 (eager 방법 = 즉시 조회)
```
@ElementCollection(fetch=FetchType.EAGER)
@CollectionTable(...)
@Column(name="perm")
private Set<String> permissions = new HashSet<>();
```

```
Role role = em.find(Role.class, roleId);

for (String perm : role.getPermissions()) {
    logger.info("perm: {}", perm);
}
```

```
SELECT r1_0.id, r1_0.name, p1_0.role_id, p1_0.perm FROM role r1_0 LEFT JOIN role_perm p1_0 on p1_0.id=p1_0.role_id WHERE r1_0.id=?
```

### Set 수정 (Add(), Remove())
```
Role role = em.find(Role.class, roleId);

role.getPermissions().add("F3");
role.getPermissions().remove("F1");
```

```
DELETE FROM role_perm WHERE role_id=? and perm=?
INSERT INTO role_perm (role_id, perm) values (?, ?)
```

## Set 새로 할당
```
Role role = em.find(Role.class, roleId);
role.setPermissions(Set.of("F4", "F5));

// Role#setPermissions()
public void setPermissions(Set<String> new Permissions) {
    this.permissions = newPermissions;
}
```

Set을 새로 할당한다면 우선 기존의 것을 지운다. 그리고 다시 추가를 하여 할당한다.   

```
DELETE FROM role_perm WHERE role_id=?
INSERT INTO role_perm (role_id, perm) values (?, ?)
INSERT INTO role_perm (role_id, perm) values (?, ?)
```

## Set Clear()
```
Role role = em.find(Role.class, roleId);
role.revokeAll();

// Role#revokeAll()
public void revokeAll() {
    this.permissions.clear(); // Set 데이터에 접근이 발생 (Select 발생)
}
```

```
SELECT p1_0.role_id, p1_0.perm FROM role_perm p1_0 WHERE p1_0.role_id=?
DELETE FROM role_perm WHERE role_id=?
```

## @Embeddable 타입 Set
```
role
id varchar(10)
name varchar(20)
    |
    |
role_perm
role_id varchar(10)
perm varchar(20)
grantor varchar(10)

<=>

<<Entity>>
Role
-id: String
-name: String
-permission: Set<GrantedPermission>
    |
    |
<<Embeddable>>
GrantedPermission
-permission: String
-grantor: String
```

```
@Entity
@Table(name="role")
public class Role2 {

    @Id
    private String id;

    private String name;

    @ElementCollection
    @CollectionTable(
        name="role_perm", // Join에 사용할 테이블
        joinColumns=@JoinColumn(name="role_id") // Join Key
    )

    // Set 타입을 @Embeddable한 클래스를 지정해주기만 하면 된다.
    private Set<GrantedPermission> permissions = new HashSet<>();
}
```

```
@Embeddable
public class GrantedPermission {

    @Column(name="perm")
    private String permission;
    private String grantor;
}
```

만약에 <b>@Embeddable</b>를 사용해 매핑을 할 경우, ```Equal 메소드```, ```HashCode 메소드```가 필요하다.   

## 정리
콜렉션 테이블을 이용한 값 Set 매핑은 ```@ElementCollection```과 ```@CollectionTable```으로 단순하게 사용이 가능하다.   

[값 콜렉션 Set](https://www.youtube.com/watch?v=lQ4-kVeHVGk)