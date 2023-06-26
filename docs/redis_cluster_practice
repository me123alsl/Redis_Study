# Redis Cluster 구성 실습

- [Redis Cluster 구성 실습](#redis-cluster-구성-실습)
  - [클러스터 설정 파일](#클러스터-설정-파일)
  - [클러스터 구성해보기](#클러스터-구성해보기)
    - [설정파일 수정](#설정파일-수정)

---

## 클러스터 설정 파일

- `cluster-enabled` <yes/no>
  - **클러스터 모드로 실행 여부**
- `cluster-config-file` \<filename>
  - **클러스터 정보를 저장할 파일**
  - `사용자가 수정하지 않음`
- `cluster-node-timeout` \<milliseconds>
  - **특정 노드가 정상인지 판단하는 기준 시간**
  - 이 시간동안 감지되지 않은 master는 replica에 의해 failover
- `cluster-replica-validity-factor` \<factor>
  - **master와 replica의 동기화 지연 시간**
  - master와 replica의 동기화 지연 시간이 이 값보다 크면 failover가 발생
  - (cluste-node-timeout) * (cluster-replica-validity-factor) 만큼 master와 통신이 없으면 replica는 failover 대상에서 제외
- `cluster-migration-barrier` \<count>
  - **한 master가 유지해야 하는 최소 replica 수**
- `cluster-require-full-coverage` <yes/no>
  - **일부 hash slot이 커버되지 않을 때, write 요청을 받을지 여부**
  - no로 설정하면 일부 노드에 문제가 생겨 hash slot이 정상작동하지 않더라도 다른 hash slot에 대해서는 작동할 수 있음
- `cluster-allow-reads-when-down` <yes/no>
  - **일부 노드에 문제가 생겨도 read 요청을 받을지 여부**
  - 어플리케이션에서 read 동작의 consistency가 중요하지 않은 경우 yes로 설정

---

## 클러스터 구성해보기

### 설정파일 수정

``` ini

```