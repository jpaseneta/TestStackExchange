package com.example.myapplication.data

import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeService {
    @GET("2.3/users")
    suspend fun getUsers(
        @Query("site") site: String = "stackoverflow",
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "reputation",
        @Query(value = "pagesize") pageSize: String = "20"
    ): UserListResponse

    /**
     * Hi! As per instructions it was indicated to display the list by names alphabetically ascendingly
     * However, running that through the api with
     *
     * /2.3/users?order=asc&sort=name&site=stackoverflow
     *
     * produces users with Russian/Arabic/Symbolized names such as:
     *
     * {
     *       "badge_counts": {
     *         "bronze": 0,
     *         "silver": 0,
     *         "gold": 0
     *       },
     *       "account_id": 44574884,
     *       "is_employee": false,
     *       "last_access_date": 1762385106,
     *       "reputation_change_year": 0,
     *       "reputation_change_quarter": 0,
     *       "reputation_change_month": 0,
     *       "reputation_change_week": 0,
     *       "reputation_change_day": 0,
     *       "reputation": 1,
     *       "creation_date": 1762385106,
     *       "user_type": "registered",
     *       "user_id": 31823746,
     *       "link": "https://stackoverflow.com/users/31823746/%d9%8e%d9%91%d8%b3%d9%80%d9%84%d9%8e%d9%85%d9%8e%d8%a3%d9%91%d9%86%d9%90-%d8%a7%d9%84%d8%b4%d9%85%d8%b1%d9%8a",
     *       "profile_image": "https://www.gravatar.com/avatar/e51bb84e338cbeb1429be31ab11fd85f?s=256&d=identicon&r=PG&f=y&so-version=2",
     *       "display_name": "َّسـلَمَأّنِ الشمري"
     *     },
     *     {
     *       "badge_counts": {
     *         "bronze": 8,
     *         "silver": 1,
     *         "gold": 0
     *       },
     *       "account_id": 433288,
     *       "is_employee": false,
     *       "last_modified_date": 1667952300,
     *       "last_access_date": 1678367812,
     *       "reputation_change_year": 0,
     *       "reputation_change_quarter": 0,
     *       "reputation_change_month": 0,
     *       "reputation_change_week": 0,
     *       "reputation_change_day": 0,
     *       "reputation": 35,
     *       "creation_date": 1309238312,
     *       "user_type": "registered",
     *       "user_id": 818544,
     *       "accept_rate": 50,
     *       "location": "Thai",
     *       "website_url": "",
     *       "link": "https://stackoverflow.com/users/818544/%e1%b4%ba%e1%b4%b1%e1%b4%bc%e1%b4%ba",
     *       "profile_image": "https://www.gravatar.com/avatar/894891fa86576454f1cab28c28625425?s=256&d=identicon&r=PG",
     *       "display_name": "ᴺᴱᴼᴺ"
     *     },
     *     {
     *       "badge_counts": {
     *         "bronze": 1,
     *         "silver": 0,
     *         "gold": 0
     *       },
     *       "account_id": 1807985,
     *       "is_employee": false,
     *       "last_modified_date": 1573682110,
     *       "last_access_date": 1346846006,
     *       "reputation_change_year": 0,
     *       "reputation_change_quarter": 0,
     *       "reputation_change_month": 0,
     *       "reputation_change_week": 0,
     *       "reputation_change_day": 0,
     *       "reputation": 21,
     *       "creation_date": 1346708248,
     *       "user_type": "registered",
     *       "user_id": 1644666,
     *       "link": "https://stackoverflow.com/users/1644666/%e1%8b%88%e1%8a%95%e1%8b%b5%e1%8b%88%e1%88%b0%e1%8a%95-%e1%8a%a5%e1%88%ad%e1%8c%88%e1%8c%a4",
     *       "profile_image": "https://www.gravatar.com/avatar/3314c6781ca9db3eed19951546f9be4f?s=256&d=identicon&r=PG",
     *       "display_name": "ወንድወሰን እርገጤ"
     *     }
     *
     *  so it is better to use their recommended default /users sorting which is:
     *  2.3/users?order=desc&sort=reputation&site=stackoverflow
     */

}
