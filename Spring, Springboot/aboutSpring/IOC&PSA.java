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