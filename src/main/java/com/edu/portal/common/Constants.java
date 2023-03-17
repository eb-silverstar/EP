package com.edu.portal.common;

public class Constants {

    public static final ApiStatusEntity DEFAULT = new ApiStatusEntity(400, "요청 처리 중 오류가 발생했습니다.", "");
    public static final ApiStatusEntity SUCCESS = new ApiStatusEntity(200, "요청이 정상적으로 처리되었습니다.", "");
    public static final ApiStatusEntity FAILURE = new ApiStatusEntity(400, "요청 처리가 실패했습니다.", "");
    public static final ApiStatusEntity NO_API_PATH = new ApiStatusEntity(400, "요청 API Path가 존재하지 않습니다.", "Rest API 경로를 확인해 주세요.");
    public static final ApiStatusEntity INVALID_USER_LOGIN_ID = new ApiStatusEntity(409, "아이디가 올바르지 않습니다.", "다시 확인해 주세요.");
    public static final ApiStatusEntity INVALID_USER_LOGIN_PWRD = new ApiStatusEntity(409, "비밀번호가 일치하지 않습니다.", "5회 틀릴 경우 계정이 잠깁니다.");
    public static final ApiStatusEntity LOGIN_APRV_CD_NO = new ApiStatusEntity(410, "승인대기 중입니다.", "인증코드 승인완료 후 로그인 시도하세요.");
    public static final ApiStatusEntity LOGIN_USE_LOCK = new ApiStatusEntity(412, "계정이 잠겼습니다.", "비밀번호 찾기를 통해 임시비밀번호를 받으세요.");
    public static final ApiStatusEntity INVALID_AUTHORITY = new ApiStatusEntity(401, "요청 처리 권한이 없습니다.", "관리자에게 문의해 주세요.");
    public static final ApiStatusEntity INVALID_USER = new ApiStatusEntity(401, "유효한 사용자가 아닙니다.", "관리자에게 문의해 주세요.");
    public static final ApiStatusEntity NO_TOKEN = new ApiStatusEntity(401, "API 접근 토큰 정보가 없습니다.", "재 로그인을 해주세요.");
    public static final ApiStatusEntity INVALID_TOKEN = new ApiStatusEntity(401, "API 접근 토큰 정보가 올바르지 않습니다.", "재 로그인을 해주세요.");
    public static final ApiStatusEntity NO_CERTIFICATION = new ApiStatusEntity(401, "인증 정보가 없습니다.", "인증 후 다시 시도해 주세요.");
    public static final ApiStatusEntity INVALID_CERTIFICATION = new ApiStatusEntity(401, "인증 정보가 유효하지 않습니다.", "관리자에게 문의해 주세요.");
    public static final ApiStatusEntity REQUIRE_CERTIFICATION = new ApiStatusEntity(401, "필수 인증 정보가 없습니다.", "업소 아이디를 넣어 주세요.");
    public static final ApiStatusEntity EXPIRE_CERTIFICATION = new ApiStatusEntity(401, "인증 기간이 만료되었습니다.", "관리자에게 문의해 주세요.");
    public static final ApiStatusEntity INVALID_USER_INFO = new ApiStatusEntity(401, "사용자 인증 정보가 올바르지 않습니다.", "인증 정보를 확인해 주세요.");
    public static final ApiStatusEntity NO_USER = new ApiStatusEntity(430, "입력하신 정보를 다시 확인해 주세요.", "");
    public static final ApiStatusEntity NO_DATA = new ApiStatusEntity(450, "요청 정보가 존재하지 않습니다.", "");
    public static final ApiStatusEntity NO_ID = new ApiStatusEntity(451, "아이디가 올바르지 않습니다.", "");
    public static final ApiStatusEntity NO_PWD = new ApiStatusEntity(452, "비밀번호가 올바르지 않습니다.", "");
    public static final ApiStatusEntity ALREADY_DATA = new ApiStatusEntity(453, "동일한 정보가 이미 존재합니다.", "");
    public static final ApiStatusEntity INVALID_PARAMETER = new ApiStatusEntity(453, "요청 정보가 올바르지 않습니다.", "");
    public static final ApiStatusEntity DUPLICATE_DATA = new ApiStatusEntity(460, "동일한 정보가 이미 존재합니다.", "입력 정보 확인 후 다시 시도해 주세요.");
    public static final ApiStatusEntity INVALID_ARGUMENT = new ApiStatusEntity(461, "입력 정보가 올바르지 않습니다.", "입력 정보 확인 후 다시 시도해 주세요.");
    public static final ApiStatusEntity INVALID_ID = new ApiStatusEntity(462, "사용 불가능한 아이디 입니다.", "");
    public static final ApiStatusEntity NO_USER_INFO = new ApiStatusEntity(462, "입력한 아이디와 비밀번호가 일치하지 않습니다.", "아이디 및 비밀번호를 확인해 주세요.");
    public static final ApiStatusEntity INVALID_ID_MASTER = new ApiStatusEntity(462, "이미 등록된 아이디 입니다.", "");
    public static final ApiStatusEntity INVALID_NAME = new ApiStatusEntity(462, "사용 불가능한 닉네임 입니다.", "");
    public static final ApiStatusEntity INVALID_TENANT = new ApiStatusEntity(463, "Tenant 정보가 올바르지 않습니다.", "");
    public static final ApiStatusEntity VALID_ID = new ApiStatusEntity(200, "사용 가능한 아이디 입니다.", "");
    public static final ApiStatusEntity VALID_NAME = new ApiStatusEntity(200, "사용 가능한 닉네임 입니다.", "");
    public static final ApiStatusEntity SUCCESS_LOGIN = new ApiStatusEntity(200, "정상적으로 로그인 되었습니다.", "");
    public static final ApiStatusEntity INVALID_PASSWORD = new ApiStatusEntity(462, "입력 비밀번호가 올바르지 않습니다.", "비밀번호를 확인해 주세요.");
    public static final ApiStatusEntity FAILURE_UPLOAD = new ApiStatusEntity(501, "파일을 업로드하는 동안 오류가 발생되었습니다.", "업로드 재시도 후에도 이상이 있는 경우 관리자에게 문의해 주세요.");

}
