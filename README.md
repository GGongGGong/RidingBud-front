# RidingBud-Client
지역 사회의 자전거 라이딩의 활성화 프로젝트 `RidingBud`의 클라이언트 Repository 입니다.
## 소개
> **라이딩벗**와 함꼐하는 슬기로운 에코생활

라이딩벗은 지역 내 자전거 코스 데이터를 이용하여 지역 주민의 건강 증진과 지역 내 자전거 코스의 활성화를 위한 서비스이며, 나아가 지역의 환경 보전을 위한 건강한 생활을 형성하기 위한 서비스입니다.

## 기술 스택

<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"> <img src="https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=Android%20Studio&logoColor=white"> <img src="https://img.shields.io/badge/Retrofit-D20A0A?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/OkHttp-2C5BB4?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/Glide-007054?style=for-the-badge&logoColor=white">

## 시스템 아키텍쳐


## 사용 방법

### Git Clone 실행
```shell
$ git clone https://github.com/GGongGGong/RidingBud-front.git
```

### Kakao 지도 설정

#### 프로젝트의 디버그 키 해시값 찾기
- MainActivity.kt의 클래스에 아래 코드 추가 (없으면)
- Logcat에서 KeyHash를 입력하고 프로젝트의 디버그 키 해시값 찾기
- 찾은 해시값을 Contributor에게 전달 및 Kakao Developers에 해시값 등록 요청
```kotlin
private fun getHashKey() {
    var packageInfo: PackageInfo? = null
    try {
        packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    if (packageInfo == null)
        Log.e("KeyHash", "KeyHash:null")
    for (signature: Signature in packageInfo!!.signatures) {
        try {
            var md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        } catch (e: NoSuchAlgorithmException) {
            Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e)
        }
    }
}
```

## Contributors
|Name|Github|Email|
|:---|:---|:---|
|이지수|[Jiphago](https://github.com/Jiphago)|
|허진수|[HJTN](https://github.com/HJTN)|
