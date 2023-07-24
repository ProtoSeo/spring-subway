# 지하철 노선도

## 진행 방법

* 지하철 노선도 요구 사항을 파악한다.
* 요구 사항에 대한 구현을 완료한 후 자신의 Github 아이디에 해당하는 브랜치에 Pull request를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 Push한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 페어 프로그래밍

### 목표

* 서승훈: 즐기면서 하자
* 최정규: 모든걸 의심하자

### 규칙

* 잡담을 많이 하자
* 시간적 압박 등의 부담 없이 하자
* 너무 완벽하려고 하지 말자
* 고민이 길어지면 가위바위보로 정하자

## 용어 사전

| 한글명 | 영문      |
|-----|---------|
| 역   | Station |
| 구간  | Section |
| 노선  | Line    |

## 요구 사항

### 1단계

- [x] 구간에 대한 도메인을 정의한다.
    - [x] 구간은 **상행 역의 정보**와 **하행 역의 정보**를 갖는다.
    - [x]  상행 역과 하행 역 중 하나 이상 없을 경우 정의할 수 없다.
    - [x] 상행 역과 하행 역 사이의 **길이 정보**를 갖는다.
- [x] 구간 등록 기능
    - [x] 노선에 새로운 구간을 등록할 수 있다. 단, 아래 조건을 만족해야한다. 그렇지 않으면 예외를 던진다.
        - [x] 새로운 구간의 상행역은 해당 노선에 등록되어 있는 하행 종점역이어야 한다.
        - [x] 새로운 구간의 하행역은 해당 노선에 등록되어 있는 역일 수 없다.
- [x] 구간 제거 기능
    - [x] 노선에 존재하는 구간을 제거할 수 있다. 단, 아래 조건을 만족해야한다. 그렇지 않으면 예외를 던진다.
        - [x] 지하철 노선에 등록된 하행 종점역만 제거할 수 있다.
        - [x] 지하철 노선에 구간이 하나인 경우 역을 제거할 수 없다.

### 2단계

- [x] 구간 추가 기능 심화
    - [x] 노선 조회 시 상행 종점역부터 하행 종점역까지 순서대로 응답한다.
    - [x] 역 사이에 새로운 역을 등록할 수 있다.
        - [x] 요청의 상행역과 노선에 속한 구간의 상행역이 같고, 요청의 하행역이 해당 노선에 존재하지 않는 경우 해당 구간의 사이에 등록한다.
        - [x] 요청의 하행역과 노선에 속한 구간의 하행역이 같고, 요청의 상행역이 해당 노선에 존재하지 않는 경우 해당 구간의 사이에 등록한다.
    - [x] 새로운 역을 상행 종점역으로 등록할 수 있다.
        - [x] 요청의 하행역과 노선의 상행 종점역이 같고, 요청의 상행역이 해당 노선에 존재하지 않는 경우, 상행 종점역으로 등록한다.
    - [x] 새로운 역을 하행 종점역으로 등록할 수 있다.
        - [x] 요청의 상행역과 노선의 하행 종점역이 같고, 요청의 하행역이 해당 노선에 존재하지 않는 경우, 하행 종점역으로 등록한다.
    - [x] 예외 케이스
        - [x] 역 사이에 새로운 역을 등록할 경우 기존 역 사이 길이보다 크거나 같으면 등록할 수 없다.
        - [x] 상행 역과 하행 역이 이미 노선에 모두 등록되어 있다면 추가할 수 없다.
        - [x] 상행 역과 하행 역 둘 다 포함되어 있지 않으면 추가할 수 없다.