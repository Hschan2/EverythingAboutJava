# JPA, Entity CRUD 처리

## 보조 클래스 생성
* EntityManager를 쉽게 구하는 방법을 구현한다.   
```
public void EMF {
    
    private static EntityManagerFactory emf;

    public static void init() {
        emf = Persistence.createEntityManagerFactory("jpabegin");
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
```

## Entity 단위 CRUD 처리를 위한 EntityManager가 제공하는 메소드 이용
* persist()
* find()
* remove()
* merge()

## 저장 단계
* EntityManager#persist (Object entity)
```
public class NewUserService {
    public void saveNewUser(User user) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            ex.close();
        }
    }
}
```

## 조회 단계
* EntityManager#find (Class<T> entityClass, Object primaryKey)
```
public class GetUserService {
    public User getUser(String email) {
        EntityManager em = EMF.createEntityManager();

        try {
            User user = em.find(User.class, email);
            if (user == null) {
                throw new NoUserException();
            }

            return user;
        } finally {
            ex.close();
        }
    }
}
```

만약 Entity Type, ID Type이 맞아야 한다. 일치하지 않으면 Exception을 발생한다.   

```
String str = em.find(String.class, "1");
User user = em.find(User.class, 11);
```

위 코드에서 <b>String.class</b> 부분과 <b>11</b> 부분은 타입이 맞지 않기 때문에 Exception 처리한다.   

## 수정 단계
* 트랜잭션 범위 내에서 변경된 값을 자동으로 반영한다.   

```
public class ChangeNameService {
    public void changeName(String email, String newName) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            User user = em.find(User.class, email);
            if (user == null) {
                throw new NoUserException();
            }
            user.changeName(newName);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            ex.close();
        }
    }
}
```

## 삭제 단계
* EntityManager#remove (Object entity)
```
public class RemoveUserService {
    public void removeUser(String email) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            User user = em.find(User.class, email);
            if (user == null) {
                throw new NoUserException();
            }
            user.remove(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            ex.close();
        }
    }
}
```

만약 삭제할 대상이 존재하지 않는다면

```
user.remove(user);
tx.commit();
```

이 시점에 다른 프로세스가 데이터를 삭제하면 Exception이 발생한다.   

main에서 테스트한다면 아래처럼 사용할 수 있다.   
```
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static NewUserService newUserService = new NewUserService();
    private static GetUserService getUserService = new GetUserService();
    private static ChangeNameService changeNameService = new ChangeNameService();
    private static RemoveUserService removeUserService = new RemoveUserService();

    public static void main(String[] args) throws IOException {
        EMF.init();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("명령어 입력");
                String line = reader.readLine();
                if (line == null) break;

                if (line.startsWith("new ")) {
                    handleNew(line);
                } else if (line.startsWith("get ")) {
                    handleGet(line);
                } else if (line.startsWith("change name ")) {
                    handleChangeName(line);
                } else if (line.startsWith("remove ")) {
                    handleRemove(line);
                } else if (line.equals("exit")) {
                    break;
                }
            }
        } finally {
            EMF.close();
        }
    }

    private static void handleNew(String line) {
        String[] v = line.substring(4).split(" ");
        User u = new User(v[0], v[1], LocalDateTime.now());
        try {
            newUserService.saveNewUser(u);
            logger.info("새 사용자 저장: {}", u);
        } catch (EntityExistsException e) {
            logger.info("사용자가 이미 존재함: {}", u.getEmail());
        }
    }

    private static void handleGet(String line) {
        String email = line.substring(4);
        try {
            User user = getUserService.getUser(email);
            logger.info("사용자 정보: {}", user);
        } catch (NoUserException e) {
            logger.info("사용자가 존재하지 않음: {}", email);
        }
    }

    private static void handleChangeName(String line) {
        String[] v = line.substring(12).split(" ");
        String email = v[0];
        String newName = v[1];
        try {
            changeNameService.changeName(email, newName);
            logger.info("사용자 이름 변경: {}, {}", email, newName);
        } catch (NoUserException e) {
            logger.info("사용자가 존재하지 않음: {}", email);
        }
    }

    private static void handleRemove(String line) {
        String email = line.substring(7);
        try {
            removeUserService.removeUser(email);
            logger.info("사용자 삭제함: {}", email);
        } catch (NoUserException e) {
            logger.info("사용자가 존재하지 않음: {}", email);
        }
    }
}
```

## 정리
* EntityManager를 사용해 Entity 단위로 CRUD 처리를 할 수 있다.
* 변경은 트랜잭션 범위 안에서 실행한다.
    * persist()
    * 수정
    * remove()   

[Entity CRUD 처리](https://www.youtube.com/watch?v=kmCKAwOie_I)