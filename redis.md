# Redis Basic

- [Redis Basic](#redis-basic)
  - [Run Redis in docker](#run-redis-in-docker)
  - [Redis Module](#redis-module)
  - [Redis Command](#redis-command)
  - [Redis Data Type](#redis-data-type)
    - [*Strings*](#strings)
    - [*Lists*](#lists)
    - [*Sets*](#sets)
    - [*Hashes*](#hashes)
    - [*Sorted Sets*](#sorted-sets)
    - [*Bitmaps*](#bitmaps)
    - [*HyperLogLog*](#hyperloglog)

## Run Redis in docker

```bash
# pull redis image 
docker pull redis

# docker run as redis server
docker run redis

# docker run as redis server with options
# --name : container name
# -d : run as daemon
# -p : port mapping
docker run -p "6379:6379" --name my-redis -d redis
```

## Redis Module

- redis-server : Redis 서버
- redis-cli : Redis Command Line Interface (명령어 수행)

## Redis Command

``` bash
# In docker container
docker exec -it my-redis /bin/sh

# run redis-cli
redis-cli
```

```bash (in redis-cli)
# in redis-cli
# create data (key1:banana)
set key1 banana
# show data
get key1
# not exist key
get key2

# show all key (expression)
keys *
# show db size
dbsize
# all key delete
flushall
```

## Redis Data Type

### *Strings*

- 기본적인 key-value 형태의 데이터 타입
- 바이트 배열을 저장
- 바이너리로 변환할 수 있는 모든 데이터를 저장할 수 있음
- 문자열, 숫자, JSON, XML 등을 저장할 수 있음
- 최대 512MB까지 저장 가능

|명령어|설명|사용법|
|:---:|:---:|:---:|
|SET|데이터 저장|SET key value|
|GET|데이터 조회|GET key|
|INCR|숫자 데이터 증가|INCR key|
|DECR|숫자 데이터 감소|DECR key|
|MSET|여러 데이터 저장|MSET key1 value1 key2 value2 ...|
|MGET|여러 데이터 조회|MGET key1 key2 ...|

```bash (in redis-cli)
#in redis-cli
set myname "hong gil dong"
get myname
# myname = hong gil dong

set mycount 1
get mycount
# mycount = 1
incr mycount
get mycount
# mycount = 2
decr mycount
get mycount
# mycount = 1

mget myname mycount
# 1) "hong gil dong"
# 2) "1"
```

### *Lists*

- Linked-list 형태의 자료구조
- Queue, Stack으로 사용 가능

|명령어|설명|사용법|
|:---:|:---:|:---:|
|LPUSH|리스트의 왼쪽에 데이터 추가|LPUSH key value|
|RPUSH|리스트의 오른쪽에 데이터 추가|RPUSH key value|
|LLEN|리스트의 길이 조회|LLEN key|
|LRANGE|리스트의 범위 반환|LRANGE key start end|
|LPOP|리스트의 첫번째 데이터 반환 및 삭제|LPOP key|
|RPOP|리스트의 마지막 데이터 반환 및 삭제|RPOP key|

```bash (in redis-cli)
#in redis-cli
lpush fruits apple
rpush fruits orange
lpush fruits banana

llen fruits

lrange fruits 0 -1

lpop fruits
rpop fruits
```

### *Sets*

- 순서가 없는 유니크 값의 집합
- 중복된 값을 저장할 수 없음
- 검색이 빠름
- 교집합, 합집합, 차집합 등의 집합 연산 가능

|명령어|설명|사용법|
|:---:|:---:|:---:|
|SADD|Set에 데이터 추가|SADD key value|
|SREM|Set의 데이터 삭제|SREM key value|
|SCARD|Set의 길이 조회|SCARD key|
|SMEMBERS|Set의 모든 데이터 조회|SMEMBERS key|
|SISMEMBER|Set에 데이터 존재 여부 확인|SISMEMBER key value|

```bash (in redis-cli)
SADD myset apple
SADD myset banana
SMEMBERS myset
SCARD myset

SREM myset banana
SMEMBERS myset
SCARD myset

SISMEMBER myset apple
SISMEMBER myset banana
```

### *Hashes*

- 하나의 KEy 하위에 여러개의 field와 value를 저장
- 여러 필드를 가진 객체를 저장하는것
- field는 string, value는 string 또는 number
- `HINCYBY`로 카운터 역할로 사용 가능
  
|명령어|설명|사용법|
|:---:|:---:|:---:|
|HSET|Hash에 데이터 저장|HSET key field value|
|HGET|Hash에 데이터 조회|HGET key field|
|HMGET |Hash에 여러 데이터 조회|HMGET key field1 field2 ...|
|HGETALL|Hash의 모든 데이터 조회|HGETALL key|
|HDEL|Hash의 데이터 삭제|HDEL key field|
|HINCRBY|Hash의 데이터 증가|HINCRBY key field increment|

```bash (in redis-cli)
# user1이라는 hash에 name, age 저장
HSET user1 name bear age 10
HGET user1 name
# "bear"
HGET user1 age
# "10"
HMGET user1 name age
# 1) "bear"
# 2) "10"

HSET user viewcnt 10
# (integer) 1
HINCRBY user viewcnt 3
# (integer) 13
HGET user viewcnt
# "13"
```

### *Sorted Sets*

- Set과 유사하지만, 데이터마다 score가 존재
- 각 값은 연관된 score를 가지고 정렬되어 있음
- 정렬된 상태이기에 최소/최대값 검색이 빠름
- 순위 계산, 리더보드 구현등에 용이

|명령어|설명|사용법|
|:---:|:---|:---|
|ZADD|Sorted Set에 데이터 저장|ZADD key score value|
|ZRANGE|Sorted Set의 데이터 조회|ZRANGE key start end|
|ZREVRANGE|Sorted Set의 데이터 역순 조회|ZREVRANGE key start end|
|ZRANK|Sorted Set의 데이터 순위 조회|ZRANK key value|
|ZREVRANK|Sorted Set의 데이터 역순 순위 조회|ZREVRANK key value|
|ZREM|Sorted Set의 데이터 삭제|ZREM key value|
|ZCARD|Sorted Set의 길이 조회|ZCARD key|
|ZCOUNT|Sorted Set의 score 범위 내 데이터 개수 조회|ZCOUNT key min max|
|ZINCRBY|Sorted Set의 데이터 증가|ZINCRBY key increment value|

```bash (in redis-cli)
# apple(10), banana(20), grape(30)을 score와 함께 저장
ZADD myrank 10 apple 20 banana 30 grape

# myrank의 0~1번째 데이터 조회
ZRANGE myrank 0 1
# 1) "apple"
# 2) "banana"

ZRANK myrank apple
# (integer) 0

ZREM myrank apple
ZRANK myrank banana
# (integer) 0
```

### *Bitmaps*

- 0과 1로 이루어진 이진 데이터를 저장
- 비트 벡터를 사용해 N개의 Set을 공간 효율적으로 저장
- 하나의 비트맵이 가지는 공간은 2^32-1(4,294,967,295)
- 비트 연산을 통해 AND, OR, XOR, NOT 등의 연산 가능

|명령어|설명|사용법|
|:---:|:---|:---|
|SETBIT|Bitmap에 데이터 저장|SETBIT key offset value|
|GETBIT|Bitmap의 데이터 조회|GETBIT key offset|
|BITCOUNT|Bitmap의 데이터 개수 조회|BITCOUNT key [start end]|
|BITOP|Bitmap의 데이터 연산|BITOP operation destkey key [key ...]|

```bash (in redis-cli)
SETBIT today_visit 2 1
SETBIT today_visit 3 1
SETBIT today_visit 4 1

GETBIT today_visit 2
# (integer) 1

BITCOUNT today_visit
# (integer) 3

SETBIT yesterday_visit 2 1
SETBIT yesterday_visit 3 1

GETBIT yesterday_visit 4
# (integer) 0

BITCOUNT yesterday_visit
# (integer) 2


BITOP AND result yesterday_visit today_visit
# (integer) 2
```

### *HyperLogLog*

- 대용량의 고유 데이터를 세기 위해 사용
- 유니크한 값의 개수를 효율적으로 얻을수 있음
- 확률적 자료구조로서 오차가 있으며, 매우 큰 데이터를 다룰때 사용
- 18,446,744,073,709,551,616개(2^64) 의 고유한 요소를 저장 가능
- 12KB까지 메모리를 사용하며, 0.81%의 오차율을 가짐
- 보통 카운터 용도로 사용

|명령어|설명|사용법|
|:---:|:---|:---|
|PFADD|HyperLogLog에 데이터 저장|PFADD key element [element ...]|
|PFCOUNT|HyperLogLog의 데이터 개수 조회|PFCOUNT key [key ...]|
|PFMERGE|HyperLogLog의 데이터 병합|PFMERGE destkey sourcekey [sourcekey ...]|

```bash (in redis-cli)
PFADD today_visits Kim Lee Park
# (integer) 1

PFADD today_visits Kim
# (integer) 0 --> 중복을 허용하지 않음

PFADD yesterday_visits Kim Lee
# (integer) 1

PFMERGE total_visits today_visits yesterday_visits
# OK

PFCOUNT total_visits
```
