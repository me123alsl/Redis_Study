# Redis Cluster의 제약 사항

- [Redis Cluster의 제약 사항](#redis-cluster의-제약-사항)
  - [***Redis Cluster의 제약 사항***](#redis-cluster의-제약-사항-1)
    - [***클러스터는 DB0만 사용가능***](#클러스터는-db0만-사용가능)
    - [***Multi key Operation 사용의 제약***](#multi-key-operation-사용의-제약)
    - [***클라이언트 구현의 강제***](#클라이언트-구현의-강제)
  
---

## ***Redis Cluster의 제약 사항***

### ***클러스터는 DB0만 사용가능***

- Redis는 한 인스턴스에 여러 데이터베이스를 가질수 있으며, 기본적으로 16개의 데이터베이스를 가짐
  - => 설정) database 16
- Multi DB는 용도별로 분리해서 관리를 용이하기 위함이 목적
- 클러스터는 해당 기능을 사용할 수 없고, DB0으로 고정

### ***Multi key Operation 사용의 제약***

- `key들이 각각 다른 노드에 저장`되므로 MESET과 같은 `Multi-key operation은 기본적으로 사용할 수 없음`
- `같은 노드 안에 속한 key들에` 대해서는 `multi-key operation 가능`
- hash tags 기능을 사용하면 여러 key들을 같은 hash slot에 속하게 할 수 있음
  - => key 값 중 {}안에 들어간 문자열에 대해서만 사용 가능
  - => 예시) `MSET {user:a}:aget 20 {user:a}:city seoul`

### ***클라이언트 구현의 강제***

- 클라이언트는 클러스터의 모든 노드에 접속해야함
- 클라이언트는 redirect 기능을 구현해야함 (MOVED 에러 대응)
- 클라이언트 구현이 잘 된 라이브러리가 없는 환경도 있을 수 있음
