# 프로젝트 개발 규칙

## 1. 보안 (Security)

- **CORS 설정**: 모든 Cross-Origin 요청을 처리할 수 있도록, `SecurityConfig` 파일 내에 CORS 정책을 설정하고 필터를 적용해야 합니다.
- **코드 정리**: 만약 별도의 `FilterConfig`나 `CorsFilter` 클래스가 존재한다면, `SecurityConfig`에 통합한 후 해당 중복 파일들을 삭제하여 코드를 정리해야 합니다.

## 2. 기능 구현 (Feature Implementation)

- **회원 정보 조회**:
  - **목표**: 회원 ID로 특정 사용자의 정보를 조회하는 기능을 구현합니다.
  - **API 엔드포인트**: `GET /api/users/{id}` (RESTful API 규칙 준수)
  - **구현 위치**: `UserController` 및 `UserService`

## 3. 코딩 컨벤션 (Coding Convention)

- **Controller 패턴**: `UserController`의 메서드를 작성할 때, `AuthController`의 구현 패턴(예: `ResponseEntity` 반환, `Resp.ok()` 사용)을 정확히 따라야 합니다.
- **DTO**: 모든 Data Transfer Object(DTO) 클래스에는 Lombok의 `@Data` 어노테이션을 사용하여 getter, setter, `toString()` 등을 자동으로 생성해야 합니다.
- **주석**: 새로 추가되거나 수정된 모든 소스코드에는 다른 개발자가 쉽게 이해할 수 있도록 친절하고 상세한 주석을 반드시 추가해야 합니다.
- **코드 삭제**: 기능이 수정되거나 대체되어 더 이상 필요 없게 된 코드는 반드시 프로젝트에서 삭제하여 최신 상태를 유지해야 합니다.
