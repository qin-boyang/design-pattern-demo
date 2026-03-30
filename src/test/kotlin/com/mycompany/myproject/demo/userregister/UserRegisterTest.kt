package com.mycompany.myproject.demo.userregister

import kotlin.test.Test

class UserRegisterTest {
    @Test
    fun `test register using listener pattern`() { UserService().register("John") }
}