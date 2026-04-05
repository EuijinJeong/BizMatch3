# BizMatch - 중소기업 간 B2B 외주 매칭 플랫폼

> 중소기업 간 외주 프로젝트 등록, 매칭, 결제까지 한 번에 처리하는 B2B 협업 플랫폼

<br>

## 📌 프로젝트 개요

중소기업의 인력난과 외주 수요 증가를 배경으로, 기업 간 신뢰 기반의 외주 매칭 환경을 제공하는 웹 서비스입니다.  
사업자 검증 시스템, 프로젝트 매칭, 결제, 리뷰, 실시간 채팅 기능을 포함합니다.

- **개발 기간**: 2024.07 ~ 2024.12
- **참여 인원**: 4명 (본인: PM 및 백엔드 개발)
- **배포 환경**: AWS EC2

<br>

## 🛠 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.3.4, Spring Security |
| Auth | JWT (jjwt 0.12.6) |
| ORM | MyBatis 3.0.3 |
| Database | Oracle |
| View | JSP, JSTL |
| Real-time | WebSocket |
| Build | Maven |
| Deploy | AWS EC2 |
| Etc | Spring Mail, Jsoup (XSS 방어) |

<br>

## 📂 프로젝트 구조

```
src/main/java/com/ktdsuniversity/edu/bizmatch/
├── member/         # 회원 가입·로그인·마이페이지 (기업/프리랜서)
├── project/        # 프로젝트 등록·검색·지원
│   └── review/     # 리뷰·평점
├── payment/        # 결제·에스크로·환불
├── board/          # 커뮤니티 게시판
├── comment/        # 댓글
├── file/           # 파일 업로드·다운로드
├── report/         # 신고
├── admin/          # 관리자 (회원·프로젝트·신고 관리)
├── ws/             # WebSocket 실시간 채팅
└── common/
    ├── security/   # Spring Security + JWT 인증 필터
    ├── exceptions/ # 도메인별 커스텀 예외 + GlobalExceptionHandler
    ├── beans/      # Interceptor, FileHandler, WebConfig
    └── utils/      # XSS 방어, 파라미터 유효성 검사
```

<br>

## ✨ 주요 기능

**회원**
- 기업/프리랜서 유형별 회원가입 및 로그인
- Spring Security + JWT 기반 인증·인가
- 이메일 인증, 비밀번호 재설정
- 마이페이지 (포트폴리오, 보유 기술 관리)

**프로젝트**
- 프로젝트 등록·수정·삭제·검색 (카테고리/키워드 필터)
- 프로젝트 지원 및 파일 첨부
- 스크랩 기능

**결제**
- 에스크로 방식 결제 (착수금·잔금 분리)
- 환불 처리

**리뷰·신고**
- 거래 완료 후 리뷰·평점 등록
- 부적절 리뷰 신고 및 관리자 처리

**실시간 채팅**
- WebSocket 기반 1:1 채팅

**관리자**
- 회원 승인·정지, 프로젝트 관리, 신고 처리

<br>

## 🔐 인증 흐름

```
클라이언트 요청
    └─→ JsonWebTokenAuthenticationFilter (JWT 검증)
            └─→ SecurityUserDetailsService (사용자 조회)
                    └─→ SecurityAuthenticationProvider (인증 처리)
                            └─→ Controller 진입
```

- 비로그인 사용자는 `LoginSessionInterceptor`로 접근 제한
- 프리랜서 전용 페이지는 `FreelancerAccessInterceptor`로 별도 제어

<br>

## ⚙️ 실행 방법

```bash
# 1. 레포지토리 클론
git clone https://github.com/EuijinJeong/BizMatch3.git
cd BizMatch3

# 2. application.properties 설정 (DB, Mail, JWT Secret 등)
# src/main/resources/application.properties 참고

# 3. 빌드 및 실행
./mvnw spring-boot:run
```

> Oracle DB 연결 정보 및 JWT Secret Key는 환경변수 또는 별도 설정 파일로 관리해주세요.

<br>

