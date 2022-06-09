# Spring Data JPA 시작하기
기본적으로 <b>JPA</b> 하나만을 사용하지 않는다. <b>SpringBoot</b>와 <b>Spring Data JPA</b>를 설정 없이 같이 사용한다. ```persistence.xml```, ```EntityManagerFactory```를 자동 설정 처리를 하고 ```Spring Transaction 연동```, ```EntityManager 연동```을 실행하고 사용한다.   

## Spring Data JPA 사용 방법
1. spring-boot-starter-data-jpa을 의존 추가하기   
    * 필요한 설정 자동 처리   
2. Spring Boot 설정하기   
3. Entity 단위로 Repository 인터페이스를 상속 받은 인터페이스 생성하기   
    * or 그 하위 인터페이스 생성   
4. 지정한 규칙에 맞게 메소드 추가하기   
5. 필요한 곳에 해당 인터페이스 타입을 주입해서 사용하기   

### spring-boot-starter-data-jpa을 의존 추가
Maven or Gradle 설정에 spring-boot-starter-data-jpa 의존을 추가할 수 있다.

### Spring Boot 설정
Database의 Driver와 사용할 Database 등을 설정한다. 그러나 스프링 부트 버전에 따라 설정은 달라질 수 있으니 참조해야 한다.

### Entity 단위로 Repository 인터페이스를 상속 받은 인터페이스 생성
* Repository 인터페이스
    * 스프링 데이터 JPA가 제공하는 특별한 타입으로 이 인터페이스를 상속한 인터페이스를 이용해 Bean(빈) 객체를 생성

```
T: Entity 타입
ID: Entity의 식별자 타입

public interface Repository<T, ID> {

}
```

```
public interface UserRepository extends Repository<User, String> {

}
```

### 지정한 규칙에 맞게 메소드 정의
* save(), findById(), delete() 등 규칙에 맞게 메소드 정의

```
public interface UserRepository extends Repository<User, String> {
    Optional<User> findById(String email);

    void save(User user);

    void delete(User user);
}
```

### 필요한 곳에 해당 인터페이스 타입(or Repository)을 주입받아 사용
```
@Service
public class NewUserService {

    private UserRepository userRepository;

    public NewUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional // Transaction 처리
    public void saveUser(SaveRequest saveRequest) {
        Optional<User> userOpt = userRepository.findById(saveRequest.getEmail());

        if (userOpt.isPresent()) throw new DupException();

        User newUser = new User(saveRequest.getEmail(), saveRequest.getName(), LocalDateTime.now());

        userRepository.save(newUser);
    }
}
```

## 정리
정해진 규칙에 따라 인터페이스만 작성하면 된다. 자동으로 처리해주기 때문이다.   

[Spring Data JPA Start](https://www.youtube.com/watch?v=1Q3Qtd5HZy4)