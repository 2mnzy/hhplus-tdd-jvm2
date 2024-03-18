package io.hhplus.tdd.point

import org.apache.catalina.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import io.hhplus.tdd.database.UserPointTable
import io.hhplus.tdd.database.PointHistoryTable

@RestController
@RequestMapping("/point")
class PointController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val usertable= UserPointTable()
    private val historytable= PointHistoryTable()
    /**
     * TODO - 특 정 유저의 포인트를 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}")
    fun point(
        @PathVariable id: Long,
    ): UserPoint {
        return usertable.selectById(id)
        //return UserPoint(id = id, 0, 0)
    }

    /**
     * TODO - 특정 유저의 포인트 충전/이용 내역을 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}/histories")
    fun history(
        @PathVariable id: Long,
    ): List<PointHistory> {
        return historytable.selectAllByUserId(id)
    }

    /**
     * TODO - 특정 유저의 포인트를 충전하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/charge")
    fun charge(
        @PathVariable id: Long,
        @RequestBody amount: Long,
    ): UserPoint {

        return UserPoint(0,  point(id).point + amount, 0)
    }

    /**
     * TODO - 특정 유저의 포인트를 사용하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/use")
    fun use(
        @PathVariable id: Long,
        @RequestBody amount: Long,
    ): UserPoint {
        return UserPoint(0, point(id).point - amount, 0)
    }
}