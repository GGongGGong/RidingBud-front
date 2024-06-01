package com.example.ridingbud.ui.riding

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ridingbud.R
import com.example.ridingbud.databinding.ActivityRidingBinding
import com.example.ridingbud.databinding.RidingCompleteDialogBinding
import com.example.ridingbud.model.Course
import com.example.ridingbud.ui.adapter.CoursesMapAdapter
import com.example.ridingbud.viewmodel.MyBookmarksViewModel
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapViewInfo
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.route.RouteLineOptions
import com.kakao.vectormap.route.RouteLinePattern
import com.kakao.vectormap.route.RouteLineSegment
import com.kakao.vectormap.route.RouteLineStyle
import com.kakao.vectormap.route.RouteLineStyles
import com.kakao.vectormap.route.RouteLineStylesSet
import okhttp3.Route
import java.util.Arrays

class RidingActivity : AppCompatActivity() {
    lateinit var binding: ActivityRidingBinding
    private val myBookmarksViewModel: MyBookmarksViewModel by viewModels()
    var courseData = mutableListOf(
        Course(0, "대구시내 근대골목 A코스", "10 km", "1 시간", 5.0, false),
        Course(0, "대구시내 근대골목 B코스", "9 km", "1 시간", 5.0, false),
        Course(0, "신천 금호강 상류 코스", "15 km", "2 시간", 4.5, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRidingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setKakaoMap()
        setAdapter()
        setUi()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.pause()
    }

    private fun setAdapter() {
        // Adapter 설정
        val coursesMapAdapter = CoursesMapAdapter()
        coursesMapAdapter.detailCourseListener =
            object : CoursesMapAdapter.DetailCourseListener {
                override fun onClick(course: Course) {
                    // 코스 자세히 보기로 이동
//                    val intent = Intent(this@MyBookmarkListActivity, )
//                    intent.putExtra(ApplicationClass.COURSE_ITEM, course)
//                    startActivity(intent)
                }
            }
        // RecyclerView 설정
        binding.courseRv.apply {
            layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
            adapter = coursesMapAdapter
        }
        // RecyclerView Item 설정
        coursesMapAdapter.submitList(courseData)
//        myBookmarksViewModel.myCourses.observe(this) {
//            courseAdapter.submitList(it)
//        }
    }

    private fun setUi() {
        // Riding 종료 후 나타나는 Dialog
        val dialogBinding = RidingCompleteDialogBinding.inflate(layoutInflater)
        val dlg = Dialog(this)

        dialogBinding.apply {
            reviewBtn.setOnClickListener {
                // Review 화면으로 이동

                dlg.dismiss()
            }
            homeBtn.setOnClickListener {
                // 홈으로 이동
                finish()
                dlg.dismiss()
            }
        }
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(dialogBinding.root)

        binding.apply {
            // 뒤로가기 버튼 설정
            backBtnBox.setOnClickListener {
                finish()
            }
            backBtn.setOnClickListener {
                finish()
            }
            myLocationBtn.setOnClickListener {

            }
            // 출발지 선택 화면 설정
            startingCourseListBtn.setOnClickListener {
                // 지도의 코스 목록 화면으로 이동

            }
            startingSelectBtn.setOnClickListener {

                startingSelectBox.visibility = View.GONE
                endingSelectBox.visibility = View.VISIBLE
            }
            // 도착지 선택 화면 설정
            moveStartingSelectBtn.setOnClickListener {
                // 출발지 선택 화면으로 이동

            }
            endingSelectBtn.setOnClickListener {

                backBtnBox.visibility = View.GONE
                endingSelectBox.visibility = View.GONE
                ridingInfoBox.visibility = View.VISIBLE
            }
            // 라이딩 중인 화면 설정
            stopBtn.setOnClickListener {
                dlg.show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setKakaoMap() {
        val locationProviderClient =
            LocationServices.getFusedLocationProviderClient(this@RidingActivity)

        // KakaoMap 설정
        binding.apply {
            mapView.start(
                object : MapLifeCycleCallback() {
                    override fun onMapDestroy() {
                        // 지도 API가 정상적으로 종료될 때 호출
                        Log.d("KakaoMap", "onMapDestroy: ")
                    }

                    override fun onMapError(error: Exception?) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출
                        Log.e("KakaoMap", "onMapError: ${error}")
                    }
                },
                object : KakaoMapReadyCallback() {
                    override fun onMapReady(map: KakaoMap) {
                        // 인증 후 API 가 정상적으로 실행될 때 호출됨
                        //setRoute(map = map)
                        // 사용자의 현재 위치 버튼 기능
                        myLocationBtn.setOnClickListener {
                            locationProviderClient.lastLocation
                                .addOnSuccessListener { success: Location? ->
                                    success?.let { location ->
                                        // 사용자의 현재 위치로 이동
                                        map.moveCamera(
                                            CameraUpdateFactory.newCenterPosition(
                                                LatLng.from(
                                                    location.latitude,
                                                    location.longitude
                                                )
                                            )
                                        )
                                    }
                                }
                                .addOnFailureListener { fail ->
                                    Log.d("LocationError", fail.localizedMessage)
                                }
                        }
                    }

                    override fun getZoomLevel(): Int {
                        // 지도 시작 시 확대/축소 줌 레벨 설정
                        return super.getZoomLevel()
                    }

                    override fun getPosition(): LatLng {
                        // 지도 시작 시 위치 좌표를 설정
                        // 대구시 반월당역
                        return LatLng.from(35.865486, 128.593359)
                    }

                    override fun getMapViewInfo(): MapViewInfo {
                        // 지도 시작 시 App 및 MapType 설정
                        return super.getMapViewInfo()
                    }
                })
        }
    }

    private fun setRoute(map: KakaoMap) {
        // 1. RouteLineLayer 가져오기
        val layer = map.routeLineManager?.getLayer()
        // 2. RouteLineStylesSet 생성
        var stylesSet = RouteLineStylesSet.from(
            "blueStyles",
            RouteLineStyles.from(RouteLineStyle.from(16F, Color.BLUE))
        )
        // 3. RouteLineSegment 생성
        // 세그먼트에 스타일 설정을 생략하면, RouteLineStylesSet 의 index 0 번째에 해당되는 스타일로 설정
        var segment = RouteLineSegment.from(
            Arrays.asList(
                LatLng.from(37.338342, 127.092890),
                LatLng.from(37.338605, 127.093759),
                LatLng.from(37.338814, 127.094000),
            )
        ).setStyles(stylesSet.getStyles(0))
        // 4. RouteLineStylesSet 추가 및 RouteLineOptions 생성
        var options = RouteLineOptions.from(segment).setStylesSet(stylesSet)
        // 5. RouteLineLayer에 추가하여 RouteLine 생성
        var routeLine = layer?.addRouteLine(options)
        routeLine?.show()
    }
}

