package com.jiwon.springsecurityjwt.domain

import javax.persistence.*

@Entity
@Table(name = "ACCOUNT")
data class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Long? = null,

        @Column(name = "ACCOUNT_USERNAME")
        val username: String? = null,

        @Column(name = "ACCOUNT_USERID")
        val userId: String? = "jiwon",

        @Column(name = "ACCOUNT_PASSWORD")
        var password: String? = "1234",

        @Column(name = "ACCOUNT_ROLE")
        @Enumerated(value = EnumType.STRING)
        var userRole: UserRole? = UserRole.USER,

        @Column(name = "ACCOUNT_SOCIAL_ID")
        val socialId: Long? = null,

        @Column(name = "ACCOUNT_SOCIAL_PROFILEPIC")
        val profileHref: String? = null
)