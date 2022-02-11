import org.kohsuke.github.*;
import java.io.IOException;

public class RepositoryRank {

    public int getPoint(String repositoryName) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository(repositoryName);

        int points = 0;

        if (repository.hasIssues()) {
            points += 1;
        }
        if (repository.getReadme() != null) {
            points += 1;
        }
        if (repository.getPullRequests(GHIssueState.CLOSED).size() > 0) {
            points += 1;
        }

        points += repository.getStargazersCount();
        points += repository.getForksCount();

        return points;
    }

    public static void main(String[] args) {
        RepositoryRank spring = new RepositoryRank();
        int point = spring.getPoint("Hschan2/EverythingAboutJava");
        System.out.println(point);
    }
}

/*
    위의 코드로 테스트 작성을 어떻게 할 수 있을까?
    테스트를 하고자 할 때 getPoint의 첫 번째 줄인 connect()에서 에러가 발생할 것이다.
    매 번 테스트를 할 때마다 GitHub 라이브러리를 사용할 것은 느리고 제한이 발생할 수 있다.
    그래서 위 코드를 테스트를 하게 된다면 아래와 같이 진행할 것이다.
*/

import org.kohsuke.github.*;
import java.io.IOException;

public class RepositoryRank {

    // 인터페이스로 사용할 connect을 선언
    interface GitHubService {
        GitHub connect() throws IOException;
    }

    // GitHubService의 connect을 가져와 재사용한다.
    class DefaultGitHubService implements GitHubService {

        @Override
        public GitHub connect() throws IOException {
            return GitHub.connect();
        }
    }

    // 의존성 주입
    // 테스트를 만들 때 GitHubService를 주입하면 된다.
    // 각자 깃허브 인스턴스를 생성해서 getPoint에 넘겨준다면 테스트가 가능하다.
    private GitHubService gitHubService;

    // 생성자로 GitHubService 데이터를 가져온다. (의존성 주입)
    public RepositoryRank(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public int getPoint(String repositoryName) throws IOException {
        // 각자 깃허브 인스턴스를 받는다.
        GitHub gitHub = gitHubService.connect();
        GHRepository repository = gitHub.getRepository(repositoryName);

        int points = 0;

        if (repository.hasIssues()) {
            points += 1;
        }
        if (repository.getReadme() != null) {
            points += 1;
        }
        if (repository.getPullRequests(GHIssueState.CLOSED).size() > 0) {
            points += 1;
        }

        points += repository.getStargazersCount();
        points += repository.getForksCount();

        return points;
    }

    public static void main(String[] args) {
        RepositoryRank spring = new RepositoryRank();
        int point = spring.getPoint("Hschan2/EverythingAboutJava");
        System.out.println(point);
    }
}

/*
    IoC (Inversion of Control)
        ApplicationContext
        Bean을 만들고 엮어주며 제공한다.
        Controller, Repository가 Bean으로 등록되어 사용된다.
        인텔리제이에서 왼쪽에 초록생 콩 모양이 Bean을 사용한다는 의미
        @Controller, 인터페이스 상속, @Bean으로 직접 등록 등을 사용했을 시 Bean을 사용
        의존성 주입은 Bean끼리만 가능 => 스프링 IoC 컨테이너 안에 들어있는 객체 끼리만 가능

        ***
        등록된 모든 Bean 확인하기

        @Autowired
        ApplicationContext applicationContext;

        @Test
        public void getBean() {
            applicationContext.getBeanDefinitionNames(); // 등록된 모든 Bean의 이름 가져오기
            applicationContext.getBean("Bean 이름"); // 해당 Bean의 내용 가져오기
            applicationContext.getBean(...Controller.class); // Controller는 모두 Bean으로 저장되어 있기 때문에
            assertThat(bean).isNotNull(); // Bean이 있으면 가져오기
        }
        ***

        ***
        위 방법과 다르게 아래 방법도 있지만 이 처럼 ApplicationContext를 직접 사용하지는 않는다.

        private final ApplicationContext applicationContext;
        
        생성자에 applicationContext 등록 (this.applicationContext = applicationContext)

        @GetMapping("/bean")
        @ResponseBody
        public String bean() {
            return "bean: " + applicationContext.getBean(...Controller.class);
        }
        ***

        ***
        위 방법은 아래처럼 쓴다. 왜냐하면 Repository에 이미 Bean이 자동 주입이 되어 있기 때문이다.

        private final AbcRepository Repo;

        @GetMapping("/bean")
        @ResponseBody
        public String bean() {
            return "bean: " + applicationContext.getBean(Repo);
        }
        ***

        강의 - https://www.youtube.com/watch?v=NOAajiABq6A

    PSA (portable service abstraction)
        스프링 웹 MVC가 PSA의 한 부분
            HttpServlet을 사용하여 Mapping하는 것이 아닌 @Controller을 선언하여 Mapping을 자유롭게 사용 가능

        스프링 트랜잭션
            어떤 데이터 베이스의 데이터를 주고 받을 때, 모든 작업이 다 되어야 하나의 작업으로 완료하는 경우를 말한다.
            모든 작업이 되야 성공이고 안 되면 실패로 간주해야 한다.
            예. 쇼핑몰에서 구입했을 때, 돈을 주었는데 물건이 없다면 취소하고 돈을 돌려줘야 한다.
            트랜잭션을 설정(@Transactional)하면 연결, 데이터 값 설정, 커밋 코드를 직접 작성하지 않아도 된다.

        스프링 캐시
        @Cacheable를 사용하여 캐시를 자동으로 사용하도록 한다.

        강의 - https://www.youtube.com/watch?v=P3vzrqADl8I

    의존성 주입 (DI)
*/