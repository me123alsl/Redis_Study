# Redis Docker 실행 방법

## 1. Docker Run

```bash
docker run -p "6379:6379" --name my-redis -d redis
```

## 2. Docker Exec Redis-cli

```bash
docker exec -it my-redis redis-cli 
```

## 3. 설정 파일 같이 실행

**RDB 설정을 위한 redis config**

```bash
docker run --network host -v ".\redis_rdb.conf:/conf/redis.conf"  -p "6379:6379" --name my-redis redis redis-server /conf/redis.conf
```

**AOF 설정을 위한 redis config**

```bash
docker run --network host -v ".\redis_aof.conf:/conf/redis.conf"  -p "6379:6379" --name "my-redis" redis redis-server /conf/redis.conf
```

**Replica 노드 redis config**

```bash
docker run --network host -v ".\redis_replica.conf:/conf/redis.conf"  -p "5000:5000" --name "my-redis-replica" redis redis-server /conf/redis.conf
```

**Docker-compose**

```bash
docker-compose up -d
```

### 3-1. snapshot 예시

```bash
# redis.conf
save 60 10
```

```bash
set a 1
set b 2
set c 3
set d 4
set e 5
set f 6
set g 7
set h 8
set i 9
set j 10
set k 11
```

### 3-2. AOF 예시

```bash
# redis-cli
set 1 1
set 2 2
keys *

# bash
cat /data/appendonlydir/appendonly.aof.1.incr.aof | grep -v '\$'| grep -v '*'
```

### 3-3. Replica 예시

```bash
# redis.conf
replicaof 127.0.0.1 6379
```
