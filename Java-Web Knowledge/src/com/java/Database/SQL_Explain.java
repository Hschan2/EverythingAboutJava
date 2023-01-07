package com.java.Database;

public class ExplainClass {

	public static void main(String[] args) {

        // Query Plan (쿼리 플랜)
        // SQL을 처리하는 최저 비용의 경로를 생성해주는 DBMS 내부 해심 엔진, 쿼리 옵티마이저가 쿼리를 수행할 때 생성한 최적의 처리 경로

        // MYSQL
        // 실행할 쿼리문 앞에 'EXPLAIN' 키워드를 이용해 실행 계획에 대한 정보를 살펴볼 수 있다.
        // 이슈가 발생하는 쿼리에 대해 이해할 수 있고, 최적화 방향에 대한 인사이트를 제공한다.

        // 예. EXPLAIN SELECT * FROM short_url su LEFT OUTER JOIN short_url_stat sus ON su.hash = sus.hash WHERE deleted_date IS NULL;
        // 1:N 관계를 가진 테이블을 조인하여 SELECT한 쿼리문 앞에 'EXPLAIN' 키워드 붙여 실행하기
        // 자세한 내용은 출력된 Output에서 확인할 수 있다.
        
        // id: 쿼리 내의 SELECT 문의 실행 순서
        // select_type: SELECT 문의 유형

        // SIMPLE: 단순 SELECT ( UNION이나 서브쿼리를 사용하지 않음 )
        // PRIMARY: 가장 외곽에 있는 SELECT 문
        // UNION: UNION에서의 두번째 혹은 나중에 따라오는 SELECT 문
        // DEPENDENT UNION: UNION에서의 두번째 혹은 나중에 따라오는 SELECT 문, 외곽 쿼리에 의존적이다.
        // UNION RESULT: UNION의 결과물
        // SUBQUERY: 서브쿼리의 첫번째 SELECT
        // DEPENDENT SUBQUERY: 서브쿼리의 첫번째 SELECT, 바깥 쪽 쿼리에 의존적이다.
        // DERIVED: FROM절의 서브쿼리

        // table: 참조하고 있는 테이블명
        // type: 조인타입, 쿼리 성능과 밀접한 항목. 아래 항목들 중에서 밑으로 내려갈수록 좋지 않은 쿼리 형태

        // system: 테이블에 단 하나의 행만 존재(=시스템 테이블). const 조인의 특별한 형태이다.
        // const: 하나의 매치되는 행만 존재하는 경우. 하나의 행이기 때문에 상수로 간주되며, 한번만 읽어들이기 때문에 무척 빠르다.
        // eq_ref: 조인수행을 위해 각 테이블에서 하나의 행만이 읽혀지는 형태. const 타입 외에 가장 훌륭한 조인타입이다.
        // ref: ref조인에서 키의 가장 왼쪽 접두사 만 사용하거나 키가 a PRIMARY KEY또는 UNIQUE인덱스 가 아닌 경우 (즉, 조인이 키 값을 기반으로 단일 행을 선택할 수없는 경우) 사용된다. 사용되는 키가 몇 개의 행과 만 일치하는 경우 이는 좋은 조인 유형이다.
        // fulltext: fulltext 색인을 사용하여 수행된다.
        // ref_or_null: 이 조인 유형은 비슷 ref하지만 MySQL이 NULL값 을 포함하는 행을 추가로 검색한다는 점이 다르다. 이 조인 유형 최적화는 하위 쿼리를 해결하는 데 가장 자주 사용된다.
        // index_merge: 인덱스 병합 최적화가 적용되는 조인타입. 이 경우, key컬럼은 사용된 인덱스의 리스트를 나타내며 key_len 컬럼은 사용된 인덱스중 가장 긴 key명을 나타낸다.
        // range: 인덱스를 사용하여 주어진 범위 내의 행들만 추출된다. key 컬럼은 사용된 인덱스를 나타내고 key_len은 사용된 가장 긴 key부분을 나타낸다. ref 컬럼은 이 타입의 조인에서 NULL 이다. range 타입은 키 컬럼이 상수와 =, <>, >, >=, <, <=, IS NULL, <=>, BETWEEN 또는 IN 연산에 사용될때 적용된다.
        // index: 이 타입은 인덱스가 스캔되는걸 제외하면 ALL과 같다. 보통 인덱스 파일이 데이터 파일보다 작기 때문에 ALL보다 빠르다.
        // ALL: 이전 테이블과의 조인을 위해 풀스캔이 된다. 만약 조인에 쓰인 첫 테이블이 고정이 아니라면 비효율적이다. 그리고 대부분의 경우 아주 느리며, 보통 상수값이나 상수인 컬럼값으로 row를 추출하도록 인덱스를 추가하여 ALL 타입을 피할 수 있다.

        // possible_keys: MySQL이 해당 테이블의 검색에 사용할 수 있는 인덱스들을 나타낸다.
        // key: MySQL이 실제 사용한 key나 인덱스를 나타낸다.
        // key_len: MySQL이 사용한 인덱스의 길이를 나타낸다. key 컬럼의 값이 NULL이면 이 컬럼의 값도 NULL입니다.
        // ref: 행을 추출하는 데 키와 함께 사용 된 컬럼이나 상수값을 나타낸다.
        // rows: 이 값은 쿼리 수행에서 MySQL이 찾아야하는 데이터행 수의 예상값을 나타낸다. 추정 수치이며 항상 정확하지 않다.
        // filtered: filetered열에 나타난 조건에 의해 필터링 될 테이블 행의 예상 비율을 나타낸다. 즉 rows는 검사 된 행 수를 나타내고 rows * filtered / 100은 이전 테이블과 조인 될 행 수를 표시한다.
        // Extra: MySQL이 이 쿼리를 어떻게 해석하는 지에 대한 추가 정보가 들어있다.

        // BUT, SQL 문이 다수일경우 매번 명령을 수행해야하는 불편함이 존재
        // 데이터를 처리하는 행동이 아니기 때문에 I/O 관ㄹㄴ 시간 측정도 불가능 (SORTING 포함)
        
        // Set autotrace
        // 한 번의 명령으로 여러 개의 SQL 문에 대한 실행 계획 확인하기
        // 다양한 옵션을 사용하여 여러 가지의 정보를 선택적으로 확인 가능
        
        // SET AUTOTRACE ON;
        // SELECT e.name, e.deptno, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno;

        // AUTOTRACE 옵션
        // SET AUTOTRACE ON EXPLAIN;
        // 출력 결과와 실행 계획까지만 나타내고 통계 정보는 생략
        
        // SET AUTOTRACE ON STATISTICS;
        // 출력 결과와 실행 계획을 생략하고 I/O관련 정보를 선택적으로 보여줌

        // SET AUTOTRACE ON TRACEONLY;
        // 데이터가 상당히 클 때 사용하는데 출력 결과를 나타내지 않음

        // SET AUTOTRACE ON TRACEONLY EXPLAIN;
        // 데이터를 처리하지 않고 실행계획만을 보여줌 (많이 사용함)

        // SET AUTOTRACE ON TRACEONLY STATISTICS;
        // 데이터를 처리하지 않고 I/O관련 정보를 보여줌

        // SET AUTOTRACE ON OFF;
        // 사용하지 않을 때 사용

    }
}